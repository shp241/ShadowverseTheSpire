package charbosses.cards.nemesis;

import charbosses.cards.AbstractBossCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import shadowverse.characters.Nemesis;

public class AbsoluteJudgment extends AbstractBossCard {
    public static final String ID = "shadowverse:AbsoluteJudgment";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AbsoluteJudgment");

    public static final String IMG_PATH = "img/cards/AbsoluteJudgment.png";

    public AbsoluteJudgment() {
        super(ID, cardStrings.NAME, IMG_PATH, 0, cardStrings.DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ENEMY, AbstractMonster.Intent.STRONG_DEBUFF);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new VulnerablePower((AbstractCreature)p, this.magicNumber, false), this.magicNumber));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)m, (AbstractPower)new BufferPower((AbstractCreature)m, 1), 1));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.GOLDENROD, true)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new AbsoluteJudgment();
    }
}
