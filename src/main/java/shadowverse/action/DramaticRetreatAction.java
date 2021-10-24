package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDrawPileEffect;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;

public class DramaticRetreatAction extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("BetterToHandAction")).TEXT;

    private AbstractPlayer p;

    public DramaticRetreatAction(int amount) {
        this.p = AbstractDungeon.player;
        setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard c : this.p.discardPile.group) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    tmp.addToRandomSpot(c);
                }
            }
            if (tmp.size() == 0) {
                this.isDone = true;
                return;
            }
            if (tmp.size() == 1) {
                AbstractCard card = tmp.getTopCard();
                if (this.p.hand.size() == 10) {
                    this.p.discardPile.moveToDiscardPile(card);
                    this.p.createHandIsFullDialog();
                } else {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.current_x = CardGroup.DISCARD_PILE_X;
                    card.current_y = CardGroup.DISCARD_PILE_Y;
                    this.p.discardPile.removeCard(card);
                    card.freeToPlayOnce = true;
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(card, true, false));
                    this.p.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                }
                this.isDone = true;
                return;
            }
            AbstractDungeon.gridSelectScreen.open(tmp, this.amount, TEXT[0], false);
            tickDuration();
            return;
        }
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                c.unhover();
                this.p.discardPile.removeCard(c);
                c.freeToPlayOnce = true;
                AbstractDungeon.effectList.add(new ShowCardAndAddToDrawPileEffect(c, true, false));
                this.p.hand.refreshHandLayout();
                this.p.hand.applyPowers();
            }
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            this.p.hand.refreshHandLayout();
        }
        tickDuration();
        if (this.isDone) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE) && !(c.cardID == "shadowverse:Satan") && !(c.cardID == "shadowverse:Technolord")) {
                    if (Shadowverse.Accelerate((AbstractCard) c)) {
                        c.setCostForTurn(0);
                        c.type = AbstractCard.CardType.SKILL;
                    } else {
                        c.type = AbstractCard.CardType.ATTACK;
                    }
                    c.applyPowers();
                } else if (c.cardID == "shadowverse:Technolord") {
                    if (Shadowverse.Accelerate((AbstractCard) c)) {
                        c.setCostForTurn(1);
                        c.type = AbstractCard.CardType.SKILL;
                    } else {
                        c.type = AbstractCard.CardType.ATTACK;
                    }
                    c.applyPowers();
                } else if (c.hasTag(AbstractShadowversePlayer.Enums.ENHANCE) && !(c.cardID == "shadowverse:TheHanged") && !(c.cardID == "shadowverse:Aerin") && !(c.cardID == "shadowverse:Korwa")) {
                    if (Shadowverse.Enhance(2)) {
                        c.setCostForTurn(2);
                        c.applyPowers();
                    }
                } else if (c.hasTag(AbstractShadowversePlayer.Enums.CRYSTALLIZE)) {
                    if (Shadowverse.Accelerate((AbstractCard) c)) {
                        c.setCostForTurn(0);
                        c.type = AbstractCard.CardType.POWER;
                    } else {
                        c.type = AbstractCard.CardType.ATTACK;
                    }
                    c.applyPowers();
                } else if (c.cardID == "shadowverse:TheHanged" || c.cardID == "shadowverse:Aerin" || c.cardID == "shadowverse:Korwa") {
                    if (Shadowverse.Enhance(3)) {
                        c.setCostForTurn(3);
                        c.applyPowers();
                    }
                }
            }
        }
    }
}
