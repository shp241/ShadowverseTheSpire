package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class IminaPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:IminaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:IminaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public IminaPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = NeutralPowertypePatch.NEUTRAL;
        updateDescription();
        this.img = new Texture("img/powers/IminaPower.png");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
       if (isPlayer){
           if (this.owner==AbstractDungeon.player){
               AbstractMonster m = AbstractDungeon.getRandomMonster();
               if (m!=null&&!m.isDeadOrEscaped()){
                   addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
                   addToBot((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,(AbstractPower)new StrengthPower(this.owner,-2),-2));
                   addToBot((AbstractGameAction)new ApplyPowerAction(m,this.owner,(AbstractPower)new IminaPower(m)));
               }
           }
       }else {
           if (this.owner!=AbstractDungeon.player){
               addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
               addToBot((AbstractGameAction)new ApplyPowerAction(this.owner,this.owner,(AbstractPower)new StrengthPower(this.owner,-2),-2));
               addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,this.owner,(AbstractPower)new IminaPower(AbstractDungeon.player)));
           }
       }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
