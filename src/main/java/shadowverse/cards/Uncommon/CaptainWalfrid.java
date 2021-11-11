package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Royal;
import shadowverse.powers.CaptainWalfridPower;

public class CaptainWalfrid extends CustomCard {
    public static final String ID = "shadowverse:CaptainWalfrid";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CaptainWalfrid");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CaptainWalfrid.png";

    public CaptainWalfrid() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(4);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new ApplyPowerAction(p, p, new CaptainWalfridPower(p, this.magicNumber),this.magicNumber));
    }


    @Override
    public AbstractCard makeCopy() {
        return new CaptainWalfrid();
    }
}

