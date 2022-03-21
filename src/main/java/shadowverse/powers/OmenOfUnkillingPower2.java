package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class OmenOfUnkillingPower2 extends TwoAmountPower {
    public static final String POWER_ID = "shadowverse:OmenOfUnkillingPower2";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:OmenOfUnkillingPower2");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public OmenOfUnkillingPower2(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.amount2 = 3;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/OmenOfUnkillingPower2.png");
    }


    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            this.amount2--;
            AbstractMonster mo = AbstractDungeon.getRandomMonster();
            if (!mo.isDeadOrEscaped() && !mo.isDying && !mo.halfDead) {
                addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) this.owner, (AbstractPower) new StrengthPower((AbstractCreature) mo, -6), -6, true, AbstractGameAction.AttackEffect.NONE));
                if (!mo.hasPower(ArtifactPower.POWER_ID))
                    addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) mo, (AbstractCreature) this.owner, (AbstractPower) new GainStrengthPower((AbstractCreature) mo, 6), 6));
            }
            if (this.amount2 <= 0) {
                addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
            updateDescription();
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1];
    }
}
