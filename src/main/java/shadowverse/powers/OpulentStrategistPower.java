package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.MinionBuffAction;

public class OpulentStrategistPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:OpulentStrategistPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:OpulentStrategistPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public OpulentStrategistPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/OpulentStrategistPower.png");
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount > 3) {
            if (this.amount % 3 == 0) {
                this.amount = 3;
            } else {
                this.amount = this.amount % 3;
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[this.amount % 3] + DESCRIPTIONS[3] + DESCRIPTIONS[4];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.SKILL) {
            flash();
            addToBot(new SFXAction("OpulentStrategist_Pow"));
            if (this.amount % 3 == 1) {
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 3));
            } else if (this.amount % 3 == 2) {
                addToBot(new DrawCardAction(1));
            } else {
                AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(1, 1, true));
            }
            this.amount += 1;
            if (this.amount > 3) {
                if (this.amount % 3 == 0) {
                    this.amount = 3;
                } else {
                    this.amount = this.amount % 3;
                }
            }
            updateDescription();
        }
    }

}


