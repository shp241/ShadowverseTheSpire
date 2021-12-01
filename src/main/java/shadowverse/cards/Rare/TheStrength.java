package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import shadowverse.Shadowverse;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.cards.Temp.Clarke_Accelerate;
import shadowverse.cards.Temp.SomnolentStrength;
import shadowverse.cards.Temp.TheStrength_Acc;
import shadowverse.characters.Bishop;
import shadowverse.powers.AbdielPower;
import shadowverse.powers.TheStrengthPower;

public class TheStrength
        extends CustomCard {
    public static final String ID = "shadowverse:TheStrength";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheStrength");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TheStrength.png";
    public boolean doubleCheck = false;

    public TheStrength() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new SomnolentStrength();
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
            doubleCheck = true;
            if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                setCostForTurn(1);
                this.type = CardType.SKILL;
                applyPowers();
            }
        }else {
            if (doubleCheck) {
                doubleCheck = false;
            }else {
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                    setCostForTurn(1);
                    this.type = CardType.SKILL;
                    applyPowers();
                }
            }
        }
    }


    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (EnergyPanel.getCurrentEnergy() >= 2 && this.type != CardType.POWER) {
            resetAttributes();
            this.type = CardType.POWER;
            applyPowers();
        }
    }

    public void triggerWhenDrawn() {
        if (Shadowverse.Accelerate((AbstractCard)this)) {
            super.triggerWhenDrawn();
            setCostForTurn(1);
            this.type = CardType.SKILL;
        } else {
            this.type = CardType.POWER;
        }
        applyPowers();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.group.contains(this)){
            if (EnergyPanel.getCurrentEnergy()<2) {
                setCostForTurn(1);
                this.type = CardType.SKILL;
            } else {
                resetAttributes();
                this.type = CardType.POWER;
            }
            applyPowers();
        }
    }

    public void onMoveToDiscard() {
        resetAttributes();
        this.type = CardType.POWER;
        applyPowers();
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
            addToBot((AbstractGameAction)new SFXAction("TheStrength_Acc"));
            addToBot((AbstractGameAction)new PlaceAmulet(this.cardsToPreview.makeStatEquivalentCopy(),null));
        }else {
            addToBot((AbstractGameAction)new SFXAction("TheStrength"));
            addToBot((AbstractGameAction)new VFXAction(new HeartBuffEffect(p.hb.cX, p.hb.cY)));
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.GOLDENROD, true)));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new TheStrengthPower((AbstractCreature) p)));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new StrengthPower((AbstractCreature) p,this.magicNumber),this.magicNumber));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) p, (AbstractCreature) p, (AbstractPower) new DexterityPower((AbstractCreature) p,this.magicNumber),this.magicNumber));
        }
    }

    public AbstractCard makeSameInstanceOf() {
        AbstractCard card = null;
        if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
            card = (new TheStrength_Acc()).makeStatEquivalentCopy();
            card.uuid = (new TheStrength_Acc()).uuid;
        } else {
            card = makeStatEquivalentCopy();
            card.uuid = this.uuid;
        }
        return card;
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new TheStrength();
    }
}


