package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.ApotheosisAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;
import shadowverse.characters.Necromancer;
import shadowverse.characters.Royal;

public class Eahta extends CustomCard {
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Eahta");
    public static final String ID = "shadowverse:Eahta";
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Eahta.png";

    public Eahta() {
        this(0);
    }
    public Eahta(int upgrades) {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 15;
        this.baseMagicNumber = 8;
        this.magicNumber = this.baseMagicNumber;
        this.timesUpgraded = upgrades;
    }

    @Override
    public void upgrade() {
        if (this.magicNumber > 0) {
            upgradeMagicNumber(-1);
        }
        this.timesUpgraded++;
        this.upgraded = true;
        this.name = cardStrings.NAME + "+" + this.timesUpgraded;
        initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        return this.magicNumber > 0;
    }

    @Override
    public void atTurnStart() {
        if (this.baseMagicNumber > 0) {
            this.baseMagicNumber--;
            this.magicNumber = this.baseMagicNumber;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (this.magicNumber <= 0) {
            for (AbstractCard c : p.drawPile.group) {
                if (c.color == Royal.Enums.COLOR_YELLOW && (c.type == CardType.ATTACK || c.type == CardType.SKILL && c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE))) {
                    addToBot(new ReduceCostForTurnAction(c, 2));
                    c.flash();
                }
            }
            for (AbstractCard c : p.discardPile.group) {
                if (c.color == Royal.Enums.COLOR_YELLOW && (c.type == CardType.ATTACK || c.type == CardType.SKILL && c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE))) {
                    addToBot(new ReduceCostForTurnAction(c, 2));
                    c.flash();
                }
            }
        }
        if (this.magicNumber <= 4) {
            for (AbstractCard c : p.hand.group) {
                if (c.color == Royal.Enums.COLOR_YELLOW && (c.type == CardType.ATTACK || c.type == CardType.SKILL && c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE))) {
                    addToBot(new ReduceCostForTurnAction(c, 2));
                    c.flash();
                }
            }
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + (this.magicNumber - 3) + cardStrings.EXTENDED_DESCRIPTION[1] + this.magicNumber + cardStrings.EXTENDED_DESCRIPTION[2];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + (this.magicNumber - 3) + cardStrings.EXTENDED_DESCRIPTION[1] + this.magicNumber + cardStrings.EXTENDED_DESCRIPTION[2];
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Eahta();
    }
}
