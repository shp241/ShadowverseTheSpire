package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DramaticRetreatAction extends AbstractGameAction {
    private AbstractCard targetCard;
    private CardGroup group;
    private float startingDuration;

    public DramaticRetreatAction(AbstractCard targetCard, CardGroup group) {
        this.targetCard = targetCard;
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.group = group;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }


    @Override
    public void update() {
        if (this.duration == this.startingDuration && this.group.contains(this.targetCard)) {
            this.group.moveToDeck(this.targetCard, true);
            if (this.targetCard.cost > 0) {
                this.targetCard.freeToPlayOnce = true;
            }
        }

        this.tickDuration();
    }
}
