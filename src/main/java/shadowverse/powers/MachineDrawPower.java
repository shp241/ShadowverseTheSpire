/*    */ package shadowverse.powers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ 
/*    */ public class MachineDrawPower extends AbstractPower {
/*    */   public static final String POWER_ID = "shadowverse:MachineDrawPower";
/* 17 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:MachineDrawPower");
/* 18 */   public static final String NAME = powerStrings.NAME;
/* 19 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public MachineDrawPower(AbstractCreature owner, int amount) {
/* 22 */     this.name = NAME;
/* 23 */     this.ID = "shadowverse:MachineDrawPower";
/* 24 */     this.owner = owner;
/* 25 */     this.amount = amount;
/* 26 */     this.type = PowerType.BUFF;
/* 27 */     updateDescription();
/* 28 */     this.img = new Texture("img/powers/MachineDrawPower.png");
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 32 */     this.amount += stackAmount;
/* 33 */     if (this.amount >= 999)
/* 34 */       this.amount = 999; 
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 38 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 43 */     if (card.hasTag(AbstractShadowversePlayer.Enums.MACHINE)) {
/* 44 */       flash();
/* 45 */       addToBot((AbstractGameAction)new DrawCardAction(this.owner, this.amount));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 50 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "shadowverse:MachineDrawPower"));
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\powers\MachineDrawPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */