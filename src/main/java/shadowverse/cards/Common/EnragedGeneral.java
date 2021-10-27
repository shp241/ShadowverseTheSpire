package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.DecreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.cards.Temp.GildedGoblet;
import shadowverse.cards.Temp.GildedNecklace;
import shadowverse.characters.Royal;
import shadowverse.orbs.Minion;

import java.util.ArrayList;

public class EnragedGeneral extends CustomCard {
    public static final String ID = "shadowverse:EnragedGeneral";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EnragedGeneral");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EnragedGeneral.png";

    public EnragedGeneral() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        int m = 0;
        for (int i = 0; i < p.orbs.size(); i++) {
            if (p.orbs.get(i) instanceof Minion) {
                m++;
            }
        }
        this.addToTop(new GainEnergyAction(m));
        this.addToBot(new DecreaseMaxOrbAction(1));
    }


    @Override
    public AbstractCard makeCopy() {
        return new EnragedGeneral();
    }
}
