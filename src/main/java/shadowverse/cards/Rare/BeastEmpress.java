package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.cards.Common.FloodBehemoth;
import shadowverse.characters.Vampire;
import shadowverse.powers.AvaricePower;
import shadowverse.powers.EpitaphPower;


public class BeastEmpress
        extends CustomCard {
    public static final String ID = "shadowverse:BeastEmpress";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BeastEmpress");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BeastEmpress.png";

    public BeastEmpress() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 15;
        this.cardsToPreview = (AbstractCard) new FloodBehemoth();
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("BeastEmpress"));
        addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        c.setCostForTurn(0);
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(c));
        if (abstractPlayer.hasPower(EpitaphPower.POWER_ID) || abstractPlayer.hasPower(AvaricePower.POWER_ID)) {
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (!mo.isDeadOrEscaped()) {
                    addToBot((AbstractGameAction) new ApplyPowerAction(mo, abstractPlayer, (AbstractPower) new StrengthPower(mo, -2), -2));
                    addToBot((AbstractGameAction) new ApplyPowerAction(mo, abstractPlayer, (AbstractPower) new DexterityPower(mo, -2), -2));
                }
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new BeastEmpress();
    }
}


