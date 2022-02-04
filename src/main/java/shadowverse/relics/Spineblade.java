package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Temp.GildedBlade;
import shadowverse.cards.Temp.GildedBoots;
import shadowverse.cards.Temp.GildedGoblet;
import shadowverse.cards.Temp.GildedNecklace;
import shadowverse.cards.Uncommon.UltimateHollow;

public class Spineblade extends CustomRelic {
    public static final String ID = "shadowverse:Spineblade";
    public static final String IMG = "img/relics/Spineblade.png";
    public static final String OUTLINE_IMG = "img/relics/outline/Spineblade_Outline.png";

    public Spineblade() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof GildedBlade || card instanceof GildedBoots || card instanceof GildedGoblet || card instanceof GildedNecklace || card instanceof UltimateHollow) {
            addToBot(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }


    @Override
    public AbstractRelic makeCopy() {
        return new Spineblade();
    }
}
