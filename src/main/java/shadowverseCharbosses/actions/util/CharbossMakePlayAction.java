 package shadowverseCharbosses.actions.util;
 
 import shadowverseCharbosses.bosses.AbstractCharBoss;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 
 
 
 
 
 
 
 public class CharbossMakePlayAction
   extends AbstractGameAction
 {
   public void update() {
     if (AbstractCharBoss.boss != null) {
       AbstractCharBoss.boss.makePlay();
     }
     this.isDone = true;
   }
 }

