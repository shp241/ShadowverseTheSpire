package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.orbs.Minion;

import java.util.Objects;

public class MinionOrderAction extends AbstractGameAction {

    private String orbID;

    public MinionOrderAction() {
        this.actionType = ActionType.SPECIAL;
        this.attackEffect = AttackEffect.NONE;
        this.orbID = "";
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
            if (p.orbs.get(i) instanceof Minion) {
                if (Objects.equals(this.orbID, "") || Objects.equals(p.orbs.get(i).ID,this.orbID)) {
                    ((Minion) p.orbs.get(i)).order();
                    break;
                }
            }
        }
        this.isDone = true;
    }
}