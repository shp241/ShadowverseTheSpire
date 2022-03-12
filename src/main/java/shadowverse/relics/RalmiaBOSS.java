package shadowverse.relics;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.stances.AbstractStance;
import shadowverse.action.TagAttachAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.patch.CharacterSelectScreenPatches;
import shadowverse.stance.Resonance;


public class RalmiaBOSS
        extends CustomRelic {
    public static final String ID = "shadowverse:RalmiaBOSS";
    public static final String IMG = "img/relics/RalmiaBOSS.png";
    public static final String OUTLINE_IMG = "img/relics/outline/RalmiaBOSS_Outline.png";
    private static final String TEXT1 = CardCrawlGame.languagePack.getUIString("shadowverse:ArtifactTag").TEXT[0];
    private static final String TEXT2 = CardCrawlGame.languagePack.getUIString("shadowverse:MachineTag").TEXT[0];
    private boolean trigger = false;

    public RalmiaBOSS() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if (!trigger){
            if (newStance.ID.equals(Resonance.STANCE_ID)) {
                addToBot((AbstractGameAction) new TagAttachAction(AbstractShadowversePlayer.Enums.ARTIFACT, TEXT1));
            } else if (prevStance.ID.equals(Resonance.STANCE_ID)) {
                addToBot((AbstractGameAction) new TagAttachAction(AbstractShadowversePlayer.Enums.ARTIFACT, TEXT2));
            }
            trigger = true;
        }
    }

    @Override
    public void onPlayerEndTurn() {
        trigger = false;
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(Offensive5.ID)) {
            instantObtain(AbstractDungeon.player, 0, false);
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(Offensive5.ID)
                && (CharacterSelectScreenPatches.characters[6]).reskinCount == 1;
    }

    public AbstractRelic makeCopy() {
        return (AbstractRelic) new RalmiaBOSS();
    }
}


