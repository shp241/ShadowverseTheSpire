package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.stance.Resonance;


public class DeusExMachinaPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:DeusExMachinaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:DeusExMachinaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DeusExMachinaPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/DeusExMachinaPower.png");
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }


    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT)) {
            flash();
            addToBot((AbstractGameAction)new GainEnergyAction(1));
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer){
        if (isPlayer){
            if (AbstractDungeon.player.stance.ID.equals(Resonance.STANCE_ID)){
                addToBot((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,(AbstractPower)new DrawCardNextTurnPower(this.owner,2),2));
            }
        }
    }
}

