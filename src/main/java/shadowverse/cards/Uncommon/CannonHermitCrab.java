package shadowverse.cards.Uncommon;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.orbs.CannonHermitCrabOrb;
import shadowverse.orbs.Knight;


public class CannonHermitCrab extends CustomCard {
    public static final String ID = "shadowverse:CannonHermitCrab";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CannonHermitCrab.png";

    public CannonHermitCrab() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (abstractPlayer.maxOrbs<5){
            int toIncrease = 5-abstractPlayer.maxOrbs;
            addToBot((AbstractGameAction)new IncreaseMaxOrbAction(toIncrease));
        }
        addToBot((AbstractGameAction) new ChannelAction(new CannonHermitCrabOrb()));
    }


    @Override
    public AbstractCard makeCopy() {
        return new CannonHermitCrab();
    }
}

