package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import shadowverse.action.MinionSummonAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.orbs.ShieldGuardian;

public class Lounes extends CustomCard {
    public static final String ID = "shadowverse:Lounes";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Lounes.png";
    public static final int ENHANCE = 1;
    private boolean doubleCheck = false;

    public Lounes() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 3;
        this.baseBlock = 2;
        this.tags.add(AbstractShadowversePlayer.Enums.LEVIN);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeDamage(3);
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
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Eh"));
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN)) {
                addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
            }
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Lounes();
    }
}




