package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.EscapePlanAction;
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
import shadowverse.action.EleganceInActionAction;
import shadowverse.cards.Rare.Albert;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class EleganceInAction extends CustomCard {
    public static final String ID = "shadowverse:EleganceInAction";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EleganceInAction.png";


    public EleganceInAction() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseDamage = 3;
        this.baseMagicNumber = this.magicNumber = 2;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(1)) {
            setCostForTurn(1);
        } else {
            setCostForTurn(0);
        }
        super.update();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Enhance(1) && this.costForTurn == 1) {
            for (int i = 0; i < this.magicNumber; i++) {
                this.addToBot(new DrawCardAction(1, new EleganceInActionAction(this)));
            }
        } else {
            this.addToBot(new DrawCardAction(1, new EleganceInActionAction(this)));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new EleganceInAction();
    }
}




