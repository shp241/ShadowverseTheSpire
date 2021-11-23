package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Temp.Fairy;
import shadowverse.cards.Temp.ForestBat;

public class NightVampirePower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:NightVampirePower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:NightVampirePower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public NightVampirePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/NightVampirePower.png");
        updateExistingBat();
    }

    private void updateExistingBat() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ForestBat) {
                c.baseDamage += c.baseDamage;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (c instanceof ForestBat) {
                c.baseDamage += c.baseDamage;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof ForestBat) {
                c.baseDamage += c.baseDamage;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c instanceof ForestBat) {
                c.baseDamage += c.baseDamage;
            }
        }
    }

    public void onDrawOrDiscard() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ForestBat) {
                c.baseDamage += c.baseDamage;
            }
        }
    }
    
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
