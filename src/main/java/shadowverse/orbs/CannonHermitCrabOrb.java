package shadowverse.orbs;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.action.MinionAttackAction;
import shadowverse.powers.NaterranTree;
import shadowverse.stance.Resonance;

public class CannonHermitCrabOrb extends Minion {

    // Standard ID/Description
    public static final String ORB_ID = "shadowverse:CannonHermitCrabOrb";
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final int ATTACK = 6;
    private static final int DEFENSE = 6;

    public CannonHermitCrabOrb() {
        // The passive/evoke description we pass in here, specifically, don't matter
        // You can ctrl+click on CustomOrb from the `extends CustomOrb` above.
        // You'll see below we override CustomOrb's updateDescription function with our own, and also, that's where the passiveDescription and evokeDescription
        // parameters are used. If your orb doesn't use any numbers/doesn't change e.g "Evoke: shuffle your draw pile."
        // then you don't need to override the update description method and can just pass in the parameters here.
        this.ID = ORB_ID;
        this.img = ImageMaster.loadImage("img/orbs/CannonHermitCrabOrb.png");
        this.name = orbString.NAME;
        this.attack = this.baseAttack = ATTACK;
        this.defense = this.baseDefense = DEFENSE;
        this.updateDescription();
    }

    public CannonHermitCrabOrb(int attack,int defense) {
        this.ID = ORB_ID;
        this.img = ImageMaster.loadImage("img/orbs/CannonHermitCrabOrb.png");
        this.name = orbString.NAME;
        this.attack = this.baseAttack = attack;
        this.defense = this.baseDefense = defense;
        this.updateDescription();
    }

    @Override
    public void updateDescription() { // Set the on-hover description of the orb
        description = DESCRIPTIONS[0] +  this.attack + DESCRIPTIONS[1] + this.defense + DESCRIPTIONS[2];
    }


    @Override
    public void playChannelSFX() { // When you channel this orb, the ATTACK_FIRE effect plays ("Fwoom").
        CardCrawlGame.sound.play("CannonHermitCrab", 0.1f);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new CannonHermitCrabOrb();
    }

    @Override
    public void onEndOfTurn() {
        super.onEndOfTurn();
        if (AbstractDungeon.player.stance.ID.equals(Resonance.STANCE_ID)||AbstractDungeon.player.hasPower(NaterranTree.POWER_ID)){
            int atk = this.attack%2==0?this.attack/2:this.attack/2+1;
            int def = this.defense%2==0?this.defense/2:this.defense/2+1;
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new CannonHermitCrabOrb(atk,def)));
        }
    }

    @Override
    public void effect() {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(AbstractDungeon.player,this.defense));
        int damage = this.attack;
        if (AbstractDungeon.player.hasPower("Electro")) {
            AbstractDungeon.actionManager.addToTop(new MinionAttackAction(new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS), true));
        } else {
            AbstractDungeon.actionManager.addToTop(new MinionAttackAction(new DamageInfo(AbstractDungeon.player, damage, DamageInfo.DamageType.THORNS), false));
        }
    }
}
