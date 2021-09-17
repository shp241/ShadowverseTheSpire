/*    */ package shadowverse.powers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MechabookSorcererPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "shadowverse:MechabookSorcererPower";
/* 24 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:MechabookSorcererPower");
/* 25 */   public static final String NAME = powerStrings.NAME;
/* 26 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public MechabookSorcererPower(AbstractCreature owner) {
/* 29 */     this.name = NAME;
/* 30 */     this.ID = "shadowverse:MechabookSorcererPower";
/* 31 */     this.owner = owner;
/* 32 */     this.amount = -1;
/* 33 */     this.type = PowerType.BUFF;
/* 34 */     updateDescription();
/* 35 */     this.img = new Texture("img/powers/MechabookSorcererPower.png");
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateDescription() {
/* 40 */     this.description = DESCRIPTIONS[0] + '\001' + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 45 */     if (card.hasTag(AbstractShadowversePlayer.Enums.MACHINE)) {
/* 46 */       flash();
/* 47 */       addToBot((AbstractGameAction)new GainEnergyAction(1));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\powers\MechabookSorcererPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */