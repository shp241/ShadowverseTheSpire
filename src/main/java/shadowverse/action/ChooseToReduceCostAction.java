package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ChooseToReduceCostAction extends AbstractGameAction {

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:ChooseToReduceCost")).TEXT;

    private static final float DURATION_PER_CARD = 0.25F;

    private AbstractPlayer p;

    private AbstractCard.CardTags tag;

    private ArrayList<AbstractCard> cannotChose = new ArrayList<>();

    public ChooseToReduceCostAction(AbstractPlayer p, int amount, AbstractCard.CardTags tag) {
        setValues((AbstractCreature) AbstractDungeon.player, source, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.25F;
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.tag = tag;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.group.size() == 0) {
                this.isDone = true;
                return;
            }
            for (AbstractCard c : this.p.hand.group) {
                if (!c.hasTag(this.tag)) {
                    this.cannotChose.add(c);
                }
            }
            if (this.cannotChose.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }
            this.p.hand.group.removeAll(this.cannotChose);
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
                tickDuration();
                return;
            }
            if (this.p.hand.group.size() == 1) {
                if (this.p.hand.getTopCard().hasTag(tag)){
                    if (this.p.hand.getTopCard().costForTurn>0){
                        addToBot((AbstractGameAction)new ReduceCostForTurnAction(this.p.hand.getTopCard(),this.amount));
                        this.p.hand.addToTop(this.p.hand.getTopCard());
                        this.p.hand.getTopCard().applyPowers();
                        this.p.hand.getTopCard().superFlash();
                    }
                    returnCards();
                    this.isDone = true;
                }
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                if (c.hasTag(tag)){
                    if (c.costForTurn>0){
                        addToBot((AbstractGameAction)new ReduceCostForTurnAction(c,this.amount));
                    }
                    this.p.hand.addToTop(c);
                    c.applyPowers();
                    c.superFlash();
                }
            }
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotChose)
            this.p.hand.addToTop(c);
        this.p.hand.refreshHandLayout();
    }
}
