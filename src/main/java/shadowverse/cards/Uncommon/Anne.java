 package shadowverse.cards.Uncommon;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Temp.AnnesSorcery;
import shadowverse.cards.Temp.AnnesSummoning;
import shadowverse.cards.Temp.ExceedBurst;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.relics.AnneBOSS;

import java.util.ArrayList;
import java.util.List;


 public class Anne
   extends CustomCard implements BranchableUpgradeCard {
   public static final String ID = "shadowverse:Anne";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Anne");
   public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:NewAnne");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Anne.png";
   public static final String IMG_PATH2 = "img/cards/NewAnne.png";
   public static final int BASE_COST = 6;
   private boolean branch2 = false;

   private float rotationTimer;
   private int previewIndex;

   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new AnnesSummoning());
     list.add(new AnnesSorcery());
     return list;
   }

   public Anne() {
     super(ID, NAME, IMG_PATH, BASE_COST, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 10;
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
   }

   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (chosenBranch()==0){
       if (c.hasTag(AbstractShadowversePlayer.Enums.MYSTERIA)||(AbstractDungeon.player.hasRelic(AnneBOSS.ID)&&c.type==CardType.SKILL)) {
         flash();
         addToBot((AbstractGameAction)new SFXAction("spell_boost"));
         addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
       }
     }
   }

   public void update() {
     super.update();
     if (!branch2){
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
   }
   
   public void upgrade() {
     ((UpgradeBranch) ((BranchableUpgradeCard) this).possibleBranches().get(chosenBranch())).upgrade();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     ((AbstractShadowversePlayer)abstractPlayer).mysteriaCount++;
     switch (chosenBranch()){
       case 0:
         AbstractCard a = (AbstractCard)new AnnesSummoning();
         AbstractCard b = (AbstractCard)new AnnesSorcery();
         addToBot((AbstractGameAction)new SFXAction("Anne"));
         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer,this.block));
         if (this.upgraded){
           a.upgrade();
           b.upgrade();
         }
         addToBot((AbstractGameAction)new ChoiceAction2(new AbstractCard[] { a, b }));
         this.cost = BASE_COST;
         break;
       case 1:
         int drawPileAmt = abstractPlayer.drawPile.group.size();
         int masterDeckAmt = AbstractDungeon.player.masterDeck.group.size();
         if (drawPileAmt <= masterDeckAmt/2){
           AbstractCard as = (AbstractCard)new AnnesSummoning();
           addToBot((AbstractGameAction)new MakeTempCardInHandAction(as,1));
         }
         if (drawPileAmt>=2){
           int rand = AbstractDungeon.cardRandomRng.random(drawPileAmt-1);
           int rand2 = AbstractDungeon.cardRandomRng.random(drawPileAmt-1);
           while (true){
             if (rand2==rand){
               rand2 = AbstractDungeon.cardRandomRng.random(drawPileAmt-1);
             }else {
               break;
             }
           }
           addToBot((AbstractGameAction)new ExhaustSpecificCardAction(abstractPlayer.drawPile.group.get(rand),abstractPlayer.drawPile));
           addToBot((AbstractGameAction)new ExhaustSpecificCardAction(abstractPlayer.drawPile.group.get(rand2),abstractPlayer.drawPile));
         }
         AbstractCard c = this.cardsToPreview;
         addToBot((AbstractGameAction)new SFXAction("NewAnne"));
         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer,this.block));
         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,1));
         break;
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Anne();
   }

   @Override
   public List<UpgradeBranch> possibleBranches() {
     ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
     list.add(new UpgradeBranch() {
       @Override
       public void upgrade() {
         ++Anne.this.timesUpgraded;
         Anne.this.upgraded = true;
         Anne.this.name = NAME + "+";
         Anne.this.initializeTitle();
         Anne.this.baseBlock = 13;
         Anne.this.upgradedBlock = true;
         Anne.this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
         Anne.this.initializeDescription();
       }
     });
     list.add(new UpgradeBranch() {
       @Override
       public void upgrade() {
         ++Anne.this.timesUpgraded;
         Anne.this.upgraded = true;
         Anne.this.textureImg = IMG_PATH2;
         Anne.this.loadCardImage(IMG_PATH2);
         Anne.this.name = cardStrings2.NAME;
         Anne.this.baseDamage = 5;
         Anne.this.upgradedDamage = true;
         Anne.this.baseBlock = 5;
         Anne.this.upgradedBlock = true;
         Anne.this.initializeTitle();
         Anne.this.rawDescription = cardStrings2.DESCRIPTION;
         Anne.this.initializeDescription();
         Anne.this.cardsToPreview = (AbstractCard)new ExceedBurst();
         Anne.this.rarity = CardRarity.RARE;
         Anne.this.setDisplayRarity(Anne.this.rarity);
         Anne.this.target = CardTarget.ENEMY;
         Anne.this.upgradeBaseCost(1);
         Anne.this.branch2 = true;
       }
     });
     return list;
   }
 }


