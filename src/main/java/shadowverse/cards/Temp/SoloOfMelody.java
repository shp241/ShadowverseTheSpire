package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Nemesis;

public class SoloOfMelody
        extends CustomCard {
    public static final String ID = "shadowverse:SoloOfMelody";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SoloOfMelody");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SoloOfMelody.png";

    public SoloOfMelody() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 15;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }



    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(2);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("SoloOfMelody"));
        addToBot((AbstractGameAction)new ExhaustAction(1, false));
        addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(p,this.damage,this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature) AbstractDungeon.player, DamageInfo.createDamageMatrix(this.magicNumber, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SoloOfMelody();
    }
}


