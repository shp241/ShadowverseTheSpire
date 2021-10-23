package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ExhaustSpecificGroupAndDrawAction extends AbstractGameAction {
    List<AbstractCard> notToExhaust;
    CardGroup group;
    boolean upgraded;

    public ExhaustSpecificGroupAndDrawAction(List<AbstractCard> notToExhaust, CardGroup group, boolean upgraded) {
        this.notToExhaust = notToExhaust;
        this.group = group;
        this.upgraded = upgraded;
        this.actionType = ActionType.EXHAUST;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> cardsToExhaust = new ArrayList<>();
        for (AbstractCard c : group.group) {
            for (AbstractCard card:notToExhaust){
                if (c!=card)
                    cardsToExhaust.add(c);
            }
        }
        int drawAmt = upgraded?1:0;
        for (AbstractCard c : cardsToExhaust){
            addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, group));
            drawAmt++;
        }
        addToBot((AbstractGameAction)new DrawCardAction(drawAmt));
        this.isDone = true;
    }
}
