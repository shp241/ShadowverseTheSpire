package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.ValiantFencerAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

import java.util.Iterator;

public class ValiantFencer extends CustomCard {
    public static final String ID = "shadowverse:ValiantFencer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ValiantFencer.png";
    public static final int ENHANCE = 3;
    private boolean doubleCheck = false;


    public ValiantFencer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 7;
        this.magicNumber = this.baseMagicNumber = 7;
        this.tags.add(AbstractShadowversePlayer.Enums.HERO);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            upgradeMagicNumber(2);
        }
    }

    @Override
    public void triggerWhenDrawn() {
        if (Shadowverse.Enhance(ENHANCE)) {
            super.triggerWhenDrawn();
            setCostForTurn(ENHANCE);
            applyPowers();
        }
    }

    @Override
    public void applyPowers() {
        if (Shadowverse.Enhance(ENHANCE)) {
            setCostForTurn(ENHANCE);
        } else {
            resetAttributes();
        }
        super.applyPowers();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.group.contains(this)) {
            setCostForTurn(ENHANCE);
            applyPowers();
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (AbstractDungeon.player.hasPower("Burst") || AbstractDungeon.player.hasPower("Double Tap") || AbstractDungeon.player.hasPower("Amplified")) {
            doubleCheck = true;
            if (EnergyPanel.getCurrentEnergy() - c.costForTurn < ENHANCE) {
                resetAttributes();
                applyPowers();
            }
        } else {
            if (doubleCheck) {
                doubleCheck = false;
            } else {
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < ENHANCE) {
                    resetAttributes();
                    applyPowers();
                }
            }
        }
    }

    @Override
    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (EnergyPanel.getCurrentEnergy() >= ENHANCE) {
            setCostForTurn(ENHANCE);
        } else {
            resetAttributes();
        }
        applyPowers();
    }

    public boolean inDanger() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth <= p.maxHealth / 4) {
            return true;
        }
        if (p instanceof AbstractShadowversePlayer) {
            if (((AbstractShadowversePlayer) p).wrathLastTurn > 0) {
                return true;
            }
        }
        for (AbstractPower pow : p.powers) {
            if (pow.type == AbstractPower.PowerType.DEBUFF) {
                return true;
            }
        }
        for (AbstractCard c : p.hand.group) {
            if (c.type == CardType.CURSE || (c.type == CardType.STATUS && !"shadowverse:EvolutionPoint".equals(c.cardID))) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Shadowverse.Enhance(ENHANCE) && this.costForTurn == ENHANCE) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_EH"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        addToBot((AbstractGameAction) new GainBlockAction(p, this.block));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
        }
        if (inDanger()) {
            addToBot((AbstractGameAction) new GainBlockAction(p, this.block));
            for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
                this.addToBot(new ApplyPowerAction(mo, p, new WeakPower(mo, 1, false), 1, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
        if (Shadowverse.Enhance(ENHANCE) && this.costForTurn == ENHANCE) {
            if (p.hand.group.size() < this.magicNumber) {
                addToBot((AbstractGameAction) new DrawCardAction(this.magicNumber - p.hand.group.size()));
            }
            addToBot(new ValiantFencerAction());
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ValiantFencer();
    }
}

