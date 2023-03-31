package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class ElluviaPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:ElluviaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ElluviaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ElluviaPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/ElluviaPower.png");
    }

    @Override
    public int onHeal(int healAmount) {
        addToBot(new ApplyPowerAction(this.owner,this.owner,new StrengthPower(this.owner,1),1));
        addToBot(new ApplyPowerAction(this.owner,this.owner,new DexterityPower(this.owner,1),1));
        return healAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
