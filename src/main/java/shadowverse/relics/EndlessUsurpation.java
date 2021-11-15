package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Temp.GildedBlade;
import shadowverse.cards.Temp.GildedBoots;
import shadowverse.cards.Temp.GildedGoblet;
import shadowverse.cards.Temp.GildedNecklace;

import java.util.ArrayList;

public class EndlessUsurpation extends CustomRelic {
    public static final String ID = "shadowverse:EndlessUsurpation";
    public static final String IMG = "img/relics/EndlessUsurpation.png";
    public static final String OUTLINE_IMG = "img/relics/outline/EndlessUsurpation_Outline.png";


    public EndlessUsurpation() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.COMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new GildedBlade());
        list.add(new GildedNecklace());
        list.add(new GildedGoblet());
        list.add(new GildedBoots());
        return list;
    }

    @Override
    public void atTurnStart() {
        AbstractPlayer p = AbstractDungeon.player;
        int r1 = AbstractDungeon.cardRandomRng.random(3);
        AbstractCard c1 = returnProphecy().get(r1);
        addToBot(new MakeTempCardInHandAction(c1));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new EndlessUsurpation();
    }
}



