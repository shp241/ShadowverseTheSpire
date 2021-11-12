package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.characters.Royal;

import java.util.ArrayList;
import java.util.List;

public class HolyPurebomb extends CustomCard {
    public static final String ID = "shadowverse:HolyPurebomb";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/HolyPurebomb.png";

    public HolyPurebomb() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        List<AbstractCard> temp = new ArrayList<>();
        for (AbstractCard c : abstractPlayer.hand.group) {
            if ((c.type == CardType.CURSE || c.type == CardType.STATUS) && !(c instanceof EvolutionPoint)) {
                temp.add(c);
            }
        }
        for (AbstractCard c : temp) {
            addToBot(new ExhaustSpecificCardAction(c, abstractPlayer.hand));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new HolyPurebomb();
    }
}

