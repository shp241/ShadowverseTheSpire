package shadowverse.cards.Temp;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.DestroyAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.powers.MiriamPower;
import shadowverse.stance.Resonance;


public class Miriam_Acc extends CustomCard {
    public static final String ID = "shadowverse:Miriam_Acc";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Miriam");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Miriam.png";

    public Miriam_Acc() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseBlock = 12;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeMagicNumber(2);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Miriam_Acc"));
        addToBot((AbstractGameAction)new DestroyAction(1,(AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new ParadigmShift())));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Miriam_Acc();
    }
}
