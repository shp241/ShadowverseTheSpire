package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

import java.util.ArrayList;

public class GrandAcquisition extends CustomCard {
    public static final String ID = "shadowverse:GrandAcquisition";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GrandAcquisition");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GrandAcquisition.png";
    public static final int ENHANCE = 3;
    private boolean doubleCheck = false;

    public GrandAcquisition() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.NONE);
        this.exhaust = true;
        this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
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
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Enhance(ENHANCE) && this.costForTurn == ENHANCE) {
            addToBot(new MakeTempCardInHandAction(new GildedBlade()));
            addToBot(new MakeTempCardInHandAction(new GildedGoblet()));
            addToBot(new MakeTempCardInHandAction(new GildedBoots()));
            addToBot(new MakeTempCardInHandAction(new GildedNecklace()));
            addToBot(new GainEnergyAction(2));
        } else {
            addToBot(new ChoiceAction2(new GildedBlade(),new GildedGoblet(),new GildedBoots(),new GildedNecklace()));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new GrandAcquisition();
    }
}
