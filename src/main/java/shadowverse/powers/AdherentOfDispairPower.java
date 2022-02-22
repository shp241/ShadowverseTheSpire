package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;

public class AdherentOfDispairPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:AdherentOfDispairPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:AdherentOfDispairPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AdherentOfDispairPower(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        loadRegion("buffer");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            boolean attacked = false;
            for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisTurn){
                if (c.type== AbstractCard.CardType.ATTACK){
                    attacked = true;
                    break;
                }
            }
            if (!attacked){
                addToBot((AbstractGameAction)new SFXAction("AdherentOfDispairPower"));
                addToBot((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,new BufferPower(this.owner,1),1));
                addToBot((AbstractGameAction)new ReducePowerAction(this.owner,this.owner,this,1));
            }
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
