package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
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
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import shadowverse.cards.Temp.Fairy;
import shadowverse.cards.Temp.ForestBat;
import shadowverse.cards.Temp.Whisp;
import shadowverse.characters.Elf;
import shadowverse.characters.Vampire;
import shadowverse.powers.AriaPower;
import shadowverse.powers.NightVampirePower;


public class NightVampire extends CustomCard {
    public static final String ID = "shadowverse:NightVampire";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NightVampire");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/NightVampire.png";

    public NightVampire() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = (AbstractCard) new ForestBat();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("NightVampire"));
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        c.baseDamage = 8;
        c.applyPowers();
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(c, 2));
        if (!abstractPlayer.hasPower(NightVampirePower.POWER_ID))
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new NightVampirePower((AbstractCreature) abstractPlayer)));
        if (EnergyPanel.getCurrentEnergy()-this.costForTurn>=3){
            addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(new DarkfeastBat(),1));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new NightVampire();
    }
}

