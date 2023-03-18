package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.powers.DDKPower;

public class ExecutorOfTheOath extends CustomCard {
    public static final String ID = "shadowverse:ExecutorOfTheOath";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ExecutorOfTheOath");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ExecutorOfTheOath.png";


    public ExecutorOfTheOath() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 20;
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
    }



    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(5);
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot(new SFXAction("ExecutorOfTheOath"));
        addToBot(new GainBlockAction(p,this.block));
        addToBot(new HealAction(p,p,2));
        this.cost = 4;
    }


    @Override
    public AbstractCard makeCopy() {
        return new ExecutorOfTheOath();
    }
}
