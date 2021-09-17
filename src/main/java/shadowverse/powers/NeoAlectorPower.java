 package shadowverse.powers;
 


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


 public class NeoAlectorPower
   extends AbstractPower {
   public static final String POWER_ID = "shadowverse:NeoAlectorPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:NeoAlectorPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public NeoAlectorPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     loadRegion("carddraw");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999) {
       this.amount = 999;
     }
   }
   
   public void atStartOfTurn() {
     flash();
     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this));
     addToBot((AbstractGameAction)new DrawCardAction(this.amount));
     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,(AbstractPower)new StrengthPower(this.owner,this.amount),this.amount));
     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,(AbstractPower)new DexterityPower(this.owner,this.amount),this.amount));
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]+this.amount+DESCRIPTIONS[2];
   }
 }

