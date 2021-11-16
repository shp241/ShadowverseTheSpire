package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.orbs.Minion;

public class LeonidasWaitPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:LeonidasWaitPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:LeonidasWaitPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LeonidasWaitPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "shadowverse:LeonidasWaitPower";
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/LeonidasWaitPower.png");
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        this.amount -= 1;
        if (this.amount == 0) {
            flash();
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new LeonidasPower(this.owner)));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new LeonidasBuffPower(this.owner,1),1));
        }
        updateDescription();
    }
}

