package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.cards.Temp.ProductMachine;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;

public class ArmoredBat
        extends CustomCard {
    public static final String ID = "shadowverse:ArmoredBat";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ArmoredBat");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ArmoredBat.png";
    private boolean doubleCheck = false;

    public ArmoredBat() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
        this.baseBlock = 1;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.cardsToPreview = (AbstractCard)new ProductMachine();
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(1);
        }
    }

    public void triggerWhenDrawn() {
        if (Shadowverse.Enhance(2)) {
            super.triggerWhenDrawn();
            setCostForTurn(2);
            applyPowers();
        }
    }

    @Override
    public void applyPowers() {
        if (Shadowverse.Enhance(2))
            setCostForTurn(2);
        else
            resetAttributes();
        super.applyPowers();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.group.contains(this)) {
            if (Shadowverse.Enhance(2)) {
                super.triggerWhenDrawn();
                setCostForTurn(2);
                applyPowers();
            }
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (AbstractDungeon.player.hasPower("Burst") || AbstractDungeon.player.hasPower("Double Tap") || AbstractDungeon.player.hasPower("Amplified")) {
            doubleCheck = true;
            if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                resetAttributes();
                applyPowers();
            }
        } else {
            if (doubleCheck) {
                doubleCheck = false;
            } else {
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                    resetAttributes();
                    applyPowers();
                }
            }
        }
    }

    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (EnergyPanel.getCurrentEnergy() >= 2) {
            setCostForTurn(2);
        } else {
            resetAttributes();
        }
        applyPowers();
    }

    @Override
    public void triggerOnExhaust() {
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),2));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),2));
        if (this.costForTurn == 2 && Shadowverse.Enhance(2)) {
            addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.magicNumber));
            addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new PlatedArmorPower(abstractPlayer,this.magicNumber),this.magicNumber));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ArmoredBat();
    }
}


