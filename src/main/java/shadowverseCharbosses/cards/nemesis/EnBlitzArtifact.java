package shadowverseCharbosses.cards.nemesis;

import shadowverseCharbosses.cards.AbstractBossCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

public class EnBlitzArtifact extends AbstractBossCard {
    public static final String ID = "shadowverse:EnBlitzArtifact";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BlitzArtifact");

    public static final String IMG_PATH = "img/cards/BlitzArtifact.png";

    public EnBlitzArtifact() {
        super(ID, cardStrings.NAME, IMG_PATH, 2, cardStrings.DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
        this.baseDamage = 10;
        this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.isMultiDamage = true;
        this.intentMultiAmt = this.magicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++)
            addToBot((AbstractGameAction) new DamageAction((AbstractCreature) p, new DamageInfo((AbstractCreature) m, this.damage), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new EnBlitzArtifact();
    }
}
