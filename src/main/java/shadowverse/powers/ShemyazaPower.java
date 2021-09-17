package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class ShemyazaPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:ShemyazaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ShemyazaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ShemyazaPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/ShemyazaPower.png");
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void atStartOfTurnPostDraw() {
        flash();
        addToBot((AbstractGameAction) new DrawCardAction(3));
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer) {
            flash();
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                    addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
            }
        }
    }
}

