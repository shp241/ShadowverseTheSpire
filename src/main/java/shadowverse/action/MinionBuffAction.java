package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.orbs.Minion;

public class MinionBuffAction extends AbstractGameAction {

    public int a;
    public int d;

    public MinionBuffAction(int a, int d) {
        this.actionType = ActionType.SPECIAL;
        this.attackEffect = AttackEffect.NONE;
        this.a = a;
        this.d = d;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.orbs.get(0) instanceof Minion) {
            ((Minion) p.orbs.get(0)).buff(a, d);
        }
        this.isDone = true;
    }
}
