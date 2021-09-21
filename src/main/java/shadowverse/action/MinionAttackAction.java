package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

public class MinionAttackAction extends AbstractGameAction {
    private DamageInfo info;
    private boolean hitAll;

    public MinionAttackAction(DamageInfo info, boolean hitAll) {
        this.info = info;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.NONE;
        this.hitAll = hitAll;
    }

    @Override
    public void update() {
        if (!this.hitAll) {
            AbstractCreature m = AbstractDungeon.getRandomMonster();
            if (m != null) {
//                float speedTime = 0.1F;
//                if (!AbstractDungeon.player.orbs.isEmpty()) {
//                    speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
//                }
//
//                if (Settings.FAST_MODE) {
//                    speedTime = 0.0F;
//                }
                this.info.output = AbstractOrb.applyLockOn(m, this.info.base);
                this.addToTop(new DamageAction(m, this.info, AttackEffect.NONE, true));
            }
        } else {
            float speedTime = 0.2F / (float) AbstractDungeon.player.orbs.size();
            if (Settings.FAST_MODE) {
                speedTime = 0.0F;
            }
            this.addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(this.info.base, true, true), DamageInfo.DamageType.THORNS, AttackEffect.NONE));
            this.addToBot(new VFXAction(AbstractDungeon.player, new CleaveEffect(), speedTime));
            this.addToTop(new SFXAction("ATTACK_HEAVY"));
        }
        this.isDone = true;
    }
}
