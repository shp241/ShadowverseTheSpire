package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.characters.Royal;
import shadowverse.orbs.HeavyKnight;
import shadowverse.orbs.Minion;
import shadowverse.orbs.ShieldGuardian;

public class DualbladeKnight extends CustomCard {
    public static final String ID = "shadowverse:DualbladeKnight";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DualbladeKnight.png";

    public DualbladeKnight() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = this.magicNumber = 1;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void degrade() {
        if (this.upgraded) {
            degradeName();
            degradeMagicNumber(1);
            this.rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
            this.superFlash();
            this.applyPowers();
        }
    }

    protected void degradeMagicNumber(int amount) {
        this.baseMagicNumber -= amount;
        this.magicNumber = this.baseMagicNumber;
        this.upgradedMagicNumber = false;
    }

    public void degradeName() {
        --this.timesUpgraded;
        this.upgraded = false;
        this.name = NAME;
        this.initializeTitle();
    }

    public int rally() {
        int rally = 0;

        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Minion) {
                rally++;
            }
        }

        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.type == CardType.ATTACK && !(c.hasTag(CardTags.STRIKE))) {
                rally++;
            }
        }
        return rally;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToTop(new GainEnergyAction(this.magicNumber));
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.degrade();
    }


    @Override
    public void applyPowers() {
        if (!this.upgraded && rally() >= 7) {
            this.upgrade();
            this.superFlash();
        }
        super.applyPowers();
        if (this.upgraded) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + rally() + cardStrings.EXTENDED_DESCRIPTION[1];
        }
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        if (this.upgraded) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (this.upgraded) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + rally() + cardStrings.EXTENDED_DESCRIPTION[1];
        }
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new DualbladeKnight();
    }
}

