 package shadowverse.action;
 
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import java.util.ArrayList;
 
 public class BlockPerCardAction
   extends AbstractGameAction {
   private int blockPerCard;
   
   public BlockPerCardAction(int blockAmount) {
     this.blockPerCard = blockAmount;
     setValues((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player);
     this.actionType = ActionType.BLOCK;
   }
   
   public void update() {
     ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
     for (AbstractCard c : AbstractDungeon.player.hand.group) {
       cardsToExhaust.add(c);
     }
     for (AbstractCard c : cardsToExhaust)
       addToTop((AbstractGameAction)new GainBlockAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, this.blockPerCard)); 
     for (AbstractCard c : cardsToExhaust)
       addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand)); 
     this.isDone = true;
   }
 }


