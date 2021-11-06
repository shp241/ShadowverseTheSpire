package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
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
import shadowverse.cards.Temp.ProductMachine;
import shadowverse.cards.Temp.TyrantsOrder;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.monsters.Henchman;

public class Nahtnaught extends CustomCard {
    public static final String ID = "shadowverse:Nahtnaught";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Nahtnaught");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Nahtnaught.png";
    public static final int ENHANCE = 2;
    private boolean doubleCheck = false;

    public Nahtnaught() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
        this.cardsToPreview = new TyrantsOrder();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
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
        if (Shadowverse.Enhance(ENHANCE) && this.costForTurn == ENHANCE) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Eh"));
            addToBot(new SpawnMonsterAction(new Henchman(-100 - 185.0F * 1, MathUtils.random(-5.0F, 25.0F)), true));
            addToBot(new SpawnMonsterAction(new Henchman(-100 - 185.0F * 2, MathUtils.random(-5.0F, 25.0F)), true));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        this.addToTop(new MakeTempCardInHandAction(new TyrantsOrder(), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Nahtnaught();
    }
}