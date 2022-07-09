package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.Temp.Koko;
import shadowverse.cards.Temp.Mimi;

import java.util.ArrayList;

public class CerberusAction
        extends AbstractGameAction {

    public CerberusAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        addToBot(new MakeTempCardInHandAction(new Mimi()));
        addToBot(new MakeTempCardInHandAction(new Koko()));
        this.isDone = true;
    }
}


