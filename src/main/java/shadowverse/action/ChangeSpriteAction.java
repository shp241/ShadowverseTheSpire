package shadowverse.action;

import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import shadowverse.animation.AbstractAnimation;
import shadowverse.monsters.Naht;

public class ChangeSpriteAction extends AbstractGameAction {

    Naht creature;
    SpriterAnimation to;
    SpriterAnimation ori;

    public ChangeSpriteAction(String path, Naht c, float duration) {
        this.actionType = ActionType.SPECIAL;
        this.duration = duration;
        this.to = new SpriterAnimation(path);
        this.creature = c;
        this.ori = creature.getAnimation();
        creature.setAnimation(to);
    }


    @Override
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            creature.setAnimation(ori);
            this.isDone = true;
        }
    }
}
