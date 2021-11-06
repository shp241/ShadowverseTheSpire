package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.orbs.Minion;

import java.util.Iterator;

public class JenoPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:JenoPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:JenoPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public JenoPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/JenoPower.png");
        this.updateExisting();
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999) {
            this.amount = 999;
        }
        this.updateExisting();
    }

    private void updateExisting() {
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while (var1.hasNext()) {
            c = (AbstractCard) var1.next();
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN)) {
                AbstractCard c1 = c.makeCopy();
                if (c.upgraded) {
                    c1.upgrade();
                }
                c.baseDamage = c1.baseDamage + this.amount;
                c.baseBlock = c1.baseBlock + this.amount;
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();

        while (var1.hasNext()) {
            c = (AbstractCard) var1.next();
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN)) {
                AbstractCard c1 = c.makeCopy();
                if (c.upgraded) {
                    c1.upgrade();
                }
                c.baseDamage = c1.baseDamage + this.amount;
                c.baseBlock = c1.baseBlock + this.amount;
            }
        }

        var1 = AbstractDungeon.player.discardPile.group.iterator();

        while (var1.hasNext()) {
            c = (AbstractCard) var1.next();
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN)) {
                AbstractCard c1 = c.makeCopy();
                if (c.upgraded) {
                    c1.upgrade();
                }
                c.baseDamage = c1.baseDamage + this.amount;
                c.baseBlock = c1.baseBlock + this.amount;
            }
        }

        var1 = AbstractDungeon.player.exhaustPile.group.iterator();

        while (var1.hasNext()) {
            c = (AbstractCard) var1.next();
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN)) {
                AbstractCard c1 = c.makeCopy();
                if (c.upgraded) {
                    c1.upgrade();
                }
                c.baseDamage = c1.baseDamage + this.amount;
                c.baseBlock = c1.baseBlock + this.amount;
            }
        }

    }

    @Override
    public void onDrawOrDiscard() {
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        while (var1.hasNext()) {
            AbstractCard c = (AbstractCard) var1.next();
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN)) {
                AbstractCard c1 = c.makeCopy();
                if (c.upgraded) {
                    c1.upgrade();
                }
                c.baseDamage = c1.baseDamage + this.amount;
                c.baseBlock = c1.baseBlock + this.amount;
            }
        }

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }


}
