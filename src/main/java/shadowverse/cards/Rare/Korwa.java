package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.cards.Temp.Fil;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;
import shadowverse.powers.FilPower;


public class Korwa extends CustomCard {
    public static final String ID = "shadowverse:Korwa";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Korwa");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Korwa.png";
    private boolean doubleCheck = false;

    public Korwa() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
        this.cardsToPreview = (AbstractCard) new Fil();
    }


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
                Shadowverse.Enhance(3)) {
            setCostForTurn(3);
        } else {
            if (this.costForTurn>1){
                setCostForTurn(2);
            }
        }
        super.update();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Korwa"));
        if (!abstractPlayer.hasPower("shadowverse:FilPower"))
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new FilPower((AbstractCreature) abstractPlayer)));
        if (Shadowverse.Enhance(3) && this.costForTurn == 3) {
            addToBot((AbstractGameAction) new MakeTempCardInHandAction((AbstractCard) new Fil(), 3));
            addToBot((AbstractGameAction) new GainEnergyAction(1));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Korwa();
    }
}

