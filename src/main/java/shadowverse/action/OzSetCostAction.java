/*    */ package shadowverse.action;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ 
/*    */ public class OzSetCostAction
/*    */   extends AbstractGameAction
/*    */ {
/*    */   private AbstractPlayer abstractPlayer;
/*    */   private AbstractCard card;
/*    */   private ArrayList<AbstractCard> group;
/*    */   
/*    */   public OzSetCostAction(AbstractPlayer abstractPlayer, ArrayList<AbstractCard> group) {
/* 18 */     this.abstractPlayer = abstractPlayer;
/* 19 */     this.group = group;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 24 */     for (AbstractCard c : this.group) {
/* 25 */       if (!c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST) && !c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK) && c.type == AbstractCard.CardType.SKILL && !c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE)) {
/* 26 */         addToBot((AbstractGameAction)new ReduceCostForTurnAction(c, 9));
/*    */       }
/* 28 */       this.isDone = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\action\OzSetCostAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */