package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RaveningPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:RaveningPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:RaveningPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int drawAmt;
    private boolean upgrade;

    public RaveningPower(AbstractCreature owner, boolean upgrade) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.upgrade = upgrade;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/RaveningPower.png");
    }


    public void updateDescription() {
        if (upgrade) {
            this.description = DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0];
        }
    }

    public void onCardDraw(AbstractCard card) {
        if (AbstractDungeon.player.hasPower(AvaricePower.POWER_ID))
            addToBot((AbstractGameAction) new DamageRandomEnemyAction(new DamageInfo(this.owner, upgrade ? 4 : 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

}

