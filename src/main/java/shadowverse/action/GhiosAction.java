 package shadowverse.action;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


 public class GhiosAction
   extends AbstractGameAction {
     private AbstractPlayer abstractPlayer = AbstractDungeon.player;
     private AbstractCard self;

   public GhiosAction(AbstractCard self) {
       this.self = self;
   }
 
   
   public void update() {
       if (self.magicNumber >= 12) {
           self.costForTurn=0;
           self.cost=0;
           self.isCostModified = true;
           self.applyPowers();
       }else if (self.magicNumber>=9){
           for (AbstractCreature mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
               addToBot((AbstractGameAction)new ApplyPowerAction(mo, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new StrengthPower(mo, -2), -2, true, AbstractGameAction.AttackEffect.NONE));
           }
       }else if (self.magicNumber>=7){
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new BlurPower((AbstractCreature)AbstractDungeon.player, 1), 1));
           addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 8));
       }else if (self.magicNumber>=3){
           for (AbstractCreature m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
               if (!m.isDeadOrEscaped())
               addToBot((AbstractGameAction)new JudgementAction(m, 10));
           }
       }
       this.isDone = true;
   }
 }

