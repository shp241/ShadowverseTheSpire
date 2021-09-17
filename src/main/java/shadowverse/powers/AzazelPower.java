package shadowverse.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AzazelPower extends AbstractPower implements HealthBarRenderPower {
    public static final String POWER_ID = "shadowverse:AzazelPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:AzazelPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int half;

    public AzazelPower(AbstractCreature owner, int half) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.half = half;
        this.amount = -1;
        this.type = NeutralPowertypePatch.NEUTRAL;
        updateDescription();
        this.img = new Texture("img/powers/AzazelPower.png");
    }

    @Override
    public int onHeal(int healAmount){
        if (this.owner.currentHealth+healAmount>half)
            return half-this.owner.currentHealth;
        return healAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public int getHealthBarAmount() {
        return this.half;
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
        if (damage>half*3/10){
            return half*3/10;
        }
        return damage;
    }

    @Override
    public Color getColor() {
        return Color.DARK_GRAY;
    }
}
