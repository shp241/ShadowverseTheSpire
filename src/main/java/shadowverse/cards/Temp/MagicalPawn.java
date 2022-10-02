package shadowverse.cards.Temp;


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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import shadowverse.characters.Elf;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.MysticKingPower;
import shadowverse.powers.NightVampirePower;
import shadowverse.powers.VanpiPower;


public class MagicalPawn extends CustomCard {
    public static final String ID = "shadowverse:MagicalPawn";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MagicalPawn");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MagicalPawn.png";

    public MagicalPawn() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 4;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }

    public void applyPowers() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c instanceof MagicalPawn)
                count++;
        }
        if (count >= 8) {
            int realBaseDamage = this.baseDamage;
            this.baseDamage = this.baseDamage + 4;
            if (AbstractDungeon.player.hasPower(MysticKingPower.POWER_ID)){
                this.baseDamage = this.baseDamage + AbstractDungeon.player.getPower(MysticKingPower.POWER_ID).amount;
                this.baseDamage *= 2;
            }
            super.applyPowers();
            this.baseDamage = realBaseDamage;
            this.isDamageModified = (this.damage != this.baseDamage);
        }else{
            if (AbstractDungeon.player.hasPower(MysticKingPower.POWER_ID)){
                int realBaseDamage = this.baseDamage;
                this.baseDamage = this.baseDamage + AbstractDungeon.player.getPower(MysticKingPower.POWER_ID).amount;
                this.baseDamage *= 2;
                super.applyPowers();
                this.baseDamage = realBaseDamage;
                this.isDamageModified = (this.damage != this.baseDamage);
            }
            super.applyPowers();
        }
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c instanceof MagicalPawn)
                count++;
        }
        if (count > 8) {
            int realBaseDamage = this.baseDamage;
            this.baseDamage = this.baseDamage + 4;
            if (AbstractDungeon.player.hasPower(MysticKingPower.POWER_ID)){
                this.baseDamage = this.baseDamage + AbstractDungeon.player.getPower(MysticKingPower.POWER_ID).amount;
                this.baseDamage *= 2;
            }
            super.calculateCardDamage(mo);
            this.baseDamage = realBaseDamage;
            this.isDamageModified = (this.damage != this.baseDamage);
        }else{
            if (AbstractDungeon.player.hasPower(MysticKingPower.POWER_ID)){
                int realBaseDamage = this.baseDamage;
                this.baseDamage = this.baseDamage + AbstractDungeon.player.getPower(MysticKingPower.POWER_ID).amount;
                this.baseDamage *= 2;
                super.calculateCardDamage(mo);
                this.baseDamage = realBaseDamage;
                this.isDamageModified = (this.damage != this.baseDamage);
            }
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        calculateCardDamage(abstractMonster);
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MagicalPawn();
    }
}

