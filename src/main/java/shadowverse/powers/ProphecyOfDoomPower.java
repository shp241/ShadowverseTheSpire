 package shadowverse.powers;

 import com.badlogic.gdx.graphics.Color;
 import com.badlogic.gdx.graphics.Texture;
 import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

 public class ProphecyOfDoomPower extends AbstractPower implements HealthBarRenderPower {
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ProphecyOfDoomPower");
   public static final String POWER_ID = "shadowverse:ProphecyOfDoomPower";
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public ProphecyOfDoomPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = "shadowverse:ProphecyOfDoomPower";
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.DEBUFF;
     updateDescription();
     this.img = new Texture("img/powers/ProphecyOfDoomPower.png");
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
       addToBot((AbstractGameAction)new LoseHPAction(this.owner,this.owner,this.amount, AbstractGameAction.AttackEffect.POISON));
   }

     @Override
     public int getHealthBarAmount() {
         return this.amount;
     }

     @Override
     public Color getColor() {
         return Color.PURPLE;
     }
 }


