package charbosses.cards.nemesis;

import charbosses.cards.AbstractBossCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import shadowverse.characters.Nemesis;

public class EnBlackenedScripture extends AbstractBossCard {
    public static final String ID = "shadowverse:EnBlackenedScripture";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BlackenedScripture");

    public static final String IMG_PATH = "img/cards/BlackenedScripture.png";

    public EnBlackenedScripture() {
        super(ID, cardStrings.NAME, IMG_PATH, 1, cardStrings.DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ENEMY, AbstractMonster.Intent.STRONG_DEBUFF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new WeakPower((AbstractCreature)p, this.magicNumber, false), this.magicNumber));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)m, (AbstractPower)new FrailPower((AbstractCreature)p, this.magicNumber, false), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new EnBlackenedScripture();
    }
}
