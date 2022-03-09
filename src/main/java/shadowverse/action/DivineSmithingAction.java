package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DivineSmithingAction extends AbstractGameAction {
    private boolean enhance;
    private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Exhaust").TEXT[0];

    public DivineSmithingAction(boolean enhance){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.enhance = enhance;
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
            if (c.cost==0&&c.type== AbstractCard.CardType.ATTACK) {
                if (cardGroup.type == CardGroup.CardGroupType.HAND)
                    c.superFlash();
                if (this.enhance){
                    c.retain= true;
                    c.selfRetain = true;
                    c.rawDescription += " NL "+TEXT+" 。";
                    c.initializeDescription();
                }
                if (c.canUpgrade()){
                    c.upgrade();
                    c.applyPowers();
                }
            }
        }
    }
}
