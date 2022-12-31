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
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class GenomuelPower extends TwoAmountPower {
    public static final String POWER_ID = "shadowverse:GenomuelPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:GenomuelPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public GenomuelPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.amount2 = 3;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/GenomuelPower.png");
    }

    @Override
    public void atStartOfTurn() {
        this.amount2--;
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
            if (mo!=null && !mo.isDeadOrEscaped() && !mo.isDying && !mo.halfDead){
                addToBot(new ApplyPowerAction(mo, this.owner, new StrengthPower(mo, -3), -3, true, AbstractGameAction.AttackEffect.NONE));
                addToBot(new ApplyPowerAction(mo, this.owner, new DexterityPower(mo, -3), -3, true, AbstractGameAction.AttackEffect.NONE));
                mo.decreaseMaxHealth(9);
            }
        }
        if (this.amount2<=0){
            addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
        }
        updateDescription();
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1];
    }
}
