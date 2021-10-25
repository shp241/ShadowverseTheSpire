package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Royal;
import shadowverse.powers.FortressStrategistPower;

import java.util.ArrayList;

public class FortressStrategist extends CustomCard {
    public static final String ID = "shadowverse:FortressStrategist";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FortressStrategist");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/FortressStrategist.png";

    public FortressStrategist() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(1);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new ApplyPowerAction(p, p, new FortressStrategistPower(p, this.magicNumber)));
    }


    @Override
    public AbstractCard makeCopy() {
        return new FortressStrategist();
    }
}
