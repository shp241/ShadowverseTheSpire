package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


public class FairyCirclePower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:FairyCirclePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:FairyCirclePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public FairyCirclePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        if (this.amount >= 5)
            this.amount = 5;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/FairyCirclePower.png");
        this.priority = 6;
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 5)
            this.amount = 5;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }


    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
            count++;
        }
        if (count >= 5) {
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) this.owner, (AbstractCreature) this.owner, (AbstractPower) new FairyCirclePower((AbstractCreature) this.owner, 1), 1));
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        switch (this.amount){
            case 1:
                return damage*1.1F;
            case 2:
                return damage*1.2F;
            case 3:
                return damage*1.3F;
            case 4:
                return damage*1.4F;
            case 5:
                return damage*1.5F;
            default:break;
        }
        return damage ;
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        switch (this.amount){
            case 1:
                return damage*0.9F;
            case 2:
                return damage*0.8F;
            case 3:
                return damage*0.7F;
            case 4:
                return damage*0.6F;
            case 5:
                return damage*0.5F;
            default:break;
        }
        return damage;
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount>0)
            this.amount -= 1;
        if (this.amount < 0)
            this.amount = 0;
        updateDescription();
        return damageAmount;
    }

}

