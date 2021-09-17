package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BetterAutoPlayCardAction extends AbstractGameAction {
    private AbstractCard card;
    private CardGroup group;

    public BetterAutoPlayCardAction(AbstractCard card, CardGroup group) {
        this.card = card;
        this.group = group;
    }

    public void update() {
        this.isDone = true;
        if (this.group.contains(this.card)) {
            this.card.targetAngle = 0.0F;
            AbstractMonster m = null;
            if (this.card.target== AbstractCard.CardTarget.ENEMY)
                    m = AbstractDungeon.getRandomMonster();
            this.group.removeCard(this.card);
            AbstractDungeon.actionManager.addToTop(new QueueCardAction(this.card, (AbstractCreature)m));
        }
    }
}
