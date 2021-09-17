package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;

public class ArchdemonPower extends AbstractPower implements OnLoseBlockPower {
    public static final String POWER_ID = "shadowverse:ArchdemonPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ArchdemonPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ArchdemonPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/ArchdemonPower.png");
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        updateDescription();
    }



    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] ;
    }

    @Override
    public void atStartOfTurn(){
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
    }

    @Override
    public int onLoseBlock(DamageInfo damageInfo, int i) {
            if (i<this.owner.currentBlock&& damageInfo.owner!=this.owner){
                flash();
                addToBot((AbstractGameAction)new SFXAction("ArchdemonPower"));
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new NextTurnBlockPower((AbstractCreature)this.owner, this.amount), this.amount));
                addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ClashEffect(damageInfo.owner.hb.cX, damageInfo.owner.hb.cY)));
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature) damageInfo.owner, new DamageInfo((AbstractCreature)this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
            }

        return i;
    }
}
