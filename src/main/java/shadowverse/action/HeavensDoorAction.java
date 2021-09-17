package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class HeavensDoorAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;

    public void update() {
            if (this.p.hand.group.size() <= 0) {
                this.isDone = true;
                return;
            }

            boolean deckCheck = true;
            ArrayList<String> tmp = new ArrayList<>();
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (tmp.contains(c.cardID)) {
                    deckCheck = false;
                    break;
                }
                tmp.add(c.cardID);
            }
            CardGroup g = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.p.hand.group) {
                if (c.type != AbstractCard.CardType.STATUS && c.type != AbstractCard.CardType.CURSE)
                    g.addToTop(c);
            }
            if (g.size() > 0){
                g.shuffle();
                if (deckCheck){
                    addToBot((AbstractGameAction)new ReduceCostForTurnAction((AbstractCard)g.group.get(0),6));
                }else {
                    addToBot((AbstractGameAction)new ReduceCostForTurnAction((AbstractCard)g.group.get(0),1));
                }
                ((AbstractCard)g.group.get(0)).superFlash();
                ((AbstractCard)g.group.get(0)).applyPowers();
            }
            this.isDone = true;
            return;
    }
}
