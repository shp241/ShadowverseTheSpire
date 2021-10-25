package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.BounceAction;
import shadowverse.action.DramaticRetreatAction;
import shadowverse.characters.Elf;
import shadowverse.characters.Royal;

public class DramaticRetreat extends CustomCard {
    public static final String ID = "shadowverse:DramaticRetreat";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DramaticRetreat");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DramaticRetreat.png";

    public DramaticRetreat() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        boolean hasAttack = false;
        for (AbstractCard c : p.discardPile.group) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                hasAttack = true;
                break;
            }
        }
        if (!hasAttack) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            canUse = false;
        }
        return canUse;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new DramaticRetreatAction(1));
        addToBot(new DrawCardAction(abstractPlayer, this.magicNumber));
    }


    @Override
    public AbstractCard makeCopy() {
        return new DramaticRetreat();
    }
}
