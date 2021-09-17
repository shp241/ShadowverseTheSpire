package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;

public class IryaPower extends AbstractPower implements OnLoseBlockPower {
    public static final String POWER_ID = "shadowverse:IryaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:IryaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean lostCheck = false;

    public IryaPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/IryaPower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (info.owner == this.owner) {
            this.lostCheck = true;
        }
    }



    public void atStartOfTurn() {
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
        if (lostCheck){
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)this.owner, (AbstractGameEffect)new InflameEffect((AbstractCreature)this.owner), 1.0F));
            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,(AbstractPower)new StrengthPower(this.owner,1),1));
            addToBot((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,(AbstractPower)new DexterityPower(this.owner,1),1));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1];
    }

    @Override
    public int onLoseBlock(DamageInfo damageInfo, int i) {
        if (damageInfo.owner!=null&&damageInfo.type!=DamageInfo.DamageType.THORNS&&damageInfo.owner!=this.owner){
            if (i<this.owner.currentBlock){
                flash();
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new NextTurnBlockPower((AbstractCreature)this.owner, this.amount), this.amount));
            }
        }
        return i;
    }
}
