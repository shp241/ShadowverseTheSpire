package shadowverse.cards.Temp;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class AwakenedSeraph extends AbstractAmuletCard {
    public static final String ID = "shadowverse:AwakenedSeraph";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AwakenedSeraph");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/AwakenedSeraph.png";


    public AwakenedSeraph() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.NONE);
        this.countDown = 1;
        this.tags.add(AbstractShadowversePlayer.Enums.AMULET_FOR_ONECE);
        this.cardsToPreview = new RenascentSeraph();
    }



    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }
}
