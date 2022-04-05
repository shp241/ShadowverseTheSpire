package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

public class MysterianMissile extends CustomCard {
    public static final String ID = "shadowverse:MysterianMissile";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MysterianMissile");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MysterianMissile.png";

    public MysterianMissile() {
        super("shadowverse:MysterianMissile", NAME, "img/cards/MysterianMissile.png", 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isEthereal = true;
        this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
        }
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractMonster, (AbstractCreature) abstractPlayer, (AbstractPower) new VulnerablePower((AbstractCreature) abstractMonster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MysterianMissile();
    }
}


