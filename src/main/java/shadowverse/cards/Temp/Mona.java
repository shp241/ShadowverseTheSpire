package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class Mona extends CustomCard {
    public static final String ID = "shadowverse:Mona";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Mona.png";

    public Mona() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseBlock = 12;
        this.tags.add(AbstractShadowversePlayer.Enums.LEVIN);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        this.addToTop(new GainEnergyAction(1));
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN) && c != this) {
                this.addToTop(new GainEnergyAction(1));
                break;
            }
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Mona();
    }
}



