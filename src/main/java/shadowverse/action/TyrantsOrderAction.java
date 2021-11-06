package shadowverse.action;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

import java.util.Iterator;

public class TyrantsOrderAction extends AbstractGameAction {
    public int[] damage;
    private AbstractCreature target;

    public TyrantsOrderAction(AbstractCreature source, AbstractCreature target, int amount, DamageInfo.DamageType type, AttackEffect effect) {
        this.source = source;
        this.amount = amount;
        this.target = target;
        this.actionType = ActionType.DAMAGE;
        this.damageType = type;
        this.attackEffect = effect;
        this.duration = Settings.ACTION_DUR_FAST;
        this.damage=new int[AbstractDungeon.getCurrRoom().monsters.monsters.size()];
        for (int i = 0; i < AbstractDungeon.getCurrRoom().monsters.monsters.size(); ++i) {
            if (AbstractDungeon.getCurrRoom().monsters.monsters.get(i) != this.target && !AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDeadOrEscaped()) {
                this.damage[i] = Math.min(target.currentHealth, this.amount);
            } else {
                this.damage[i] = 0;
            }
        }

    }

    @Override
    public void update() {
        Iterator var4 = AbstractDungeon.player.powers.iterator();

        while (var4.hasNext()) {
            AbstractPower p = (AbstractPower) var4.next();
            p.onDamageAllEnemies(this.damage);
        }

        int temp = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        for (int i = 0; i < temp; ++i) {
            if (AbstractDungeon.getCurrRoom().monsters.monsters.get(i) != this.target && !AbstractDungeon.getCurrRoom().monsters.monsters.get(i).isDeadOrEscaped()) {
                AbstractDungeon.getCurrRoom().monsters.monsters.get(i).damage(new DamageInfo(this.source, this.damage[i], this.damageType));
            }
        }

        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
        this.isDone = true;
    }
}
