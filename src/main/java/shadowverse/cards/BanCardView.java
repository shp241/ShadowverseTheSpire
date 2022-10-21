package shadowverse.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.Shadowverse;
import shadowverse.characters.Royal;
import shadowverse.helper.BanCardHelper;
import shadowverse.helper.ViewBanCardScreen;

import java.util.ArrayList;

public class BanCardView extends CustomCard {
    public static final String ID = "shadowverse:BanCardView";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;
    private int code;

    public BanCardView(int code) {
        super(ID, EXTENDED_DESCRIPTION[code], getImg(code), -2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.code = code;
    }

    private static String getImg(int code) {
        String[] imgs = {"Erika","Mars","Seofon","Octrice","Albert","MistolinaBayleon","Leod","JiemonThiefLord"};
        return "img/cards/" + imgs[code] + ".png";
    }

    public void showGroup() {
        AbstractDungeon.closeCurrentScreen();
        AbstractDungeon.closeCurrentScreen();
        if (AbstractDungeon.getCurrRoom().rewardTime) {
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }

        if (Shadowverse.banCardGroupScreen == null) {
            Shadowverse.banCardGroupScreen = new ViewBanCardScreen();
        }

        Shadowverse.banCardGroupScreen.open((ArrayList) BanCardHelper.royalCardGroupPool.get(this.code));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        super.use(p, m);
    }

    public boolean canUpgrade() {
        return false;
    }

    public void upgrade() {
    }

    public AbstractCard makeCopy() {
        return new BanCardView(this.code);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BanCardView");
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }
}

