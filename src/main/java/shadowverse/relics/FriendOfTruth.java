/*    */ package shadowverse.relics;
/*    */ 
/*    */ import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
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
/*    */ public class FriendOfTruth
/*    */   extends CustomRelic
/*    */ {
/*    */   public static final String ID = "shadowverse:FriendOfTruth";
/*    */   public static final String IMG = "img/relics/FriendOfTruth.png";
/*    */   public static final String OUTLINE_IMG = "img/relics/outline/FriendOfTruth_Outline.png";
/*    */   
/*    */   public FriendOfTruth() {
/* 25 */     super("shadowverse:FriendOfTruth", ImageMaster.loadImage("img/relics/FriendOfTruth.png"), RelicTier.UNCOMMON, LandingSound.FLAT);
/*    */   }
/*    */   
/*    */   public String getUpdatedDescription() {
/* 29 */     return this.DESCRIPTIONS[0];
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractRelic makeCopy() {
/* 34 */     return (AbstractRelic)new FriendOfTruth();
/*    */   }
/*    */   
/*    */   public void atBattleStartPreDraw() {
/* 38 */     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
/* 39 */     addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Shiv(), 1, false));
/* 40 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 41 */       if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)) {
/* 42 */         c.flash();
/* 43 */         addToBot((AbstractGameAction)new SFXAction("spell_boost"));
/* 44 */         addToBot((AbstractGameAction)new ReduceCostAction(c)); continue;
/*    */       } 
/* 46 */       if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)) {
/* 47 */         c.flash();
/*    */         
/* 49 */         c.magicNumber = ++c.baseMagicNumber;
/* 50 */         addToBot((AbstractGameAction)new SFXAction("spell_boost"));
/*    */       } 
/*    */     } 
/* 53 */     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
/* 54 */       if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)) {
/* 55 */         c.flash();
/* 56 */         addToBot((AbstractGameAction)new SFXAction("spell_boost"));
/* 57 */         addToBot((AbstractGameAction)new ReduceCostAction(c)); continue;
/*    */       } 
/* 59 */       if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)) {
/* 60 */         c.flash();
/*    */         
/* 62 */         c.magicNumber = ++c.baseMagicNumber;
/* 63 */         addToBot((AbstractGameAction)new SFXAction("spell_boost"));
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\relics\FriendOfTruth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */