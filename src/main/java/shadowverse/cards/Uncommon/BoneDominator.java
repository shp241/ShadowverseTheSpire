 package shadowverse.cards.Uncommon;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.BurialAction;
import shadowverse.cards.Common.DemonicProcession;
import shadowverse.cards.Common.HungrySlash;
import shadowverse.cards.Common.SpiritCurator;
 import shadowverse.cards.Rare.Ferry;
 import shadowverse.cards.Rare.HinterlandGhoul;
import shadowverse.cards.Rare.TheLovers;
import shadowverse.cards.Temp.BoneDominator_Accelerate;
 import shadowverse.cards.Temp.InstantPotion;
 import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


 public class BoneDominator
   extends CustomCard
 {
   public static final String ID = "shadowverse:BoneDominator";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BoneDominator");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/BoneDominator.png";
   public boolean doubleCheck = false;



   public BoneDominator() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 20;
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
     this.cardsToPreview = (AbstractCard)new HinterlandGhoul();
   }
 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(5);
       upgradeMagicNumber(2);
     } 
   }
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
       if (c instanceof DemonicProcession||c instanceof TheLovers ||c instanceof HungrySlash||c instanceof SpiritCurator||c instanceof Ferry||c instanceof InstantPotion){
           this.type = CardType.ATTACK;
           this.resetAttributes();
           return;
       }
       if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
           doubleCheck = true;
           if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
               setCostForTurn(0);
               this.type = CardType.SKILL;
               applyPowers();
           }
       }else {
           if (doubleCheck) {
               doubleCheck = false;
           }else {
               if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                   setCostForTurn(0);
                   this.type = CardType.SKILL;
                   applyPowers();
               }
           }
       }
   }

   
   public void triggerOnGainEnergy(int e, boolean dueToCard) {
     if (EnergyPanel.getCurrentEnergy() >= 3 && this.type != CardType.ATTACK) {
       resetAttributes();
       this.type = CardType.ATTACK;
       applyPowers();
     }
   }
   
   public void triggerWhenDrawn() {
     if (Shadowverse.Accelerate((AbstractCard)this)) {
       super.triggerWhenDrawn();
       setCostForTurn(0);
       this.type = CardType.SKILL;
     } else {
       this.type = CardType.ATTACK;
     } 
     applyPowers();
   }

   @Override
   public void atTurnStart() {
       if (AbstractDungeon.player.hand.group.contains(this)){
               resetAttributes();
               this.type = CardType.ATTACK;
           applyPowers();
       }
   }

     public void onMoveToDiscard() {
         resetAttributes();
         this.type = CardType.ATTACK;
         applyPowers();
     }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
         addToBot((AbstractGameAction)new SFXAction("BoneDominator_Acc"));
         addToBot((AbstractGameAction)new DrawCardAction(1));
         addToBot((AbstractGameAction)new BurialAction(1,(AbstractGameAction)new GainBlockAction(abstractPlayer,this.magicNumber)));
     } else {
         addToBot((AbstractGameAction)new SFXAction("BoneDominator"));
         addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
     } 
   }
 
   
   public AbstractCard makeSameInstanceOf() {
     AbstractCard card = null;
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       card = (new BoneDominator_Accelerate()).makeStatEquivalentCopy();
       card.uuid = (new BoneDominator_Accelerate()).uuid;
     } else {
       card = makeStatEquivalentCopy();
       card.uuid = this.uuid;
     } 
     return card;
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new BoneDominator();
   }
 }

