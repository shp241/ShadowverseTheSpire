package shadowverse.cards.Uncommon;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import shadowverse.Shadowverse;
import shadowverse.action.ReanimateAction;
import shadowverse.characters.Necromancer;
import shadowverse.powers.CarnivalNecromancerPower;


public class CarnivalNecromancer extends CustomCard {
    public static final String ID = "shadowverse:CarnivalNecromancer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CarnivalNecromancer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/CarnivalNecromancer.png";
    private boolean doubleCheck = false;

    public CarnivalNecromancer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.POWER, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public void triggerWhenDrawn() {
        if (Shadowverse.Enhance(3)) {
            super.triggerWhenDrawn();
            setCostForTurn(3);
            applyPowers();
        }
    }

    @Override
    public void applyPowers() {
        if (Shadowverse.Enhance(3))
            setCostForTurn(3);
        else
            resetAttributes();
        super.applyPowers();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.group.contains(this)) {
            setCostForTurn(3);
            applyPowers();
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (AbstractDungeon.player.hasPower("Burst") || AbstractDungeon.player.hasPower("Double Tap") || AbstractDungeon.player.hasPower("Amplified")) {
            doubleCheck = true;
            if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 3) {
                resetAttributes();
                applyPowers();
            }
        } else {
            if (doubleCheck) {
                doubleCheck = false;
            } else {
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 3) {
                    resetAttributes();
                    applyPowers();
                }
            }
        }
    }

    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (EnergyPanel.getCurrentEnergy() >= 3) {
            setCostForTurn(3);
        } else {
            resetAttributes();
        }
        applyPowers();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new CarnivalNecromancerPower((AbstractCreature) abstractPlayer, this.magicNumber), this.magicNumber));
        if (this.costForTurn == 3 && Shadowverse.Enhance(3)) {
            addToBot((AbstractGameAction) new SFXAction("CarnivalNecromancer_EH"));
            addToBot((AbstractGameAction)new ReanimateAction(4));
        }else {
            addToBot((AbstractGameAction) new SFXAction("CarnivalNecromancer"));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new CarnivalNecromancer();
    }
}

