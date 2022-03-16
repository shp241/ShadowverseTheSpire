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
import com.megacrit.cardcrawl.rooms.AbstractRoom;
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

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(3)) {
            setCostForTurn(3);
        } else {
            setCostForTurn(0);
        }
        super.update();
    }

    public void applyPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        if (Shadowverse.Enhance(3)){
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

