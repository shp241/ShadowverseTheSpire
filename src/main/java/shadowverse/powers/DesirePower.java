 package shadowverse.powers;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
 
 
 public class DesirePower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:DesirePower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:DesirePower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   public int damageMax = 0;
   
   public DesirePower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = "shadowverse:DesirePower";
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     loadRegion("static_discharge");
   }
   


     public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
         if (damageAmount > this.damageMax) {
             this.damageMax = damageAmount;
         }
         return damageAmount;
     }

   public void atEndOfRound() {
     flash();
     addToBot((AbstractGameAction)new HealAction(this.owner, this.owner, this.damageMax));
     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "shadowverse:DesirePower"));
     damageMax = 0;
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 }

