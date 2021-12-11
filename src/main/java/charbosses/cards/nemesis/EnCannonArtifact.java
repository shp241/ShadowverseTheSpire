package charbosses.cards.nemesis;

import charbosses.bosses.AbstractCharBoss;
import charbosses.cards.AbstractBossCard;
import charbosses.powers.general.EnemyDrawCardNextTurnPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

public class EnCannonArtifact extends AbstractBossCard {
    public static final String ID = "shadowverse:EnCannonArtifact";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CannonArtifact");

    public static final String IMG_PATH = "img/cards/CannonArtifact.png";

    public EnCannonArtifact() {
        super(ID, cardStrings.NAME, IMG_PATH, 0, cardStrings.DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ENEMY, AbstractMonster.Intent.ATTACK_BUFF);
        this.baseDamage = 8;
        this.baseMagicNumber = 4;
        this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) p, new DamageInfo((AbstractCreature) m, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        int artifactAmt = 0;
        for (AbstractCard c : AbstractCharBoss.boss.hand.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT) && c != this)
                artifactAmt++;
        }
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) p, new DamageInfo((AbstractCreature) m, artifactAmt * this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(2);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new EnCannonArtifact();
    }
}
