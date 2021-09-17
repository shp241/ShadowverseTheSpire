 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

 public class WitchsCauldronPower
   extends AbstractPower implements BetterOnApplyPowerPower {
   public static final String POWER_ID = "shadowverse:WitchsCauldronPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:WitchsCauldronPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public WitchsCauldronPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = "shadowverse:WitchsCauldronPower";
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/WitchsCauldronPower.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }


     @Override
     public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
         return true;
     }

     @Override
     public int betterOnApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
       if (power instanceof EarthEssence && stackAmount<0){
           flash();
           addToBot((AbstractGameAction)new DrawCardAction(this.owner, this.amount));
       }
         return stackAmount;
     }
 }

