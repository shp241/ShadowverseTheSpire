 package shadowverse.action;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;







 
 public class SilenceAction
   extends AbstractGameAction {
     private AbstractPlayer abstractPlayer = AbstractDungeon.player;

   public SilenceAction() {
   }
 
   
   public void update() {
       for (AbstractMonster mo:(AbstractDungeon.getCurrRoom()).monsters.monsters){
           for (AbstractPower pow : mo.powers){
               if (pow.type == AbstractPower.PowerType.BUFF && pow.ID!="Invincible" &&pow.ID!="Mode Shift"&&pow.ID!="Split"&&pow.ID!="Unawakened"&&pow.ID!="Life Link"&&pow.ID!="Fading"&&pow.ID!="Stasis"&&pow.ID!="Minion"&&pow.ID!="Shifting"&&pow.ID!="shadowverse:chushouHealPower"){
                   if (pow.amount>=0){
                       addToBot((AbstractGameAction)new ReducePowerAction(pow.owner,abstractPlayer,pow.ID,3));
                   }else {
                       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(pow.owner,abstractPlayer,pow.ID));
                   }
               }
           }
       }
     this.isDone = true;
   }
 }

