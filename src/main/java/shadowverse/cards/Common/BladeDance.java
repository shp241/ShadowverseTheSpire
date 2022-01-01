package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.MinionBuffAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.orbs.Minion;
import shadowverse.powers.DualbladePower;

public class BladeDance
        extends CustomCard {
    public static final String ID = "shadowverse:BladeDance";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BladeDance");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/BladeDance.png";
    private boolean doubleCheck = false;

    public BladeDance() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 3;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
        }
    }

    public void triggerWhenDrawn() {
        if (Shadowverse.Enhance(2)) {
            super.triggerWhenDrawn();
            setCostForTurn(2);
            applyPowers();
        }
    }
    @Override
    public void applyPowers(){
        if (Shadowverse.Enhance(2))
            setCostForTurn(2);
        else
            resetAttributes();
        super.applyPowers();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.group.contains(this)){
            if (Shadowverse.Enhance(2)) {
                super.triggerWhenDrawn();
                setCostForTurn(2);
                applyPowers();
            }
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
            doubleCheck = true;
            if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                resetAttributes();
                applyPowers();
            }
        }else {
            if (doubleCheck) {
                doubleCheck = false;
            }else {
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                    resetAttributes();
                    applyPowers();
                }
            }
        }
    }

    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (EnergyPanel.getCurrentEnergy() >= 2) {
            setCostForTurn(2);
        }  else {
            resetAttributes();
        }
        applyPowers();
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        int atk = 0;
        AbstractOrb target = null;
        for (AbstractOrb o:p.orbs){
            if (o instanceof Minion){
                if (((Minion) o).attack>atk){
                    atk = ((Minion) o).attack;
                    target = o;
                }
            }
        }
        if (this.costForTurn == 2 && Shadowverse.Enhance(2) && target!=null) {
            atk+=2;
            AbstractDungeon.actionManager.addToBottom(new MinionBuffAction(2, 0, (Minion)target));
            addToBot((AbstractGameAction)new SFXAction("BladeDance_EH"));
        }else {
            addToBot((AbstractGameAction)new SFXAction("BladeDance"));
        }
        if (atk>10)
            atk=10;
        for (int i = 0; i < atk; i++) {
            addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)this, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new BladeDance();
    }
}


