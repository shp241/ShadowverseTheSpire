package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class Wilbert2Power extends AbstractPower {
    public static final String POWER_ID = "shadowverse:Wilbert2Power";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:Wilbert2Power");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public Wilbert2Power(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/Wilbert2Power.png");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            int dmg = this.owner.currentBlock;
            if (dmg>50)
                dmg = 50;
            addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(dmg, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
