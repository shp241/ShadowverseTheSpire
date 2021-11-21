package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.orbs.HeavyKnight;

import java.util.Iterator;

public class EleganceInActionAction extends AbstractGameAction {
    private AbstractCard card;

    public EleganceInActionAction(AbstractCard card) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.card = card;
    }

    @Override
    public void update() {
        Iterator var1 = DrawCardAction.drawnCards.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if (c.type == AbstractCard.CardType.ATTACK) {
                AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new HeavyKnight()));
                break;
            } else {
                AbstractDungeon.actionManager.addToBottom(new AttackDamageRandomEnemyAction(this.card, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                break;
            }
        }

        this.isDone = true;
    }
}
