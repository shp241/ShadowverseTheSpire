package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CradlePower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:CradlePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:CradlePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CradlePower(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/CradlePower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void atStartOfTurnPostDraw() {
        flash();
        addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)this.owner,this.amount/2));
        addToBot((AbstractGameAction)new DiscardAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, this.amount, false));
        addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(this.owner, this.amount*5, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount/2+DESCRIPTIONS[1]+this.amount+DESCRIPTIONS[2]+this.amount*5+DESCRIPTIONS[3];
    }
}
