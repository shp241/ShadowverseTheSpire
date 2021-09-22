package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.MinionBuffAction;
import shadowverse.action.MinionOrderAction;
import shadowverse.characters.Royal;

public class GildedBoots extends CustomCard {
    public static final String ID = "shadowverse:GildedBoots";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GildedBoots");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GildedBoots.png";

    public GildedBoots() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.upgraded) {
            addToBot(new MinionOrderAction());
        }
        addToBot(new MinionOrderAction());
    }


    @Override
    public AbstractCard makeCopy() {
        return new GildedBoots();
    }
}