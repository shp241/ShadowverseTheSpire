package shadowverseCharbosses.cards.vampire;

import shadowverseCharbosses.bosses.AbstractCharBoss;
import shadowverseCharbosses.cards.AbstractBossCard;
import shadowverseCharbosses.stances.EnVengeance;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;

public class EnDarkGeneral extends AbstractBossCard {
    public static final String ID = "shadowverse:EnDarkGeneral";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DarkGeneral");

    public static final String IMG_PATH = "img/cards/DarkGeneral.png";

    public EnDarkGeneral() {
        super(ID, cardStrings.NAME, IMG_PATH, 2, cardStrings.DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.BASIC, CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
        this.baseDamage = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("DarkGeneral"));
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)p, new DamageInfo((AbstractCreature)m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractCharBoss.boss!=null&&AbstractCharBoss.boss.stance instanceof EnVengeance){
            if (upgraded){
                this.baseDamage = 26;
            }else {
                this.baseDamage = 20;
            }
        }
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new EnDarkGeneral();
    }
}
