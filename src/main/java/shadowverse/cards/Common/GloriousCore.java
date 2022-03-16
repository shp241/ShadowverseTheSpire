package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
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
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.cards.Temp.ExterminusWeapon;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.powers.GloriousCorePower;

public class GloriousCore extends CustomCard {
    public static final String ID = "shadowverse:GloriousCore";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GloriousCore");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GloriousCore.png";
    public static final int ENHANCE = 2;

    public GloriousCore() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.cardsToPreview = new EvolutionPoint();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.cardsToPreview.upgrade();
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
        addToBot(new ApplyPowerAction(p, p, new GloriousCorePower(p, this.magicNumber)));
        if (Shadowverse.Enhance(ENHANCE) && this.costForTurn == ENHANCE) {
            this.addToTop(new GainEnergyAction(1));
            this.addToTop(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(), 1));
            if (this.upgraded){
                this.addToTop(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(), 1));
            }
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new GloriousCore();
    }
}

