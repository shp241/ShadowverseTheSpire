package shadowverseCharbosses.cards.bishop;

import shadowverseCharbosses.cards.AbstractBossCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import shadowverse.characters.Bishop;

public class EnDarkPriest extends AbstractBossCard {
    public static final String ID = "shadowverse:EnDarkPriest";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EnDarkPriest");

    public static final String IMG_PATH = "img/cards/DarkPriest.png";

    public EnDarkPriest() {
        super(ID, cardStrings.NAME, IMG_PATH, 1, cardStrings.DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.ENEMY, AbstractMonster.Intent.DEFEND_DEBUFF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("DarkPriest"));
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
        addToBot((AbstractGameAction)new ApplyPowerAction(p,m,(AbstractPower)new WeakPower(p,this.magicNumber,false),this.magicNumber));
        addToBot((AbstractGameAction)new ApplyPowerAction(p,m,(AbstractPower)new FrailPower(p,this.magicNumber,false),this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new EnDarkPriest();
    }
}
