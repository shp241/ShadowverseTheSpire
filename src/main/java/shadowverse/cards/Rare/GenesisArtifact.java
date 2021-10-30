 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.Gdx;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.cards.Temp.*;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Nemesis;

 import java.util.ArrayList;


 public class GenesisArtifact
   extends CustomCard
 {
   public static final String ID = "shadowverse:GenesisArtifact";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GenesisArtifact");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/GenesisArtifact.png";
     private float rotationTimer;
     private int previewIndex;
     public static ArrayList<AbstractCard> returnChoice() {
         ArrayList<AbstractCard> list = new ArrayList<>();
         list.add(new GuardArtifact());
         list.add(new DefectArtifact());
         list.add(new CannonArtifact());
         return list;
     }

   public GenesisArtifact() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 8;
     this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
   }

     public void update() {
         super.update();
         if (this.hb.hovered)
             if (this.rotationTimer <= 0.0F) {
                 this.rotationTimer = 2.0F;
                 this.cardsToPreview = (AbstractCard)returnChoice().get(previewIndex).makeCopy();
                 if (this.previewIndex == returnChoice().size() - 1) {
                     this.previewIndex = 0;
                 } else {
                     this.previewIndex++;
                 }
                 if (this.upgraded)
                     this.cardsToPreview.upgrade();
             } else {
                 this.rotationTimer -= Gdx.graphics.getDeltaTime();
             }
     }

   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     }
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
       AbstractCard g = (AbstractCard)new GuardArtifact();
       AbstractCard d = (AbstractCard)new DefectArtifact();
       g.setCostForTurn(0);
       d.setCostForTurn(0);
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(g));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(d));
       ArrayList<AbstractCard> list = new ArrayList<>();
       ArrayList<String> dup = new ArrayList<>();
       for (AbstractCard c: abstractPlayer.exhaustPile.group){
           if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT)&&!dup.contains(c.cardID)){
               dup.add(c.cardID);
               AbstractCard card = c.makeCopy();
               list.add(card);
           }
       }
       if (list.size()>=6){
           addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new CannonArtifact(),2));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new GenesisArtifact();
   }
 }

