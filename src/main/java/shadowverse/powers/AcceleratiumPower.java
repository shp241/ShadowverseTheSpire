package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;


public class AcceleratiumPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:AcceleratiumPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:AcceleratiumPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private int artifactUsed = 0;
    private AbstractCard c = (AbstractCard)new VoidCard();

    public AcceleratiumPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/AcceleratiumPower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    public void atStartOfTurnPostDraw() {
        this.amount -= 1;
        if (this.amount == 0){
            flash();
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
        }
        updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {

        if (card.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT)) {
            flash();
            addToBot((AbstractGameAction)new GainEnergyAction(1));
            artifactUsed++;
            if (artifactUsed%2==0){
                addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(c.makeStatEquivalentCopy(),1));
            }
        }
    }

}

