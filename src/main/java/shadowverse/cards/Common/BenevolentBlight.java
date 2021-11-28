package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import shadowverse.Shadowverse;
import shadowverse.cards.Temp.GildedBlade;
import shadowverse.cards.Temp.GildedBoots;
import shadowverse.cards.Temp.GildedGoblet;
import shadowverse.cards.Temp.GildedNecklace;
import shadowverse.characters.Bishop;
import shadowverse.characters.Royal;

import java.util.ArrayList;

public class BenevolentBlight extends CustomCard {
    public static final String ID = "shadowverse:BenevolentBlight";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BenevolentBlight");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BenevolentBlight.png";
    private boolean doubleCheck = false;


    public BenevolentBlight() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 7;
        this.isMultiDamage = true;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }



    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
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
    public void applyPowers(){
        if (Shadowverse.Enhance(3))
            setCostForTurn(3);
        else
            resetAttributes();
        super.applyPowers();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.group.contains(this)){
            if (Shadowverse.Enhance(3)) {
                super.triggerWhenDrawn();
                setCostForTurn(3);
                applyPowers();
            }
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
            doubleCheck = true;
            if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 3) {
                resetAttributes();
                applyPowers();
            }
        }else {
            if (doubleCheck) {
                doubleCheck = false;
            }else {
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
        }  else {
            resetAttributes();
        }
        applyPowers();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new VFXAction(new MiracleEffect()));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        if (this.costForTurn == 3 && Shadowverse.Enhance(3)) {
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
            addToBot((AbstractGameAction)new HealAction(p,p,this.magicNumber*2));
            addToBot((AbstractGameAction)new DrawCardAction(2));
        }else {
            addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
            addToBot((AbstractGameAction)new HealAction(p,p,this.magicNumber));
            addToBot((AbstractGameAction)new DrawCardAction(1));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new BenevolentBlight();
    }
}
