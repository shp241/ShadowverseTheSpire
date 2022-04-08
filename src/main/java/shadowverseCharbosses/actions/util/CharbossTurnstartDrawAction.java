 package shadowverseCharbosses.actions.util;
 
 import shadowverseCharbosses.bosses.AbstractCharBoss;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 
 public class CharbossTurnstartDrawAction
   extends AbstractGameAction
 {
   public void update() {
     if (AbstractCharBoss.boss != null) AbstractCharBoss.boss.endTurnStartTurn(); 
     this.isDone = true;
   }
 }

