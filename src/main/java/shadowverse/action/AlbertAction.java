package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Rare.Albert;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlbertAction  extends AbstractGameAction {
    public static final float DURATION = Settings.ACTION_DUR_MED;
    private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
    private ArrayList<String> nameTmp = new ArrayList<>();
    private ArrayList<AbstractCard> finalList = new ArrayList<AbstractCard>();
    private AbstractPlayer p = AbstractDungeon.player;

    public AlbertAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
    }


    @Override
    public void update() {
        List<AbstractCard> temp = new ArrayList<>();
        for (AbstractCard c : p.drawPile.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN) && !(c instanceof Albert)) {
                temp.add(c);
            }
        }
        for (AbstractCard c : temp) {
            if (p.hand.size() < 10) {
                c.freeToPlayOnce = true;
                p.drawPile.moveToHand(c, p.drawPile);
            }
        }
        temp = new ArrayList<>();
        for (AbstractCard c : p.discardPile.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN) && !(c instanceof Albert)) {
                temp.add(c);
            }
        }
        for (AbstractCard c : temp) {
            if (p.hand.size() < 10) {
                c.freeToPlayOnce = true;
                p.drawPile.moveToHand(c, p.drawPile);
            }
        }
        p.hand.refreshHandLayout();
        p.hand.applyPowers();
        this.isDone = true;
    }
}

