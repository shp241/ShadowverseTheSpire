 package shadowverseCharbosses.actions.orb;
 
 import shadowverseCharbosses.bosses.AbstractCharBoss;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 
 public class EnemyAnimateOrbAction extends AbstractGameAction {
   private int orbCount;
   
   public EnemyAnimateOrbAction(int amount) {
     this.orbCount = amount;
   }
 
   
   public void update() {
     for (int i = 0; i < this.orbCount; i++) {
       AbstractCharBoss.boss.triggerEvokeAnimation(i);
     }
     this.isDone = true;
   }
 }
