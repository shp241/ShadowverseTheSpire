package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.MinionSummonAction;
import shadowverse.characters.Royal;
import shadowverse.orbs.*;


public class Arthur extends CustomCard {
    public static final String ID = "shadowverse:Arthur";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Arthur.png";

    public Arthur() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 18;
        this.exhaust = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        addToBot(new MinionSummonAction(new SteelcladKnight()));
        addToBot(new MinionSummonAction(new Knight()));
        addToBot(new MinionSummonAction(new HeavyKnight()));
        addToBot(new MinionSummonAction(new ShieldGuardian()));
    }


    @Override
    public AbstractCard makeCopy() {
        return new Arthur();
    }
}

