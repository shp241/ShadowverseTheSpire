package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Nemesis;

public class ApostleOfDestruction
        extends CustomCard {
    public static final String ID = "shadowverse:ApostleOfDestruction";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ApostleOfDestruction");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ApostleOfDestruction.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public ApostleOfDestruction() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 8;
    }



    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("ApostleOfDestruction"));
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
        addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],false,false,card -> {
            return true;
        }, abstractCards ->{
            for (AbstractCard c:abstractCards){
                addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,p.hand));
                AbstractCard card = c.makeCopy();
                addToBot((AbstractGameAction)new ReduceCostAction(card));
                addToBot((AbstractGameAction)new MakeTempCardInHandAction(card));
            }
        } ));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ApostleOfDestruction();
    }
}


