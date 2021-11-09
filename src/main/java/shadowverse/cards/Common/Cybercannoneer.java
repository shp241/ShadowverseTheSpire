package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Temp.ProductMachine;
import shadowverse.cards.Temp.WhiteTiger;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class Cybercannoneer extends CustomCard {
    public static final String ID = "shadowverse:Cybercannoneer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cybercannoneer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Cybercannoneer.png";

    public Cybercannoneer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ENEMY);
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.baseDamage = 6;
        this.baseMagicNumber = this.magicNumber = 2;
        this.cardsToPreview = new ProductMachine();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(3);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        this.addToTop(new MakeTempCardInHandAction(new ProductMachine(), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cybercannoneer();
    }
}

