package shadowverse.relics;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class GrandSword
        extends CustomRelic {
    public static final String ID = "shadowverse:GrandSword";
    public static final String IMG = "img/relics/GrandSword.png";
    public static final String OUTLINE_IMG = "img/relics/outline/GrandSword_Outline.png";
    private boolean triggered = false;

    public GrandSword() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.SOLID);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public void atTurnStart() {
        triggered = false;
    }

    public AbstractRelic makeCopy() {
        return (AbstractRelic) new GrandSword();
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        if (!triggered){
            flash();
            addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, (AbstractPower) new ThornsPower(AbstractDungeon.player, 2), 2));
            triggered = true;
        }
        return super.onPlayerGainedBlock(blockAmount);
    }

}

