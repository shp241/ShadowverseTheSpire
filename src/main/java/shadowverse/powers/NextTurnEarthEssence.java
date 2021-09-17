/*    */ package shadowverse.powers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ public class NextTurnEarthEssence
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "shadowverse:NextTurnEarthEssence";
/* 15 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:NextTurnEarthEssence");
/* 16 */   public static final String NAME = powerStrings.NAME;
/* 17 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public NextTurnEarthEssence(AbstractCreature owner, int amount) {
/* 20 */     this.name = NAME;
/* 21 */     this.ID = "shadowverse:NextTurnEarthEssence";
/* 22 */     this.owner = owner;
/* 23 */     this.amount = amount;
/* 24 */     this.type = PowerType.BUFF;
/* 25 */     updateDescription();
/* 26 */     this.img = new Texture("img/powers/EarthEssence.png");
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 30 */     this.amount += stackAmount;
/* 31 */     if (this.amount >= 999) {
/* 32 */       this.amount = 999;
/*    */     }
/*    */   }
/*    */   
/*    */   public void atStartOfTurn() {
/* 37 */     flash();
/* 38 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this));
/* 39 */     addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new EarthEssence(this.owner, this.amount), this.amount));
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 43 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\powers\NextTurnEarthEssence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */