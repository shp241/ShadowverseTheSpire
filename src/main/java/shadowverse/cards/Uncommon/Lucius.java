package shadowverse.cards.Uncommon;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.characters.Vampire;


public class Lucius
        extends CustomCard {
    public static final String ID = "shadowverse:Lucius";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lucius");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Lucius.png";
    private boolean doubleCheck = false;

    public Lucius() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.exhaust = true;
        this.baseDamage = 0;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
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

    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        if (Shadowverse.Enhance(3)){
            setCostForTurn(3);
            int dmg = p.maxHealth - p.currentHealth;
            int realBaseDamage = this.baseDamage;
            this.baseDamage = dmg;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
        } else{
            resetAttributes();
            float percent = 1.0F-((float) p.currentHealth /(float) p.maxHealth);
            int dmg = (int) (20.0F * percent);
            int realBaseDamage = this.baseDamage;
            this.baseDamage = dmg;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
        }
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        AbstractPlayer p = AbstractDungeon.player;
        if (Shadowverse.Enhance(3)){
            setCostForTurn(3);
            int dmg = p.maxHealth - p.currentHealth;
            int realBaseDamage = this.baseDamage;
            this.baseDamage = dmg;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
        } else{
            resetAttributes();
            float percent = 1.0F-((float) p.currentHealth /(float) p.maxHealth);
            int dmg = (int) (20.0F * percent);
            int realBaseDamage = this.baseDamage;
            this.baseDamage = dmg;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
        }
        this.isDamageModified = (this.damage != this.baseDamage);
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (this.costForTurn == 3 && Shadowverse.Enhance(3)) {
            addToBot((AbstractGameAction)new SFXAction("Lucius_EH"));
        }else {
            addToBot((AbstractGameAction) new SFXAction("Lucius"));
        }
        addToBot((AbstractGameAction)new WaitAction(0.8F));
        calculateCardDamage(abstractMonster);
        addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Lucius();
    }
}

