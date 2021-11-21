package shadowverse.action;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.orbs.AmuletOrb;

public class StasisEvokeIfRoomInHandAction extends AbstractGameAction {
    private AmuletOrb orb;

    public StasisEvokeIfRoomInHandAction(AmuletOrb orb) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.orb = orb;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        AbstractDungeon.player.orbs.remove(this.orb);
        AbstractDungeon.player.orbs.add(0, this.orb);
        AbstractDungeon.player.evokeOrb();
        this.isDone = true;
    }
}