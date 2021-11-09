 package charbosses.actions.util;
 
 import charbosses.bosses.AbstractCharBoss;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 
 public class CharbossDoCardQueueAction
   extends AbstractGameAction {
   private AbstractCard c;
   
   public CharbossDoCardQueueAction(AbstractCard c) {
     this.c = c;
   }
 
   
   public void update() {
     if (AbstractCharBoss.boss != null) {
       AbstractCharBoss.boss.useCard(this.c, (AbstractMonster)AbstractCharBoss.boss, this.c.energyOnUse);
     }
     
     this.isDone = true;
   }
 }