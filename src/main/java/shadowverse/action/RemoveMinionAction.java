package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import shadowverse.orbs.Minion;

import java.util.Collections;

public class RemoveMinionAction extends AbstractGameAction {
    public RemoveMinionAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (!p.orbs.isEmpty()) {
            for (int n = 0; n < p.orbs.size(); n++) {
                if ((p.orbs.get(n) instanceof Minion) && ((Minion) p.orbs.get(n)).defense <= 0) {
                    ((Minion) p.orbs.get(n)).onRemove();
                    AbstractOrb orbSlot = new EmptyOrbSlot((p.orbs.get(0)).cX, p.orbs.get(0).cY);
                    int i;
                    for (i = 1; i < p.orbs.size(); ++i) {
                        Collections.swap(p.orbs, i, i - 1);
                    }
                    p.orbs.set(p.orbs.size() - 1, orbSlot);
                    for (i = 0; i < p.orbs.size(); ++i) {
                        p.orbs.get(i).setSlot(i, p.maxOrbs);
                    }
                }
            }
        }
        this.isDone = true;
    }
}
