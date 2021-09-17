package shadowverse.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class VengeanceHealthPower extends AbstractPower implements HealthBarRenderPower {
    public static final String POWER_ID = "shadowverse:VengeanceHealthPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:VengeanceHealthPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int half;
    private int healthToReturn;

    public VengeanceHealthPower(AbstractCreature owner,int half,int currentHp) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.half = half;
        this.amount = -1;
        if (currentHp>half){
            this.healthToReturn = currentHp-half;
        }else {
            this.healthToReturn = 0;
        }
        this.type = NeutralPowertypePatch.NEUTRAL;
        updateDescription();
        this.img = new Texture("img/powers/VengeanceHealthPower.png");
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
    public void onVictory(){
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0){
            p.currentHealth+=this.healthToReturn;
            p.update();
        }
    }

    @Override
    public Color getColor() {
        return Color.GOLD;
    }
}
