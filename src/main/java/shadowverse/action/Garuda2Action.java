package shadowverse.action;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.orbs.AmuletOrb;

public class Garuda2Action extends AbstractGameAction {
    private int dmg;

    public Garuda2Action(int amount,int dmg) {
        this.amount = amount;
        this.dmg = dmg;
        this.actionType = ActionType.DAMAGE;
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
                                addToBot((AbstractGameAction)new SFXAction("Garuda2Power"));
                                addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.dmg, DamageInfo.DamageType.NORMAL), AttackEffect.SLASH_HORIZONTAL));
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
