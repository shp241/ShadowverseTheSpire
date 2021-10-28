package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import shadowverse.Shadowverse;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.cards.Temp.FrenziedCorpsmaster_Acc;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.orbs.HeavyKnight;
import shadowverse.orbs.Minion;
import shadowverse.orbs.ShieldGuardian;
import shadowverse.orbs.SteelcladKnight;

public class FrenziedCorpsmaster extends CustomCard {
    public static final String ID = "shadowverse:FrenziedCorpsmaster";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/FrenziedCorpsmaster.png";
    public static final int ACCELERATE = 1;
    public boolean doubleCheck = false;

    public FrenziedCorpsmaster() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseBlock = 8;
        this.baseDamage = 0;
        this.isMultiDamage = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
        }
    }

    public int rally() {
        int rally = 0;

        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Minion) {
                rally++;
            }
        }

        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.type == CardType.ATTACK && !(c.hasTag(CardTags.STRIKE))) {
                rally++;
            }
        }
        return rally;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL) {
//            addToBot(new SFXAction("FrenziedCorpsmaster_Acc"));
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new SteelcladKnight()));
            AbstractDungeon.actionManager.addToBottom(new ChannelAction(new HeavyKnight()));
        } else {
//            addToBot(new SFXAction("FrenziedCorpsmaster"));
            addToBot(new GainBlockAction(p, this.block));
            this.baseDamage = rally() * 2;
            this.calculateCardDamage(null);
            addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, true));
        }
    }


    @Override
    public void applyPowers() {
        super.applyPowers();
        this.baseDamage = rally() * 2;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + this.baseDamage + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        resetAttributes();
        this.type = CardType.ATTACK;
        applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + this.baseDamage + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.group.contains(this)) {
            if (EnergyPanel.getCurrentEnergy() < 2) {
                setCostForTurn(ACCELERATE);
                this.type = CardType.SKILL;
            } else {
                this.type = CardType.ATTACK;
            }
            applyPowers();
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (AbstractDungeon.player.hasPower("Burst") || AbstractDungeon.player.hasPower("Double Tap") || AbstractDungeon.player.hasPower("Amplified")) {
            doubleCheck = true;
            if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                setCostForTurn(ACCELERATE);
                this.type = CardType.SKILL;
                applyPowers();
            }
        } else {
            if (doubleCheck) {
                doubleCheck = false;
            } else {
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                    setCostForTurn(ACCELERATE);
                    this.type = CardType.SKILL;
                    applyPowers();
                }
            }
        }
    }

    @Override
    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (EnergyPanel.getCurrentEnergy() >= 2 && this.type != CardType.ATTACK) {
            resetAttributes();
            this.type = CardType.ATTACK;
            applyPowers();
        }
    }

    @Override
    public void triggerWhenDrawn() {
        if (Shadowverse.Accelerate(this)) {
            super.triggerWhenDrawn();
            setCostForTurn(ACCELERATE);
            this.type = CardType.SKILL;
        } else {
            this.type = CardType.ATTACK;
        }
        applyPowers();
    }

    @Override
    public AbstractCard makeSameInstanceOf() {
        AbstractCard card = null;
        if (Shadowverse.Accelerate(this) && this.type == CardType.SKILL) {
            card = (new FrenziedCorpsmaster_Acc()).makeStatEquivalentCopy();
            card.uuid = (new FrenziedCorpsmaster_Acc()).uuid;
        } else {
            card = makeStatEquivalentCopy();
            card.uuid = this.uuid;
        }
        return card;
    }

    @Override
    public AbstractCard makeCopy() {
        return new FrenziedCorpsmaster();
    }
}

