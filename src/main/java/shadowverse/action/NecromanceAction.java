package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.powers.Cemetery;
import shadowverse.powers.GremoryPower;
import shadowverse.powers.GremoryUsedPower;
import shadowverse.relics.Alice;

import java.util.ArrayList;

public class NecromanceAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;
    private int necromance;
    private ArrayList<AbstractGameAction> useNecromanceAction = new ArrayList<AbstractGameAction>();
    private AbstractGameAction commonAction;

    public NecromanceAction(int necromance, AbstractGameAction commonAction, AbstractGameAction... useNecromanceAction) {
        this.necromance = necromance;
        for (AbstractGameAction action:useNecromanceAction){
            this.useNecromanceAction.add(action);
        }
        this.commonAction = commonAction;
    }



    @Override
    public void update() {
        int playerNecromance = 0;
        if (p.hasPower(Cemetery.POWER_ID)){
            for (AbstractPower p :p.powers){
                if (p.ID.equals(Cemetery.POWER_ID))
                    playerNecromance = p.amount;
            }
        }
        if (playerNecromance >= this.necromance){
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Cemetery((AbstractCreature)p, -necromance), -necromance));
            if (this.useNecromanceAction!=null&&this.useNecromanceAction.size()!=0){
                for (int i=0;i<this.useNecromanceAction.size();i++){
                    addToBot(this.useNecromanceAction.get(i));
                }
            }
            if (this.p.hasPower(GremoryPower.POWER_ID)){
                addToBot((AbstractGameAction)new RemoveSpecificPowerAction(p,p,GremoryPower.POWER_ID));
                addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new GremoryUsedPower(p)));
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Cemetery((AbstractCreature)p, necromance), necromance));
                addToBot((AbstractGameAction)new GainEnergyAction(2));
            }
            if (this.p.hasRelic(Alice.ID)){
                addToBot((AbstractGameAction)new DamageAllEnemiesAction(null,
                        DamageInfo.createDamageMatrix(3, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }
        else{
                addToBot(commonAction);
        }
        this.isDone = true;
    }
}
