package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.orbs.Minion;

public class MinionOrderAction extends AbstractGameAction {

    private String orbID = null;

    public MinionOrderAction() {
        this.actionType = ActionType.SPECIAL;
        this.attackEffect = AttackEffect.NONE;
    }

    public MinionOrderAction(String orbID) {
        this.actionType = ActionType.SPECIAL;
        this.attackEffect = AttackEffect.NONE;
        this.orbID = orbID;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        for (int i = 0; i < p.orbs.size(); i++) {
            if (p.orbs.get(i) instanceof Minion && (this.orbID == null || p.orbs.get(i).ID.equals(this.orbID))) {
                ((Minion) p.orbs.get(i)).order();
                break;
            }
        }
        this.isDone = true;
    }
}