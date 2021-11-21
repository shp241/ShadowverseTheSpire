package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ReturnAmuletToDiscardAction extends AbstractGameAction {
    private AbstractCard card;

    public ReturnAmuletToDiscardAction(AbstractCard card) {
        this.card = card;
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
    }

    public void update() {
        if (!AbstractDungeon.player.discardPile.contains(this.card)) {
            AbstractDungeon.player.discardPile.addToRandomSpot(this.card);
            this.card.update();
            AbstractDungeon.player.hand.refreshHandLayout();
        }
        this.isDone = true;
    }
}

