package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.AlbertDrawAction;
import shadowverse.cards.Temp.TheChariot_I;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.powers.TheChariotPower;
import shadowverse.powers.TheChariot_IPower;

public class TheChariot extends CustomCard {
    public static final String ID = "shadowverse:TheChariot";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheChariot");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TheChariot.png";
    public static final int ENHANCE = 3;
    private boolean doubleCheck = false;

    public TheChariot() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 10;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new TheChariot_I();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
            upgradeMagicNumber(2);
            this.cardsToPreview.upgrade();
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
        addToBot((AbstractGameAction)new SFXAction("TheChariot"));
        if (Shadowverse.Enhance(ENHANCE) && this.costForTurn == ENHANCE) {
            if (this.upgraded){
                addToBot((AbstractGameAction)new ApplyPowerAction(p,p,new TheChariot_IPower(p,70),70));
            }else {
                addToBot((AbstractGameAction)new ApplyPowerAction(p,p,new TheChariot_IPower(p,63),63));
            }
        }else {
            addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
            addToBot((AbstractGameAction)new ApplyPowerAction(p,p,new TheChariotPower(p,this.magicNumber),this.magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new TheChariot();
    }
}


