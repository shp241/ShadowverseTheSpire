 package shadowverse.powers;

 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

 public class TheFoolPower extends AbstractPower {
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:TheFoolPower"); public static final String POWER_ID = "shadowverse:TheFoolPower";
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   public int maxHealth;

   public TheFoolPower(AbstractCreature owner, int amount,int maxHealth) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.maxHealth = maxHealth;
     this.type = PowerType.DEBUFF;
     updateDescription();
     this.img = new Texture("img/powers/TheFoolPower.png");
   }
 
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 

   
   public void atEndOfRound() {
       flash();
       this.owner.decreaseMaxHealth((this.maxHealth/10)*this.amount);
   }
 }


