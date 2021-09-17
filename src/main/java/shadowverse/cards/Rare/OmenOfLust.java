package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.OmenOfLustPower;
import shadowverse.powers.WrathPower;


public class OmenOfLust
        extends CustomCard {
    public static final String ID = "shadowverse:OmenOfLust";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfLust");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmenOfLust.png";

    public OmenOfLust() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("OmenOfLust"));
        if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.hasPower(WrathPower.POWER_ID)){
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new WeakPower((AbstractCreature)abstractMonster, this.magicNumber,false), this.magicNumber));
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new VulnerablePower((AbstractCreature)abstractMonster, this.magicNumber,false), this.magicNumber));
            addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }else {
            addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new OmenOfLustPower(abstractPlayer,this.magicNumber),this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OmenOfLust();
    }
}


