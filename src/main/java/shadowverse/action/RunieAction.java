 package shadowverse.action;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ReaperEffect;
 import shadowverse.characters.AbstractShadowversePlayer;


 public class RunieAction
   extends AbstractGameAction {
     private AbstractPlayer abstractPlayer = AbstractDungeon.player;
     private AbstractCard self;

   public RunieAction(int amount,AbstractCard self) {
       this.amount = amount;
       this.self = self;
   }
 
   
   public void update() {
       for (AbstractCard c : abstractPlayer.hand.group) {
           if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST)&&c!=self) {
               c.flash();
               addToBot((AbstractGameAction)new SFXAction("spell_boost"));
               addToBot((AbstractGameAction)new ReduceCostAction(c));
           }
           if (c.hasTag(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK)&&c!=self) {
               c.flash();
               c.magicNumber = ++c.baseMagicNumber;
               addToBot((AbstractGameAction)new SFXAction("spell_boost"));
           }
       }
       if (this.amount >= 1) {
           addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, 1));
       }
       if (this.amount >= 3) {
           addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)self, AbstractGameAction.AttackEffect.LIGHTNING));
       }
       if (this.amount >= 5) {
           addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ReaperEffect()));
           addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)self, AbstractGameAction.AttackEffect.NONE));
           AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, self.block));
           addToBot((AbstractGameAction)new HealAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, 3));
           addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_MED));
       }
       if (this.amount >= 7) {
           self.baseMagicNumber = 0;
           self.magicNumber = self.baseMagicNumber;
           addToBot((AbstractGameAction)new MakeTempCardInHandAction(self.makeStatEquivalentCopy(), 3));
       }
       this.isDone = true;
   }
 }

