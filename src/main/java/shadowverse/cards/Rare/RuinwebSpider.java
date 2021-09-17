package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.EntangleEffect;
import shadowverse.Shadowverse;
import shadowverse.cards.Temp.RuinwebSpider_Acc;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.RuinwebSpiderPower;


public class RuinwebSpider
        extends CustomCard {
    public static final String ID = "shadowverse:RuinwebSpider";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RuinwebSpider");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RuinwebSpider.png";
    public boolean doubleCheck = false;

    public RuinwebSpider() {
        super(ID, NAME, IMG_PATH, 5, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.tags.add(AbstractShadowversePlayer.Enums.CRYSTALLIZE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (AbstractDungeon.player.hasPower("Burst") || AbstractDungeon.player.hasPower("Double Tap") || AbstractDungeon.player.hasPower("Amplified")) {
            doubleCheck = true;
            if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                setCostForTurn(0);
                this.type = CardType.POWER;
                applyPowers();
            }
        } else {
            if (doubleCheck) {
                doubleCheck = false;
            } else {
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                    setCostForTurn(0);
                    this.type = CardType.POWER;
                    applyPowers();
                }
            }
        }
    }

    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (EnergyPanel.getCurrentEnergy() >= 5 && this.type != CardType.ATTACK) {
            resetAttributes();
            this.type = CardType.ATTACK;
            applyPowers();
        }
    }

    public void triggerWhenDrawn() {
        if (Shadowverse.Accelerate((AbstractCard) this)) {
            super.triggerWhenDrawn();
            setCostForTurn(0);
            this.type = CardType.POWER;
        } else {
            this.type = CardType.ATTACK;
        }
        applyPowers();
    }

    public void onMoveToDiscard() {
        resetAttributes();
        this.type = CardType.ATTACK;
        applyPowers();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.POWER) {
            addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new RuinwebSpiderPower(abstractPlayer,this.magicNumber),this.magicNumber));
        } else {
            addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new EntangleEffect(abstractPlayer.hb.cX - 70.0F * Settings.scale, abstractPlayer.hb.cY + 10.0F * Settings.scale, mo.hb.cX, mo.hb.cY), 0.5F));
            }
        }
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.group.contains(this)) {
            if (EnergyPanel.getCurrentEnergy() < 5) {
                setCostForTurn(0);
                this.type = CardType.POWER;
            } else {
                resetAttributes();
                this.type = CardType.ATTACK;
            }
            applyPowers();
        }
    }

    public AbstractCard makeSameInstanceOf() {
        AbstractCard card = null;
        if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.POWER) {
            card = (new RuinwebSpider_Acc()).makeStatEquivalentCopy();
            card.uuid = (new RuinwebSpider_Acc()).uuid;
        } else {
            card = makeStatEquivalentCopy();
            card.uuid = this.uuid;
        }
        return card;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new RuinwebSpider();
    }
}



