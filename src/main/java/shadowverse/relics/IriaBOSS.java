package shadowverse.relics;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.patch.CharacterSelectScreenPatches;


public class IriaBOSS
        extends CustomRelic {
    public static final String ID = "shadowverse:IriaBOSS";
    public static final String IMG = "img/relics/IriaBOSS.png";
    public static final String OUTLINE_IMG = "img/relics/outline/IriaBOSS_Outline.png";
    private AbstractPlayer p = AbstractDungeon.player;

    public IriaBOSS() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atTurnStart() {
        addToBot((AbstractGameAction) new LoseHPAction(p, p, 2));
    }

    @Override
    public void onPlayerEndTurn() {
        addToBot((AbstractGameAction)new HealAction(p,p,4));
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
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(Offensive4.ID)
                &&(CharacterSelectScreenPatches.characters[4]).reskinCount == 0;
    }


    public AbstractRelic makeCopy() {
        return (AbstractRelic) new IriaBOSS();
    }
}


