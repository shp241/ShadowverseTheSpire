package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.cards.Temp.ProductMachine;
import shadowverse.cards.Temp.TyrantsOrder;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.monsters.Henchman;

public class Nahtnaught extends CustomCard {
    public static final String ID = "shadowverse:Nahtnaught";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Nahtnaught");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Nahtnaught.png";

    public Nahtnaught() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.NONE);
        this.exhaust = true;
        this.cardsToPreview = new TyrantsOrder();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(2)) {
            setCostForTurn(2);
        } else {
            setCostForTurn(1);
        }
        super.update();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Shadowverse.Enhance(2) && this.costForTurn == 2) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Eh"));
            Henchman h1 = new Henchman(-300 - 185.0F * 1, MathUtils.random(-5.0F, 25.0F));
            Henchman h2 = new Henchman(-300 - 185.0F * 2, MathUtils.random(-5.0F, 25.0F));
            addToBot(new SpawnMonsterAction(h1, true));
            addToBot(new SpawnMonsterAction(h2, true));
            h1.getMove();
            h2.getMove();
            h1.createIntent();
            h2.createIntent();
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        this.addToTop(new MakeTempCardInHandAction(new TyrantsOrder(), 1));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Nahtnaught();
    }
}