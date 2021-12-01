package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import shadowverse.action.JatelantAction;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.effect.BetterRainingGoldEffect;
import shadowverse.powers.AbdielPower;

public class Jatelant
        extends CustomCard {
    public static final String ID = "shadowverse:Jatelant";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Jatelant");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Jatelant.png";

    public Jatelant() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.baseMagicNumber = 7;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 10;
        this.isMultiDamage = true;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(3);
            upgradeDamage(5);
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
            count = ((AbstractShadowversePlayer) AbstractDungeon.player).amuletCount;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("Jatelant"));
        AbstractDungeon.effectList.add(new BetterRainingGoldEffect(this.magicNumber * 2, true));
        addToBot((AbstractGameAction)new JatelantAction(this.multiDamage,this.damageTypeForTurn,this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Jatelant();
    }
}


