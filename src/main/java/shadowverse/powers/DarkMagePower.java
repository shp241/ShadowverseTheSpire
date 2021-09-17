 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
 
 public class DarkMagePower
   extends EarthEssence {
   public static final String POWER_ID = "shadowverse:DarkMagePower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:DarkMagePower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public DarkMagePower(AbstractCreature owner, int amount) {
     super(owner, amount);
     this.name = NAME;
     this.ID = "shadowverse:DarkMagePower";
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     if (this.amount >= 5)
       this.amount = 5; 
     updateDescription();
     this.img = new Texture("img/powers/DarkMagePower.png");
   }
   
   public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
     if (damage > 4.0F)
       damage = 4.0F; 
     return damage;
   }
   
   public void atEndOfRound() {
     flash();
     addToBot((AbstractGameAction)new DrawCardAction(this.owner, 1));
     addToBot((AbstractGameAction)new HealAction(this.owner, this.owner, 1));
     if (this.amount == 0) {
       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "shadowverse:DarkMagePower"));
     } else {
       addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "shadowverse:DarkMagePower", 1));
     } 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 }


