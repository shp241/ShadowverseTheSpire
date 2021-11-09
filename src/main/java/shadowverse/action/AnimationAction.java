package shadowverse.action;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.animation.AbstractAnimation;
import shadowverse.cards.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;
import java.util.Collections;

public class AnimationAction extends AbstractGameAction {
    public AbstractAnimation animation;
    public String animationName;


    public AnimationAction(String atlasUrl, String skeletonUrl, float scale, float positionX, float positionY, String animationName, float duration) {
        this(new AbstractAnimation(atlasUrl, skeletonUrl, scale, positionX, positionY, 0F, 0F), animationName, duration);
    }

    public AnimationAction(AbstractAnimation animation, String animationName, float duration) {
        this.actionType = ActionType.SPECIAL;
        this.animation = animation;
        this.animationName = animationName;
        if (Settings.DISABLE_EFFECTS) {
            this.duration = 0.0F;
        } else {
            this.duration = duration;
            AbstractAnimation.sr.setPremultipliedAlpha(false);
            animation.setVisible(true);
            animation.setMovable(false);
            animation.setAnimation(1, animationName, false);
        }
    }


    @Override
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            animation.setVisible(false);
            this.isDone = true;
        }
    }
}
