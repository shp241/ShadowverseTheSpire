 package charbosses.actions.unique;
 
 import charbosses.bosses.AbstractCharBoss;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 
 public class EnemyHaltAction extends AbstractGameAction {
   private AbstractMonster m;
   private int additionalAmt;
   
   public EnemyHaltAction(AbstractMonster monster, int block, int magicNumber) {
     this.m = monster;
     this.amount = block;
     this.additionalAmt = magicNumber;
   }
   
   public void update() {
     if (AbstractCharBoss.boss.stance instanceof charbosses.stances.EnWrathStance) {
       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)this.m, (AbstractCreature)this.m, this.amount + this.additionalAmt));
     } else {
       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)this.m, (AbstractCreature)this.m, this.amount));
     } 
     this.isDone = true;
   }
 }
