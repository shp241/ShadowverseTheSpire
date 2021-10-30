package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.characters.Royal;
import shadowverse.powers.LuminousMagePower;

public class LuminousMage extends CustomCard {
    public static final String ID = "shadowverse:LuminousMage";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LuminousMage");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LuminousMage.png";

    public LuminousMage() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = new EvolutionPoint();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new ApplyPowerAction(p, p, new LuminousMagePower(p, this.magicNumber)));
    }


    @Override
    public AbstractCard makeCopy() {
        return new LuminousMage();
    }
}
