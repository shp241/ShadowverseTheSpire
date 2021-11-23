package shadowverse.cards.Temp;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class RenascentSeraph extends AbstractAmuletCard {
    public static final String ID = "shadowverse:RenascentSeraph";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RenascentSeraph");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RenascentSeraph.png";


    public RenascentSeraph() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.NONE);
        this.countDown = 1;
        this.tags.add(AbstractShadowversePlayer.Enums.AMULET_FOR_ONECE);
        this.cardsToPreview = new SeraphLapis();
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
