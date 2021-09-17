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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.cards.Temp.BloodArts;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.NextAluzard;


public class Aluzard
        extends CustomCard {
    public static final String ID = "shadowverse:Aluzard";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Aluzard");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Aluzard.png";

    public Aluzard() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.baseMagicNumber = 5;
        this.magicNumber = this.baseMagicNumber;
        this.cardsToPreview = (AbstractCard) new BloodArts();
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,(AbstractPower)new NextAluzard(AbstractDungeon.player,1,this,this.upgraded)));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Aluzard"));
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Aluzard();
    }
}


