package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import shadowverse.action.MinionSummonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.cards.Temp.ExterminusWeapon;
import shadowverse.characters.Royal;
import shadowverse.orbs.Knight;
import shadowverse.orbs.SteelcladKnight;

public class Alyaska extends CustomCard {
    public static final String ID = "shadowverse:Alyaska";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Alyaska.png";

    public Alyaska() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, AbstractCard.CardType.ATTACK, Royal.Enums.COLOR_YELLOW, AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.ENEMY);
        this.baseDamage = 10;
        this.cardsToPreview = new ExterminusWeapon();
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void degrade() {
        if (this.upgraded) {
            degradeName();
            this.rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
            this.superFlash();
            this.applyPowers();
        }
    }

    public void degradeName() {
        --this.timesUpgraded;
        this.upgraded = false;
        this.name = NAME;
        this.initializeTitle();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.upgraded) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Ev"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (this.upgraded) {
            AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
            addToBot(new MakeTempCardInHandAction(c, 1));
            this.degrade();
        }
    }

    @Override
    public void triggerWhenDrawn() {
        if (!this.upgraded) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")+"_Eff"));
            this.addToTop(new MakeTempCardInHandAction(new EvolutionPoint(), 1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Alyaska();
    }
}

