package shadowverseCharbosses.cards.bishop;

import shadowverseCharbosses.cards.AbstractBossCard;
import shadowverseCharbosses.powers.general.EnemyDrawCardNextTurnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.Bishop;

public class EnSacredPlea extends AbstractBossCard {
    public static final String ID = "shadowverse:EnSacredPlea";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EnSacredPlea");

    public static final String IMG_PATH = "img/cards/SacredPlea.png";

    public EnSacredPlea() {
        super(ID, cardStrings.NAME, IMG_PATH, 0, cardStrings.DESCRIPTION, CardType.POWER, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.SELF, AbstractMonster.Intent.BUFF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new EnemyDrawCardNextTurnPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new EnSacredPlea();
    }
}
