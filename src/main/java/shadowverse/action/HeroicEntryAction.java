package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import java.util.ArrayList;
import java.util.Iterator;

import static shadowverse.characters.AbstractShadowversePlayer.Enums.HERO;

public class HeroicEntryAction extends AbstractGameAction {
    private static final String TEXT = CardCrawlGame.languagePack.getUIString("shadowverse:Retain").TEXT[0];
    boolean upgraded;
    boolean inDanger;


    public HeroicEntryAction(boolean upgraded, boolean inDanger) {
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FAST;
        this.upgraded = upgraded;
        this.inDanger = inDanger;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        int s = 1;
        for (AbstractCard c : p.hand.group) {
            if (c.hasTag(HERO)) {
                s++;
            }
        }
        for (int i = 0; i < s; i++) {
            addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
            addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(4, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
        }
        if (upgraded) {
            for (AbstractCard c : p.hand.group) {
                if (c.hasTag(HERO) && !c.retain && !c.selfRetain) {
                    c.retain = true;
                    c.rawDescription += " NL " + TEXT;
                    c.initializeDescription();
                    c.applyPowers();
                }
            }
        }
        this.tickDuration();
    }

}