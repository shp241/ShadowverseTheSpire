package shadowverse.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;

public class SadisticDemonPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:SadisticDemonPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:SadisticDemonPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractPlayer p = AbstractDungeon.player;

    public SadisticDemonPower(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/SadisticDemonPower.png");
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (info.owner == this.owner) {
            flash();
            addToBot((AbstractGameAction)new SFXAction("SadisticDemonPower"));
            addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer) {
            flash();
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClawEffect(p.hb.cX, p.hb.cY, Color.SCARLET, Color.ORANGE), 0.1F));
            addToBot((AbstractGameAction)new SFXAction("SadisticDamage"));
            addToBot((AbstractGameAction)new LoseHPAction(p,p,1));
        }
    }

    @Override
    public void atStartOfTurn() {
        flash();
        addToBot((AbstractGameAction)new SFXAction("SadisticHeal"));
        addToBot((AbstractGameAction)new HealAction(this.owner,this.owner,2));
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

}
