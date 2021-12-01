 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
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
 import shadowverse.cards.Temp.Fil;
 import shadowverse.cards.Temp.NaterranGreatTree;
 import shadowverse.cards.Uncommon.Muse;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Bishop;
 import shadowverse.characters.Elf;
 import shadowverse.powers.FilPower;
 import shadowverse.powers.MusePrincessPower;


 public class MusePrincess extends CustomCard {
   public static final String ID = "shadowverse:MusePrincess";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MusePrincess");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MusePrincess.png";
   private  boolean doubleCheck = false;

   public MusePrincess() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
     this.baseBlock = 9;
     this.cardsToPreview = (AbstractCard)new Muse();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(5);
     } 
   }

     public void triggerWhenDrawn() {
         if (Shadowverse.Enhance(3)) {
             super.triggerWhenDrawn();
             setCostForTurn(3);
             applyPowers();
         }
     }

     @Override
     public void applyPowers(){
         if (Shadowverse.Enhance(3))
             setCostForTurn(3);
         else
             resetAttributes();
         super.applyPowers();
     }


     @Override
     public void atTurnStart() {
         if (AbstractDungeon.player.hand.group.contains(this)){
                 setCostForTurn(3);
                 applyPowers();
         }
     }

     public void triggerOnOtherCardPlayed(AbstractCard c) {
         if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
             doubleCheck = true;
             if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 3) {
                 resetAttributes();
                 applyPowers();
             }
         }else {
             if (doubleCheck) {
                 doubleCheck = false;
             }else {
                 if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 3) {
                     resetAttributes();
                     applyPowers();
                 }
             }
         }
     }

     public void triggerOnGainEnergy(int e, boolean dueToCard) {
         if (EnergyPanel.getCurrentEnergy() >= 3) {
             setCostForTurn(3);
         }  else {
             resetAttributes();
         }
         applyPowers();
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer,this.block));
       if (!abstractPlayer.hasPower(MusePrincessPower.POWER_ID))
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new MusePrincessPower((AbstractCreature)abstractPlayer)));
       if (Shadowverse.Enhance(3) && this.costForTurn == 3 ){
           addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new NaterranGreatTree(),3));
           addToBot((AbstractGameAction)new GainEnergyAction(1));
           addToBot((AbstractGameAction)new SFXAction("MusePrincess_EH"));
       }else {
           addToBot((AbstractGameAction)new SFXAction("MusePrincess"));
           addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new NaterranGreatTree()));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MusePrincess();
   }
 }

