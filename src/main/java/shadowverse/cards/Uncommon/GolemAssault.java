 package shadowverse.cards.Uncommon;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

 import shadowverse.Shadowverse;
import shadowverse.cards.Temp.ConjureGuardian;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 import shadowverse.powers.EarthEssence;
 
 
 
 public class GolemAssault
   extends CustomCard
 {
   public static final String ID = "shadowverse:GolemAssault";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GolemAssault");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/GolemAssault.png";
   private boolean canEnhance = false;
   private boolean doubleCheck =false;
   
   public GolemAssault() {
     super("shadowverse:GolemAssault", NAME, "img/cards/GolemAssault.png", 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
     this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
     this.cardsToPreview = (AbstractCard)new ConjureGuardian();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.cardsToPreview.upgrade();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
   
   public void triggerWhenDrawn() {
     if (Shadowverse.Enhance(2)) {
       super.triggerWhenDrawn();
       setCostForTurn(2);
       this.canEnhance = true;
       applyPowers();
     } 
   }
     @Override
     public void applyPowers(){
         if (Shadowverse.Enhance(2))
             setCostForTurn(2);
         else
             resetAttributes();
         super.applyPowers();
     }
     
     @Override
     public void atTurnStart() {
         if (AbstractDungeon.player.hand.group.contains(this)){
             if (Shadowverse.Enhance(2)) {
                 super.triggerWhenDrawn();
                 setCostForTurn(2);
                 applyPowers();
             }
         }
     }

     public void triggerOnOtherCardPlayed(AbstractCard c) {
         if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
             doubleCheck = true;
             if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                 resetAttributes();
                 this.canEnhance = false;
                 applyPowers();
             }
         }else {
             if (doubleCheck) {
                 doubleCheck = false;
             }else {
                 if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                     resetAttributes();
                     this.canEnhance = false;
                     applyPowers();
                 }
             }
         }
     }
   
   public void triggerOnGainEnergy(int e, boolean dueToCard) {
     if (EnergyPanel.getCurrentEnergy()  >= 2) {
       setCostForTurn(2);
       this.canEnhance = true;
     }  else {
         resetAttributes();
         this.canEnhance =false;
     }
       applyPowers();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     boolean powerExists = false;
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:EarthEssence")) {
         powerExists = true;
         break;
       } 
     } 
     if (powerExists) {
       ((AbstractShadowversePlayer)abstractPlayer).earthCount++;
       c.setCostForTurn(0);
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, -1), -1));
     } 
     if (this.upgraded) {
       c.upgrade();
     }
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
     if (this.costForTurn == 2 && Shadowverse.Enhance(2)) {
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 2));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new GolemAssault();
   }
 }

