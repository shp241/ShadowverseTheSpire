package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.Collections;

public class NephthysAction extends AbstractGameAction {
    public static final float DURATION = Settings.ACTION_DUR_MED;
    private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
    private ArrayList<Integer> costTmp = new ArrayList<>();
    private ArrayList<AbstractCard> finalList = new ArrayList<AbstractCard>();
    private AbstractPlayer p = AbstractDungeon.player;

    public NephthysAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
    }


    @Override
    public void update() {
        for (AbstractCard c : p.drawPile.group) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                list.add(c);
            }
        }
        if (list != null && list.size() != 0) {
            for (AbstractCard c : list) {
                costTmp.add(c.cost);
            }
            Collections.shuffle(list);
            for (Integer cost:costTmp){
                for (AbstractCard card:list){
                    if (cost==card.cost){
                        finalList.add(card);
                        break;
                    }
                }
            }
            for (AbstractCard c:finalList){
                AbstractDungeon.actionManager.cardsPlayedThisCombat.add(c);
                addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,this.p.drawPile));
            }
        }
        p.hand.refreshHandLayout();
        p.hand.applyPowers();
        this.isDone = true;
    }
}
