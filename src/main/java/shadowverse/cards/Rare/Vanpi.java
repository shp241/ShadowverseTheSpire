 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.Gdx;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction;
 import shadowverse.cards.Temp.*;
 import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.stance.Vengeance;

 import java.util.ArrayList;


 public class Vanpi
   extends CustomCard
 {
   public static final String ID = "shadowverse:Vanpi";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Vanpi");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Vanpi.png";
     private float rotationTimer;
     private int previewIndex;

     public static ArrayList<AbstractCard> returnShikigami() {
         ArrayList<AbstractCard> list = new ArrayList<>();
         list.add(new ForestBat());
         list.add(new Vanpi1());
         list.add(new Vanpi2());
         return list;
     }

   public Vanpi() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ALL);
     this.baseBlock = 18;
   }

     public void update() {
         super.update();
         if (this.hb.hovered)
             if (this.rotationTimer <= 0.0F) {
                 this.rotationTimer = 2.0F;
                 this.cardsToPreview = (AbstractCard)returnShikigami().get(previewIndex).makeCopy();
                 if (this.previewIndex == returnShikigami().size() - 1) {
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
       upgradeBlock(3);
         this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
         initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
       if (p.hasPower(EpitaphPower.POWER_ID)||p.stance.ID.equals(Vengeance.STANCE_ID)){
           AbstractCard v2 = (AbstractCard)new Vanpi2();
           AbstractCard v1 =(AbstractCard)new Vanpi1();
           if (this.upgraded){
               v1.upgrade();
               v2.upgrade();
           }
           addToBot((AbstractGameAction)new ChoiceAction(new AbstractCard[]{v2,v1}));
       }else {
           addToBot((AbstractGameAction)new SFXAction("Vanpi"));
           addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
           addToBot((AbstractGameAction)new LoseHPAction(p,p,2));
           for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
               if (!mo.isDeadOrEscaped()){
                   addToBot((AbstractGameAction)new LoseHPAction(mo,p,2));
               }
           }
           AbstractCard fb = (AbstractCard)new ForestBat();
           if (this.upgraded)
               fb.upgrade();
           addToBot((AbstractGameAction)new MakeTempCardInHandAction(fb));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Vanpi();
   }
 }

