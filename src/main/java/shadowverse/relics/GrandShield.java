package shadowverse.relics;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Temp.HolyCavalier;


public class GrandShield
        extends CustomRelic {
    public static final String ID = "shadowverse:GrandShield";
    public static final String IMG = "img/relics/GrandShield.png";
    public static final String OUTLINE_IMG = "img/relics/outline/GrandShield_Outline.png";

    public GrandShield() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public void atTurnStart() {
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(new HolyCavalier()));
    }

    public AbstractRelic makeCopy() {
        return (AbstractRelic) new GrandShield();
    }
}

