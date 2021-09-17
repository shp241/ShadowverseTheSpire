/*    */ package shadowverse.action;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class BlockPerCardAction
/*    */   extends AbstractGameAction {
/*    */   private int blockPerCard;
/*    */   
/*    */   public BlockPerCardAction(int blockAmount) {
/* 16 */     this.blockPerCard = blockAmount;
/* 17 */     setValues((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player);
/* 18 */     this.actionType = ActionType.BLOCK;
/*    */   }
/*    */   
/*    */   public void update() {
/* 22 */     ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
/* 23 */     for (AbstractCard c : AbstractDungeon.player.hand.group) {
/* 24 */       cardsToExhaust.add(c);
/*    */     }
/* 26 */     for (AbstractCard c : cardsToExhaust)
/* 27 */       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.blockPerCard)); 
/* 28 */     for (AbstractCard c : cardsToExhaust)
/* 29 */       addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand)); 
/* 30 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\action\BlockPerCardAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */