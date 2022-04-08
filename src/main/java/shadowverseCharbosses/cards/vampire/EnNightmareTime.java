package shadowverseCharbosses.cards.vampire;

import shadowverseCharbosses.cards.AbstractBossCard;
import shadowverseCharbosses.powers.bossmechanicpowers.EnemyNightmareTimePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;

public class EnNightmareTime extends AbstractBossCard {
    public static final String ID = "shadowverse:EnNightmareTime";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EnNightmareTime");

    public static final String IMG_PATH = "img/cards/NightmareTime.png";

    public EnNightmareTime() {
        super(ID, cardStrings.NAME, IMG_PATH, 0, cardStrings.DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.SELF, AbstractMonster.Intent.BUFF);
        this.exhaust = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("NightmareTime"));
        addToBot((AbstractGameAction)new LoseHPAction(m,m,1));
        addToBot((AbstractGameAction)new ApplyPowerAction(m,m,new EnemyNightmareTimePower(m,this.magicNumber)));
        addToBot((AbstractGameAction)new RemoveDebuffsAction(m));
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new EnNightmareTime();
    }
}
