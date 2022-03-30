package shadowverse.cards.Common;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DestroyAction;
import shadowverse.characters.Necromancer;


public class SoulGuide extends CustomCard {
    public static final String ID = "shadowverse:SoulGuide";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SoulGuide");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SoulGuide.png";

    public SoulGuide() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 5;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeBlock(3);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("SoulGuide"));
        addToBot(new GainBlockAction(abstractPlayer, this.block));
        addToBot((AbstractGameAction) new DestroyAction(1, (AbstractGameAction) new DrawCardAction(this.magicNumber)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SoulGuide();
    }
}
