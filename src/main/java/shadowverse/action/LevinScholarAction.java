package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;
import java.util.Collections;

public class LevinScholarAction extends AbstractGameAction {
    public static final float DURATION = Settings.ACTION_DUR_MED;
    private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
    private AbstractPlayer p = AbstractDungeon.player;

    public LevinScholarAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
    }


    @Override
    public void update() {
        for (AbstractCard c : p.drawPile.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN)) {
                list.add(c);
            }
        }
        if (list != null && !list.isEmpty()) {
            Collections.shuffle(list);
            this.p.drawPile.moveToHand(list.get(0), this.p.drawPile);
            if (list.size() >= 2) {
                Collections.shuffle(list);
                this.p.drawPile.moveToHand(list.get(1), this.p.drawPile);
            }
        }
        p.hand.refreshHandLayout();
        p.hand.applyPowers();
        this.isDone = true;
    }
}
