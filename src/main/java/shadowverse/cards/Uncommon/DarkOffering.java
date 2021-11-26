package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.Curse.EvilWorship;
import shadowverse.characters.Bishop;
import shadowverse.powers.HereticPriestPower;

public class DarkOffering
        extends CustomCard {
    public static final String ID = "shadowverse:DarkOffering";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DarkOffering");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DarkOffering.png";
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("PlaceCardToAmuletZone");

    public DarkOffering() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.NONE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],true,true,card -> {
            return card.type==CardType.CURSE;
        },abstractCards -> {
            for (AbstractCard c:abstractCards){
                addToBot((AbstractGameAction)new PlaceAmulet(c,p.hand));
                addToBot((AbstractGameAction)new GainEnergyAction(2));
            }
        }));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DarkOffering();
    }
}


