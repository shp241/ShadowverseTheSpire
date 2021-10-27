package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Status.EvolutionPoint;

public class OliviasBlessing extends CustomRelic {
    public static final String ID = "shadowverse:OliviasBlessing";
    public static final String IMG = "img/relics/OliviasBlessing.png";
    public static final String OUTLINE_IMG = "img/relics/outline/OliviasBlessing_Outline.png";
    public boolean lifeCheck;

    public OliviasBlessing() {
        super("shadowverse:OliviasBlessing", ImageMaster.loadImage("img/relics/OliviasBlessing.png"), RelicTier.BOSS, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public void atTurnStart() {
        if (!this.grayscale) {
            this.counter++;
            flash();
            AbstractCard c = new Miracle();
            addToBot(new MakeTempCardInHandAction(c.makeStatEquivalentCopy(), 1));
            c = new EvolutionPoint();
            addToBot(new MakeTempCardInHandAction(c.makeStatEquivalentCopy(), 1));
        }
        if (this.counter == 3) {
            flash();
            this.counter = -1;
            this.grayscale = true;
        }
    }


    @Override
    public void onVictory() {
        this.counter = -1;
        this.grayscale = false;
    }


    @Override
    public AbstractRelic makeCopy() {
        return new OliviasBlessing();
    }
}
