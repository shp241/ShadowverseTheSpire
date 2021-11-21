package shadowverse.orbs;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import shadowverse.action.MinionAttackAction;
import shadowverse.action.MinionBuffAction;
import shadowverse.action.RemoveMinionAction;

public class QueenMagnus extends Minion {

    // Standard ID/Description
    public static final String ORB_ID = "shadowverse:QueenMagnus";
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final int ATTACK = 3;
    private static final int DEFENSE = 3;

    public QueenMagnus() {
        // The passive/evoke description we pass in here, specifically, don't matter
        // You can ctrl+click on CustomOrb from the `extends CustomOrb` above.
        // You'll see below we override CustomOrb's updateDescription function with our own, and also, that's where the passiveDescription and evokeDescription
        // parameters are used. If your orb doesn't use any numbers/doesn't change e.g "Evoke: shuffle your draw pile."
        // then you don't need to override the update description method and can just pass in the parameters here.
        this.ID = ORB_ID;
        this.img = ImageMaster.loadImage("img/orbs/QueenMagnus.png");
        this.name = orbString.NAME;
        this.attack = this.baseAttack = ATTACK;
        this.defense = this.baseDefense = DEFENSE;
        this.updateDescription();
    }

    @Override
    public void updateDescription() { // Set the on-hover description of the orb
        description = DESCRIPTIONS[0] + "3*" + this.attack + "=" + 3 * this.attack + DESCRIPTIONS[1];
    }




    @Override
    public AbstractOrb makeCopy() {
        return new QueenMagnus();
    }

    @Override
    public void onEvoke() {
        AbstractDungeon.actionManager.addToBottom(new SFXAction(this.ID.replace("shadowverse:", "") + "_Atk"));
        for (int i = 0; i < defense; i++) {
            this.effect();
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
        }
        this.defense = 0;
        AbstractDungeon.actionManager.addToBottom(new RemoveMinionAction());
    }

    @Override
    public void playChannelSFX() { // When you channel this orb, the ATTACK_FIRE effect plays ("Fwoom").
        AbstractDungeon.actionManager.addToTop(new SFXAction(this.ID.replace("shadowverse:", "")));
    }

    @Override
    public void onEndOfTurn() {
        if (this.defense > 0) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction(this.ID.replace("shadowverse:", "") + "_Atk"));
            AbstractCreature p = AbstractDungeon.player;
            this.effect();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new EnergizedPower(p, 1), 1));
            AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(0, -1, this));
            this.updateDescription();
        }
    }

    @Override
    public void effect() {
        int damage = this.attack * 3;
        if (AbstractDungeon.player.hasPower("Electro")) {
            AbstractDungeon.actionManager.addToBottom(new MinionAttackAction(new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS), true));
        } else {
            AbstractDungeon.actionManager.addToBottom(new MinionAttackAction(new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS), false));
        }
    }
}

