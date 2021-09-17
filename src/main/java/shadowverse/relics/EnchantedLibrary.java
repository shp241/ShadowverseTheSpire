/*    */ package shadowverse.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.SkillFromDeckToHandAction;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*    */ import com.megacrit.cardcrawl.relics.AbstractRelic;
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
/*    */ public class EnchantedLibrary
/*    */   extends CustomRelic
/*    */ {
/*    */   public static final String ID = "shadowverse:EnchantedLibrary";
/*    */   public static final String IMG = "img/relics/EnchantedLibrary.png";
/*    */   public static final String OUTLINE_IMG = "img/relics/outline/EnchantedLibrary_Outline.png";
/*    */   
/*    */   public EnchantedLibrary() {
/* 29 */     super("shadowverse:EnchantedLibrary", ImageMaster.loadImage("img/relics/EnchantedLibrary.png"), RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 33 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */   
/*    */   public void atBattleStart() {
/* 37 */     this.counter = 0;
/* 38 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
/*    */   }
/*    */   
/*    */   public void atTurnStart() {
/* 42 */     if (!this.grayscale) {
/* 43 */       this.counter++;
/* 44 */       addToBot((AbstractGameAction)new SkillFromDeckToHandAction(1));
/*    */     } 
/* 46 */     if (this.counter == 2) {
/* 47 */       flash();
/* 48 */       this.counter = -1;
/* 49 */       this.grayscale = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onVictory() {
/* 55 */     this.counter = -1;
/* 56 */     this.grayscale = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 61 */     return (AbstractRelic)new EnchantedLibrary();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\relics\EnchantedLibrary.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */