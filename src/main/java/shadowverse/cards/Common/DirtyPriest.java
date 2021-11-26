package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import charbosses.actions.RealWaitAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.Curse.Indulgence;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class DirtyPriest
        extends CustomCard implements AbstractCrystalizeCard {
    public static final String ID = "shadowverse:DirtyPriest";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DirtyPriest");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/DirtyPriest.png";

    public DirtyPriest() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 12;
        this.cardsToPreview = new Indulgence();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (EnergyPanel.getCurrentEnergy()>=2){
            if (this.costForTurn>0 && this.type == CardType.POWER){
                this.type = CardType.ATTACK;
                resetAttributes();
            }
        }else if (EnergyPanel.getCurrentEnergy()<2){
            if (this.costForTurn>=2 && this.type == CardType.ATTACK){
                this.type = CardType.POWER;
                this.costForTurn = 0;
                this.isCostModifiedForTurn = true;
            }
        }
    }

    @Override
    public void onMoveToDiscard() {
        super.onMoveToDiscard();
        this.type = CardType.ATTACK;
        resetAttributes();
    }

    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (EnergyPanel.getCurrentEnergy() >= 2 && this.type != CardType.ATTACK) {
            resetAttributes();
            this.type = CardType.ATTACK;
            applyPowers();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (this.type==CardType.POWER && this.costForTurn == 0){
            addToBot((AbstractGameAction)new SFXAction("DirtyPriest_Acc"));
        }else {
            addToBot((AbstractGameAction)new SFXAction("DirtyPriest"));
            addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
            addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(this.cardsToPreview,1,true,true,false));
            addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(this.cardsToPreview,1));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new DirtyPriest();
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {
    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {
    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return healAmount;
    }

    @Override
    public int returnCountDown() {
        return 2;
    }
}


