package shadowverse.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;

public class BenevolentElfPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:BenevolentElfPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:BenevolentElfPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public BenevolentElfPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/BenevolentElfPower.png");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            addToBot(new SFXAction("BenevolentElfPower"));
            addToBot(new VFXAction( new MiracleEffect(Color.SKY.cpy(), Color.WHITE.cpy(), "HEAL_3")));
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
                if (mo!=null && !mo.isDeadOrEscaped() && !mo.isDying && !mo.halfDead){
                    if (this.owner.currentHealth == this.owner.maxHealth){
                        addToBot(new JudgementAction(mo, 18));
                    }else{
                        addToBot(new JudgementAction(mo, 6));
                    }
                }
            }
            addToBot(new RemoveSpecificPowerAction(this.owner,this.owner,this));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
