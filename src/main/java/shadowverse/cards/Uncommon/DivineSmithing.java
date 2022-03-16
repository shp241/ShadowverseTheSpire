package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.DivineSmithingAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;

public class DivineSmithing
        extends CustomCard {
    public static final String ID = "shadowverse:DivineSmithing";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DivineSmithing");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DivineSmithing.png";

    public DivineSmithing() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(2)) {
            setCostForTurn(1);
        } else {
            setCostForTurn(0);
        }
        super.update();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("DivineSmithing"));
        if (this.costForTurn == 1 && Shadowverse.Enhance(1)) {
            addToBot((AbstractGameAction) new DivineSmithingAction(true));
        } else {
            addToBot((AbstractGameAction) new DivineSmithingAction(false));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DivineSmithing();
    }
}


