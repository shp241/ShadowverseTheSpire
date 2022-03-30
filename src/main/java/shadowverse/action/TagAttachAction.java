package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class TagAttachAction extends AbstractGameAction {

    String text;
    AbstractCard.CardTags tags;

    public TagAttachAction(AbstractCard.CardTags tags,String text){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.tags = tags;
        this.text = text;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            AbstractPlayer p = AbstractDungeon.player;
            upgradeAllCardsInGroup(p.hand);
            this.isDone = true;
        }
    }

    private void upgradeAllCardsInGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c.type== AbstractCard.CardType.ATTACK){
                if (cardGroup.type == CardGroup.CardGroupType.HAND)
                    c.superFlash();
                if (!c.hasTag(tags)){
                    c.tags.add(tags);
                    c.rawDescription += " NL "+text+" 。";
                    c.initializeDescription();
                    c.applyPowers();
                }
            }
        }
    }
}
