package shadowverse.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class AgentOfTheCommandmentsPower extends TwoAmountPower {
    public static final String POWER_ID = "shadowverse:AgentOfTheCommandmentsPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:AgentOfTheCommandmentsPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AgentOfTheCommandmentsPower(AbstractCreature owner,int amount,int amount2) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.amount2 = amount2;
        this.type = PowerType.BUFF;
        updateDescription();
        loadRegion("regen");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            this.amount2--;
            addToBot(new HealAction(this.owner,this.owner,this.amount));
            if (this.amount2<=0){
                addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
            }
            updateDescription();
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
