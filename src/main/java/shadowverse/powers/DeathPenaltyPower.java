package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

public class DeathPenaltyPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:DeathPenaltyPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:DeathPenaltyPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;


    public DeathPenaltyPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.DEBUFF;
        this.img = new Texture("img/powers/ICPower.png");
        updateDescription();
    }


    @Override
    public void onDeath(){
        AbstractDungeon.effectList.add(new RainingGoldEffect(10));
        AbstractDungeon.player.gainGold(10);
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
