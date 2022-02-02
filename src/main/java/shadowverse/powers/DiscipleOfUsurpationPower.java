package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Temp.GildedBlade;
import shadowverse.cards.Temp.GildedBoots;
import shadowverse.cards.Temp.GildedGoblet;
import shadowverse.cards.Temp.GildedNecklace;
import shadowverse.cards.Uncommon.UltimateHollow;
import shadowverse.characters.AbstractShadowversePlayer;


public class DiscipleOfUsurpationPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:DiscipleOfUsurpationPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:DiscipleOfUsurpationPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DiscipleOfUsurpationPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/DiscipleOfUsurpationPower.png");
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof GildedBlade || card instanceof GildedBoots || card instanceof GildedGoblet || card instanceof GildedNecklace || card instanceof UltimateHollow) {
            flash();
            addToBot(new SFXAction("DiscipleOfUsurpation_Pow"));
            addToBot(new GainBlockAction(this.owner,this.amount));
        }
    }

}


 