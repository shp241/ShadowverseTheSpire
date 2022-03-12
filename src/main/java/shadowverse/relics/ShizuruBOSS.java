package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.patch.CharacterSelectScreenPatches;

public class ShizuruBOSS extends CustomRelic {
    public static final String ID = "shadowverse:ShizuruBOSS";
    public static final String IMG = "img/relics/ShizuruBOSS.png";
    public static final String OUTLINE_IMG = "img/relics/outline/IriaBOSS_Outline.png";
    private boolean triggered = false;

    public ShizuruBOSS() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.BOSS, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.upgraded){
            addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,1));
        }
        if (c instanceof EvolutionPoint && !triggered){
            flash();
            addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
            addToBot((AbstractGameAction)new GainEnergyAction(1));
            triggered = true;
        }
    }

    @Override
    public void atTurnStart() {
        triggered = false;
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(Offensive6.ID)) {
            instantObtain(AbstractDungeon.player, 0, false);
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn(){
        return AbstractDungeon.player.hasRelic(Offensive6.ID)
                && (CharacterSelectScreenPatches.characters[3]).reskinCount == 1;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ShizuruBOSS();
    }
}
