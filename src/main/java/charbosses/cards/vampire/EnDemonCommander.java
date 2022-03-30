package charbosses.cards.vampire;

import charbosses.bosses.AbstractCharBoss;
import charbosses.cards.AbstractBossCard;
import charbosses.powers.cardpowers.EnemyDoubleTapPower;
import charbosses.stances.EnVengeance;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.Vampire;

public class EnDemonCommander extends AbstractBossCard {
    public static final String ID = "shadowverse:EnDemonCommander";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EnDemonCommander");

    public static final String IMG_PATH = "img/cards/DemonCommander.png";

    public EnDemonCommander() {
        super(ID, cardStrings.NAME, IMG_PATH, 1, cardStrings.DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF, AbstractMonster.Intent.DEFEND_BUFF);
        this.baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("DemonCommander"));
        addToBot((AbstractGameAction)new GainBlockAction(m,this.block));
        if (((AbstractCharBoss)m).stance instanceof EnVengeance){
            addToBot((AbstractGameAction)new ApplyPowerAction(m,m,new EnemyDoubleTapPower(m,1),1));
        }
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new EnDemonCommander();
    }
}
