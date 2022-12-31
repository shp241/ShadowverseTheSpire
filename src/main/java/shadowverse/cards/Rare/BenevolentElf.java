package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.FeedAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Temp.ArisaArrow;
import shadowverse.cards.Temp.GaleArrow;
import shadowverse.cards.Temp.StormArrow;
import shadowverse.characters.Elf;
import shadowverse.powers.ArisaPower;
import shadowverse.powers.BenevolentElfPower;

import java.util.ArrayList;

public class BenevolentElf
        extends CustomCard {
    public static final String ID = "shadowverse:BenevolentElf";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BenevolentElf");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BenevolentElf.png";



    public BenevolentElf() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("BenevolentElf"));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new BenevolentElfPower(abstractPlayer)));
        if (this.upgraded){
            abstractPlayer.increaseMaxHp(4,false);
            addToBot(new HealAction(abstractPlayer,abstractPlayer,4));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new BenevolentElf();
    }
}

