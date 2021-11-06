package shadowverse.relics;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;


public class ArtsMaster
        extends CustomRelic {
    public static final String ID = "shadowverse:ArtsMaster";
    public static final String IMG = "img/relics/ArtsMaster.png";
    public static final String OUTLINE_IMG = "img/relics/outline/ArtsMaster_Outline.png";
    private AbstractPlayer p = AbstractDungeon.player;

    public ArtsMaster() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.MAGICAL);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public void onPlayerEndTurn() {
        if (EnergyPanel.getCurrentEnergy()>0){
            addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,(AbstractPower)new DrawCardNextTurnPower(AbstractDungeon.player,2),2));
            addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,5));
            addToBot((AbstractGameAction)new DamageAllEnemiesAction(null,
                    DamageInfo.createDamageMatrix(5, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SMASH));
        }
    }


    public AbstractRelic makeCopy() {
        return (AbstractRelic) new ArtsMaster();
    }
}


