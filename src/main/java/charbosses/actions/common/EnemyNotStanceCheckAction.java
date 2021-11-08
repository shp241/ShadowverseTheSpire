 package charbosses.actions.common;
 
 import charbosses.bosses.AbstractCharBoss;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 
 
 
 
 
 public class EnemyNotStanceCheckAction
   extends AbstractGameAction
 {
   private AbstractGameAction actionToBuffer;
   
   public EnemyNotStanceCheckAction(AbstractGameAction actionToCheck) {
     this.actionToBuffer = actionToCheck;
   }
 
 
   
   public void update() {
     if (!(AbstractCharBoss.boss.stance instanceof charbosses.stances.EnNeutralStance)) {
       addToBot(this.actionToBuffer);
     }
     
     this.isDone = true;
   }
 }
