/*    */ package shadowverse.action;
/*    */ 
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class ModifyMagicNumberAction
/*    */   extends AbstractGameAction {
/*    */   private UUID uuid;
/*    */   
/*    */   public ModifyMagicNumberAction(UUID targetUUID, int amount) {
/* 13 */     setValues(this.target, this.source, amount);
/* 14 */     this.actionType = ActionType.CARD_MANIPULATION;
/* 15 */     this.uuid = targetUUID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {
/* 20 */     for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
/* 21 */       c.baseMagicNumber += this.amount;
/* 22 */       if (c.baseMagicNumber < 0)
/* 23 */         c.baseMagicNumber = 0; 
/*    */     } 
/* 25 */     this.isDone = true;
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\action\ModifyMagicNumberAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */