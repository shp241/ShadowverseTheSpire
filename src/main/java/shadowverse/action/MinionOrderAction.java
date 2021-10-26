package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.orbs.Minion;

public class MinionOrderAction extends AbstractGameAction {

    public MinionOrderAction() {
        this.actionType = ActionType.SPECIAL;
        this.attackEffect = AttackEffect.NONE;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        for (int i = 0; i < p.orbs.size(); i++) {
            if (p.orbs.get(i) instanceof Minion) {
                ((Minion) p.orbs.get(0)).order();
                break;
            }
        }
        this.isDone = true;
    }
}