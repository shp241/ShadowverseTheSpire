package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;
import java.util.Collections;

public class GenerateNineAction extends AbstractGameAction {
    public static final float DURATION = Settings.ACTION_DUR_MED;
    private ArrayList<AbstractCard> list = new ArrayList<AbstractCard>();
    private ArrayList<String> nameTmp = new ArrayList<>();
    private ArrayList<AbstractCard> finalList = new ArrayList<AbstractCard>();
    private AbstractPlayer p = AbstractDungeon.player;

    public GenerateNineAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = DURATION;
    }


    @Override
    public void update() {
        for (AbstractCard c : p.drawPile.group) {
            if (c.type == AbstractCard.CardType.ATTACK&&c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)) {
                list.add(c);
            }
        }
        if (list != null && list.size() != 0) {
            Collections.shuffle(list);
            for (AbstractCard c : list) {
                if (!nameTmp.contains(c.cardID) && finalList.size()<10-p.hand.group.size()){
                    nameTmp.add(c.cardID);
                    finalList.add(c);
                }
            }
            for (AbstractCard tempCard:finalList){
                if (tempCard.costForTurn > 0) {
                    tempCard.costForTurn = 0;
                    tempCard.isCostModifiedForTurn = true;
                    this.p.drawPile.moveToHand(tempCard, this.p.drawPile);
                }
            }
        }
        p.hand.refreshHandLayout();
        p.hand.applyPowers();
        this.isDone = true;
    }
}
