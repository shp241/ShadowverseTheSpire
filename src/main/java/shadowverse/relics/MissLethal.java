/*    */ package shadowverse.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MissLethal
/*    */   extends CustomRelic
/*    */ {
/*    */   public static final String ID = "shadowverse:MissLethal";
/*    */   public static final String IMG = "img/relics/MissLethal.png";
/*    */   public static final String OUTLINE_IMG = "img/relics/outline/MissLethal_Outline.png";
/*    */   public boolean lifeCheck;
/*    */   
/*    */   public MissLethal() {
/* 26 */     super("shadowverse:MissLethal", ImageMaster.loadImage("img/relics/MissLethal.png"), RelicTier.BOSS, LandingSound.SOLID);
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 30 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void onEquip() {
/* 34 */     AbstractDungeon.player.energy.energyMaster++;
/*    */   }
/*    */   
/*    */   public void onUnequip() {
/* 38 */     AbstractDungeon.player.energy.energyMaster--;
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 42 */     flash();
/* 43 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
/* 44 */     this.lifeCheck = true;
/*    */   }
/*    */   
/*    */   public void atTurnStart() {
/* 48 */     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 49 */       if (mo.currentHealth <= mo.maxHealth / 5 && !mo.isDead && !mo.isDeadOrEscaped() && !mo.hasPower("Minion") && this.lifeCheck) {
/* 50 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new IntangiblePlayerPower((AbstractCreature)mo, 1), 1));
/* 51 */         this.lifeCheck = false;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 58 */     return (AbstractRelic)new MissLethal();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\relics\MissLethal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */