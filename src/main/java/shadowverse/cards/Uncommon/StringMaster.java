package shadowverse.cards.Uncommon;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Temp.Puppet;
import shadowverse.characters.Nemesis;
import shadowverse.powers.StringMasterPower;


public class StringMaster extends CustomCard {
    public static final String ID = "shadowverse:StringMaster";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:StringMaster");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/StringMaster.png";

    public StringMaster() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = (AbstractCard) new Puppet();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("StringMaster"));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new StringMasterPower((AbstractCreature) abstractPlayer, 1), 1));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new StringMaster();
    }
}

