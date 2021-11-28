package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.AbstractShadowversePlayer;

public class RobowhipReverendPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:RobowhipReverendPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:RobowhipReverendPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public RobowhipReverendPower(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/RobowhipReverendPower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    @Override
    public void atStartOfTurn() {
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
    }

    @Override
    public int onHeal(int healAmount) {
        flash();
        addToBot((AbstractGameAction)new SFXAction("RobowhipReverendPower"));
        addToBot((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,(AbstractPower)new DexterityPower(this.owner,this.amount),this.amount));
        for (AbstractCard c: AbstractDungeon.player.hand.group){
            if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c.type== AbstractCard.CardType.ATTACK){
                addToBot((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,(AbstractPower)new StrengthPower(this.owner,this.amount),this.amount));
            }
        }
        return healAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
