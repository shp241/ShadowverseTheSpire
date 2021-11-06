package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.cards.Temp.WhiteTiger;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class LevinArcher extends CustomCard {
    public static final String ID = "shadowverse:LevinArcher";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LevinArcher");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LevinArcher.png";
    public static final String IMG_PATH_EV = "img/cards/LevinArcher_Ev.png";

    public LevinArcher() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.LEVIN);
        this.baseDamage = 7;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.textureImg = IMG_PATH_EV;
            this.loadCardImage(IMG_PATH_EV);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void degrade() {
        if (this.upgraded) {
            degradeName();
            this.textureImg = IMG_PATH;
            this.loadCardImage(IMG_PATH);
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
        addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new VulnerablePower(abstractMonster, this.magicNumber, false), this.magicNumber));
        if (this.upgraded) {
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            addToBot(new ApplyPowerAction(abstractMonster, abstractPlayer, new VulnerablePower(abstractMonster, this.magicNumber, false), this.magicNumber));
            this.degrade();
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new LevinArcher();
    }
}

