package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class GremoryUsedPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:GremoryUsedPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:GremoryUsedPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GremoryUsedPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/GremoryPower.png");
    }


    @Override
    public void atStartOfTurn(){
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
        addToBot((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,(AbstractPower)new GremoryPower(this.owner)));
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
