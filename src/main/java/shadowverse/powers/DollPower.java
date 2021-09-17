package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class DollPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:DollPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:DollPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int heal;

    public DollPower(AbstractCreature owner, int amount,int heal) {
        this.name = NAME;
        this.ID = "shadowverse:DollPower";
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/DollPower.png");
        this.heal = heal;
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.heal+DESCRIPTIONS[1]+this.heal+DESCRIPTIONS[2];
    }

    public void atStartOfTurnPostDraw() {
        this.amount -= 1;
        if (this.amount == 0){
            flash();
            addToBot((AbstractGameAction)new DrawCardAction(this.heal));
            addToBot((AbstractGameAction)new HealAction(this.owner,this.owner,this.heal));
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
        }
        updateDescription();
    }

}

