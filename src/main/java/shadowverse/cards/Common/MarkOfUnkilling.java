 package shadowverse.cards.Common;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;


 public class MarkOfUnkilling
   extends CustomCard
 {
   public static final String ID = "shadowverse:MarkOfUnkilling";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MarkOfUnkilling");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MarkOfUnkilling.png";
   private boolean doubleCheck = false;

   public MarkOfUnkilling() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.COMMON, CardTarget.ENEMY);
     this.baseMagicNumber = 4;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
   }


   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(2);
     } 
   }
   public void triggerWhenDrawn() {
     if (Shadowverse.Enhance(2)) {
       super.triggerWhenDrawn();
       setCostForTurn(2);
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
         applyPowers();
       }
     }else {
       if (doubleCheck) {
         doubleCheck = false;
       }else {
         if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
           resetAttributes();
           applyPowers();
         }
       }
     }
   }

   public void triggerOnGainEnergy(int e, boolean dueToCard) {
     if (EnergyPanel.getCurrentEnergy() >= 2) {
       setCostForTurn(2);
     }  else {
       resetAttributes();
     }
     applyPowers();
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("MarkOfUnkilling"));
     boolean debuffCheck = false;
     for (AbstractPower p:abstractMonster.powers){
       if (p.type == AbstractPower.PowerType.DEBUFF){
         debuffCheck = true;
       }
     }
     if (this.costForTurn == 2 && Shadowverse.Enhance(2)){
       debuffCheck = true;
     }
     if (debuffCheck){
       addToBot((AbstractGameAction)new ApplyPowerAction(abstractMonster,abstractPlayer,(AbstractPower)new StrengthPower(abstractMonster,-this.magicNumber),-this.magicNumber));
     }
     addToBot((AbstractGameAction)new DrawCardAction(1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MarkOfUnkilling();
   }
 }

