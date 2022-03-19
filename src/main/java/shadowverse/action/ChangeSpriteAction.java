package shadowverse.action;

import basemod.animations.SpriterAnimation;
import charbosses.bosses.KMR.KMR;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import shadowverse.Shadowverse;
import shadowverse.animation.AbstractAnimation;
import shadowverse.monsters.Naht;
import shadowverse.monsters.SpriteCreature;

import java.util.ArrayList;
import java.util.List;

public class ChangeSpriteAction extends AbstractGameAction {

    SpriteCreature creature;
    SpriterAnimation to;
    SpriterAnimation ori;

    public ChangeSpriteAction(SpriterAnimation to, SpriteCreature c, float duration) {
        this.actionType = ActionType.SPECIAL;
        this.duration = duration;
        if (c instanceof KMR){
            to.setFlip(true,false);
        }
        this.to = to;
        this.creature = c;
        this.ori = creature.getAnimation();
        creature.setAnimation(to);
    }

    @Override
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            creature.setAnimation(ori);
            this.to = null;
            System.gc();
            this.isDone = true;
        }
    }
}
