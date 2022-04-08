 package shadowverseCharbosses.actions.util;
 
 import shadowverseCharbosses.bosses.AbstractCharBoss;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.monsters.MonsterGroup;

 public class CharBossMonsterGroup
   extends MonsterGroup
 {
   public CharBossMonsterGroup(AbstractMonster[] input) {
     super(input);
   }
   public void applyPreTurnLogic() {
     for (AbstractMonster m : this.monsters) {
       if (m instanceof AbstractCharBoss) {
         AbstractCharBoss cB = (AbstractCharBoss)m;
         if (!m.isDying && !m.isEscaping) {
           if (!m.hasPower("Barricade") && !m.hasPower("Blur")) {
               m.loseBlock();
           }
           m.applyStartOfTurnPowers();
         } 
         
         continue;
       } 
       m.applyStartOfTurnPowers();
     } 
   }
 }

