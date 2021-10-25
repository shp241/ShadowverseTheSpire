package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.orbs.Minion;

public class MinionBuffAction extends AbstractGameAction {

    public int a;
    public int d;
    public boolean all;

    public MinionBuffAction(int a, int d) {
        new MinionBuffAction(a, d, false);
    }

    public MinionBuffAction(int a, int d, boolean all) {
        this.actionType = ActionType.SPECIAL;
        this.attackEffect = AttackEffect.NONE;
        this.a = a;
        this.d = d;
        this.all = all;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (this.all) {
            for (int i = 0; i < p.orbs.size(); i++) {
                if (p.orbs.get(i) instanceof Minion) {
                    ((Minion) p.orbs.get(i)).buff(a, d);
                }
            }
        } else {
            for (int i = 0; i < p.orbs.size(); i++) {
                if (p.orbs.get(i) instanceof Minion) {
                    ((Minion) p.orbs.get(i)).buff(a, d);
                    break;
                }
            }
        }
        this.isDone = true;
    }
}
