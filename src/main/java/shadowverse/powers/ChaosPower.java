 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.GainStrengthPower;
 import com.megacrit.cardcrawl.powers.LoseStrengthPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 
 public class ChaosPower extends AbstractPower {
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ChaosPower"); public static final String POWER_ID = "shadowverse:ChaosPower";
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   private boolean onPlayer;
   
   public ChaosPower(AbstractCreature owner, int amount, boolean playerControlled) {
     this.name = NAME;
     this.ID = "shadowverse:ChaosPower";
     this.owner = owner;
     this.amount = amount;
     this.onPlayer = playerControlled;
     this.type = PowerType.DEBUFF;
     updateDescription();
     this.img = new Texture("img/powers/ChaosPower.png");
   }
 
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 
   
   public void atStartOfTurn() {
     if (this.onPlayer) {
       flash();
       int powNum = AbstractDungeon.aiRng.random(-this.amount, this.amount);
       if (powNum > 0) {
         addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, powNum), powNum));
         addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new LoseStrengthPower(this.owner, powNum), powNum));
       } else if (powNum < 0 && !this.owner.hasPower("Artifact")) {
         addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, powNum), powNum));
         addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new GainStrengthPower(this.owner, -powNum), -powNum));
       } 
     } 
   }
   
   public void atEndOfRound() {
     if (!this.onPlayer){
         flash();
         int powNum = AbstractDungeon.aiRng.random(-this.amount, this.amount);
         if (powNum > 0) {
             addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, powNum), powNum));
             addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new LoseStrengthPower(this.owner, powNum), powNum));
         } else if (powNum < 0 && !this.owner.hasPower("Artifact")) {
             addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, powNum), powNum));
             addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new GainStrengthPower(this.owner, -powNum), -powNum));
         }
     }
   }
 }


