 package shadowverseCharbosses.actions.common;
 
 import shadowverseCharbosses.bosses.AbstractCharBoss;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 
 
 
 
 
 public class EnemyNotStanceCheckAction
   extends AbstractGameAction
 {
   private AbstractGameAction actionToBuffer;
   
   public EnemyNotStanceCheckAction(AbstractGameAction actionToCheck) {
     this.actionToBuffer = actionToCheck;
   }
 
 
   
   public void update() {
     if (!(AbstractCharBoss.boss.stance instanceof shadowverseCharbosses.stances.EnNeutralStance)) {
       addToBot(this.actionToBuffer);
     }
     
     this.isDone = true;
   }
 }
