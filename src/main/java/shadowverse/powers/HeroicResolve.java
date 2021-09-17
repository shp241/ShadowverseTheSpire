 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


 public class HeroicResolve
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:HeroicResolve";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:HeroicResolve");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public HeroicResolve(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/HeroicResolve.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3] + this.amount*2 + DESCRIPTIONS[4] + this.amount + DESCRIPTIONS[5] + this.amount*2 + DESCRIPTIONS[6];
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
       int count = 0;
       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
           count++;
       }
       if (count >= 4){
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new StrengthPower((AbstractCreature)this.owner, this.amount), this.amount));
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new DexterityPower((AbstractCreature)this.owner, this.amount), this.amount));
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new DrawCardNextTurnPower((AbstractCreature)this.owner, this.amount), this.amount));
       }
       if (count >= 8){
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new StrengthPower((AbstractCreature)this.owner, this.amount), this.amount));
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new DexterityPower((AbstractCreature)this.owner, this.amount), this.amount));
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new DrawCardNextTurnPower((AbstractCreature)this.owner, this.amount), this.amount));
       }
       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
   }
 }

