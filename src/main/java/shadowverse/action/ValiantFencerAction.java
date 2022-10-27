package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static shadowverse.characters.AbstractShadowversePlayer.Enums.HERO;

public class ValiantFencerAction extends AbstractGameAction {

    public ValiantFencerAction() {
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractCard c : p.hand.group) {
            if (c.hasTag(HERO)) {
                c.costForTurn = 0;
                c.isCostModified = true;
            }
        }
        this.tickDuration();
    }
}