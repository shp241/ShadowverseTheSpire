/*    */ package shadowverse.powers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
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
/*    */ 
/*    */ 
/*    */ public class MachineAttackPower
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "shadowverse:MachineAttackPower";
/* 26 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:MachineAttackPower");
/* 27 */   public static final String NAME = powerStrings.NAME;
/* 28 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public MachineAttackPower(AbstractCreature owner, int amount) {
/* 31 */     this.name = NAME;
/* 32 */     this.ID = "shadowverse:MachineAttackPower";
/* 33 */     this.owner = owner;
/* 34 */     this.amount = amount;
/* 35 */     this.type = PowerType.BUFF;
/* 36 */     updateDescription();
/* 37 */     this.img = new Texture("img/powers/MachineAttackPower.png");
/*    */   }
/*    */   
/*    */   public void stackPower(int stackAmount) {
/* 41 */     this.amount += stackAmount;
/* 42 */     if (this.amount >= 999)
/* 43 */       this.amount = 999; 
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 47 */     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
/*    */   }
/*    */ 
/*    */   
/*    */   public void onUseCard(AbstractCard card, UseCardAction action) {
/* 52 */     if (card.hasTag(AbstractShadowversePlayer.Enums.MACHINE)) {
/* 53 */       flash();
/* 54 */       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
/* 55 */       addToBot((AbstractGameAction)new VFXAction(this.owner, (AbstractGameEffect)new SweepingBeamEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.flipHorizontal), 0.2F));
/* 56 */       addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void atEndOfTurn(boolean isPlayer) {
/* 61 */     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "shadowverse:MachineAttackPower"));
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\powers\MachineAttackPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */