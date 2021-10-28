package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import shadowverse.orbs.Minion;

import java.util.ArrayList;
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
                    AbstractOrb orbSlot = new EmptyOrbSlot((p.orbs.get(n)).cX, p.orbs.get(n).cY);
                    p.orbs.set(n, orbSlot);
                }
            }
            for (int n = 0; n < p.orbs.size(); n++) {
                if (p.orbs.get(n) instanceof EmptyOrbSlot) {
                    for (int i = n; i < p.orbs.size(); i++) {
                        if (!(p.orbs.get(i) instanceof EmptyOrbSlot)) {
                            Collections.swap(p.orbs, i, n);
                            break;
                        }
                    }
                }
            }
            for (int n = 0; n < p.orbs.size(); n++) {
                p.orbs.get(n).setSlot(n, p.maxOrbs);
            }
        }
        this.isDone = true;
    }
}
