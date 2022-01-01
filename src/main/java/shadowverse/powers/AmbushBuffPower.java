package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.MinionBuffAction;
import shadowverse.orbs.AmbushMinion;
import shadowverse.orbs.Minion;

public class AmbushBuffPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:AmbushBuffPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:AmbushBuffPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AmbushBuffPower(AbstractCreature owner,int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        loadRegion("blur");
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] +amount + DESCRIPTIONS[2];
    }

    @Override
    public void atStartOfTurnPostDraw() {
        for (AbstractOrb o: AbstractDungeon.player.orbs){
            if (o instanceof AmbushMinion){
                if (((AmbushMinion) o).ambush){
                    AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(this.amount, this.amount, (Minion) o));
                }
            }
        }
    }
}

