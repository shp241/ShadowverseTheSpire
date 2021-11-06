package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.cards.Temp.ExterminusWeapon;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.powers.GloriousCorePower;

public class GloriousCore extends CustomCard {
    public static final String ID = "shadowverse:GloriousCore";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GloriousCore");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GloriousCore.png";
    public static final int ENHANCE = 2;
    private boolean doubleCheck = false;

    public GloriousCore() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.cardsToPreview = new EvolutionPoint();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void triggerWhenDrawn() {
        if (Shadowverse.Enhance(ENHANCE)) {
            super.triggerWhenDrawn();
            setCostForTurn(ENHANCE);
            applyPowers();
        }
    }

    @Override
    public void applyPowers() {
        if (Shadowverse.Enhance(ENHANCE)) {
            setCostForTurn(ENHANCE);
        } else {
            resetAttributes();
        }
        super.applyPowers();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.group.contains(this)) {
            setCostForTurn(ENHANCE);
            applyPowers();
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (AbstractDungeon.player.hasPower("Burst") || AbstractDungeon.player.hasPower("Double Tap") || AbstractDungeon.player.hasPower("Amplified")) {
            doubleCheck = true;
            if (EnergyPanel.getCurrentEnergy() - c.costForTurn < ENHANCE) {
                resetAttributes();
                applyPowers();
            }
        } else {
            if (doubleCheck) {
                doubleCheck = false;
            } else {
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < ENHANCE) {
                    resetAttributes();
                    applyPowers();
                }
            }
        }
    }

    @Override
    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (EnergyPanel.getCurrentEnergy() >= ENHANCE) {
            setCostForTurn(ENHANCE);
        } else {
            resetAttributes();
        }
        applyPowers();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new GloriousCorePower(p, this.magicNumber)));
        if (Shadowverse.Enhance(ENHANCE) && this.costForTurn == ENHANCE) {
            this.addToTop(new GainEnergyAction(1));
            this.addToTop(new MakeTempCardInHandAction(new EvolutionPoint(), 1));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new GloriousCore();
    }
}

