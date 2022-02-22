package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.MinionSummonAction;
import shadowverse.cards.Rare.LightOfHollow;
import shadowverse.cards.Temp.GildedBlade;
import shadowverse.cards.Temp.GildedBoots;
import shadowverse.cards.Temp.GildedGoblet;
import shadowverse.cards.Temp.GildedNecklace;
import shadowverse.cards.Uncommon.UltimateHollow;
import shadowverse.orbs.*;

public class AlwidaPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:AlwidaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:AlwidaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int triggeredThisTurn = 0;

    public AlwidaPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/AlwidaPower.png");
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }

    @Override
    public void atStartOfTurn() {
        this.triggeredThisTurn = 0;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (this.triggeredThisTurn < this.amount && (card instanceof GildedBlade || card instanceof GildedNecklace || card instanceof GildedGoblet || card instanceof GildedBoots || card instanceof UltimateHollow || card instanceof LightOfHollow)) {
            this.triggeredThisTurn++;
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new Pirate()));
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new Viking()));
        }
    }
}

