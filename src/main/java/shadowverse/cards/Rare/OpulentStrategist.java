package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ForeignInfluenceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.OpulentStrategistAction;
import shadowverse.cards.Temp.GlitteringGold;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.powers.GiveGoldPower;
import shadowverse.powers.OpulentStrategistPower;

public class OpulentStrategist extends CustomCard {
    public static final String ID = "shadowverse:OpulentStrategist";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OpulentStrategist");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OpulentStrategist.png";

    public OpulentStrategist() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeMagicNumber(6);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new OpulentStrategistAction(this.upgraded));
        addToBot(new ApplyPowerAction(p, p, new OpulentStrategistPower(p, this.magicNumber), this.magicNumber));
    }


    @Override
    public AbstractCard makeCopy() {
        return new OpulentStrategist();
    }
}
