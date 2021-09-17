package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.characters.AbstractShadowversePlayer;

public class FirstOneAction extends AbstractGameAction {

    public FirstOneAction(){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractPlayer p = AbstractDungeon.player;
            upgradeAllCardsInGroup(p.hand);
            upgradeAllCardsInGroup(p.drawPile);
            upgradeAllCardsInGroup(p.discardPile);
            upgradeAllCardsInGroup(p.exhaustPile);
            this.isDone = true;
        }
    }

    private void upgradeAllCardsInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c.type== AbstractCard.CardType.ATTACK&&c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)) {
                if (cardGroup.type == CardGroup.CardGroupType.HAND)
                    c.superFlash();
                if (c.canUpgrade()){
                    c.upgrade();
                    c.applyPowers();
                }
            }
        }
    }
}
