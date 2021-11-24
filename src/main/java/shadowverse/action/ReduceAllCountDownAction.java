package shadowverse.action;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.orbs.AmuletOrb;

public class ReduceAllCountDownAction extends AbstractGameAction {
    public ReduceAllCountDownAction(int amount) {
        this.amount = amount;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == this.startDuration && AbstractDungeon.player.orbs.size() > 0) {
            for (AbstractOrb orb : AbstractDungeon.player.orbs) {
                if (orb instanceof AmuletOrb){
                    if (!(((AmuletOrb) orb).amulet instanceof AbstractNoCountDownAmulet)){
                        for (int i=0;i<this.amount;i++){
                            if (orb.passiveAmount > 0) {
                                orb.passiveAmount--;
                                orb.evokeAmount--;
                                orb.updateDescription();
                            }
                            for (int j = 0; j < 10; j++)
                                AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(orb.tX, orb.tY, Color.YELLOW));
                            if (orb.passiveAmount <= 0){
                                AbstractDungeon.actionManager.addToTop((AbstractGameAction) new StasisEvokeIfRoomInHandAction((AmuletOrb) orb));
                                break;
                            }
                        }
                    }
                }
            }
        }
        tickDuration();
    }
}
