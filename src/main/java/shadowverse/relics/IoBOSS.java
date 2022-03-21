package shadowverse.relics;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import shadowverse.patch.CharacterSelectScreenPatches;

import java.util.ArrayList;


public class IoBOSS
        extends CustomRelic{
    public static final String ID = "shadowverse:IoBOSS";
    public static final String IMG = "img/relics/IoBOSS.png";
    public static final String OUTLINE_IMG = "img/relics/outline/IriaBOSS_Outline.png";
    private AbstractPlayer p = AbstractDungeon.player;
    private AbstractCard theCard = null;

    public IoBOSS() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        if (!this.grayscale && !m.hasPower(MinionPower.POWER_ID)) {
            this.grayscale = true;
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            ArrayList<AbstractCard> possibleCards = new ArrayList<>();
            for (AbstractCard c : p.masterDeck.group) {
                if (c.canUpgrade())
                    possibleCards.add(c);
            }
            if (!possibleCards.isEmpty()) {
                this.theCard = possibleCards.get(AbstractDungeon.miscRng.random(0, possibleCards.size() - 1));
                this.theCard.upgrade();
                p.bottledCardUpgradeCheck(this.theCard);
                if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();
                AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(this.theCard.makeStatEquivalentCopy()));
            }
        }
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(Offensive4.ID)) {
            instantObtain(AbstractDungeon.player, 0, false);
        } else {
            super.obtain();
        }
    }

    @Override
    public void atBattleStart() {
        this.grayscale = false;
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(Offensive4.ID)
                && (CharacterSelectScreenPatches.characters[4]).reskinCount == 1;
    }


    public AbstractRelic makeCopy() {
        return (AbstractRelic) new IoBOSS();
    }
}


