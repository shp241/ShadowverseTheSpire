package charbosses.cards.bishop;

import charbosses.cards.AbstractBossCard;
import charbosses.powers.general.EnemyDrawCardNextTurnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import shadowverse.characters.Bishop;

public class EnHeavenlyHound extends AbstractBossCard {
    public static final String ID = "shadowverse:EnHeavenlyHound";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EnHeavenlyHound");

    public static final String IMG_PATH = "img/cards/HeavenlyHound.png";

    public EnHeavenlyHound() {
        super(ID, cardStrings.NAME, IMG_PATH, 1, cardStrings.DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.SELF, AbstractMonster.Intent.DEFEND_BUFF);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new PlatedArmorPower((AbstractCreature)m, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
            upgradeMagicNumber(2);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new EnHeavenlyHound();
    }
}
