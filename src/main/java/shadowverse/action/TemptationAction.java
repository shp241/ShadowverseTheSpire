package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;

public class TemptationAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private AbstractMonster monster;
    public TemptationAction(AbstractMonster monster) {
        this.monster = monster;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generateCardChoices(), CardRewardScreen.TEXT[1], true);
            tickDuration();
            return;
        }
        if (!this.retrieveCard) {
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                AbstractDungeon.player.masterDeck.addToTop(disCard.makeCopy());
                disCard.setCostForTurn(0);
                disCard.current_x = -1000.0F * Settings.xScale;
                if (AbstractDungeon.player.hand.size() < 10) {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                } else {
                    AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                }
                AbstractDungeon.cardRewardScreen.discoveryCard = null;
            }
            this.retrieveCard = true;
        }
        tickDuration();
    }

    private ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> derp = new ArrayList<>();
        while (derp.size() != 3) {
            AbstractCard.CardRarity cardRarity = AbstractCard.CardRarity.COMMON;
            boolean dupe = false;
            switch (monster.type){
                case NORMAL:
                    cardRarity = AbstractCard.CardRarity.COMMON;
                    break;
                case ELITE:
                    cardRarity = AbstractCard.CardRarity.UNCOMMON;
                    break;
                case BOSS:
                    cardRarity = AbstractCard.CardRarity.RARE;
                    break;
                default:
                    break;
            }
            AbstractCard tmp = CardLibrary.getAnyColorCard(cardRarity);
            for (AbstractCard c : derp) {
                if (c.cardID.equals(tmp.cardID)) {
                    dupe = true;
                    break;
                }
            }
            if (!dupe&&(tmp.type== AbstractCard.CardType.ATTACK||tmp.type== AbstractCard.CardType.POWER)&&tmp.color!= Necromancer.Enums.COLOR_PURPLE&&tmp.cost<3)
                derp.add(tmp.makeCopy());
        }
        return derp;
    }
}
