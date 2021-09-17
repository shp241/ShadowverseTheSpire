package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.HashMap;
import java.util.Map;


public class NervaPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:NervaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:NervaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public HashMap<String,Integer> monsterGroup;

    public NervaPower(AbstractCreature owner, HashMap<String,Integer> monsterGroup) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.monsterGroup = monsterGroup;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/NexusPower.png");
    }

    public void updateDescription() {
        StringBuilder nervaSb = new StringBuilder("已安宁：");
        if (this.monsterGroup.size()!=0){
            for (Map.Entry<String,Integer> nerva :this.monsterGroup.entrySet()){
                nervaSb.append(nerva.getKey());
                nervaSb.append("\b #b");
                nervaSb.append(nerva.getValue());
                nervaSb.append(" 次 NL ");
            }
        }
        this.description = DESCRIPTIONS[0]+nervaSb;
    }

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (info.owner instanceof AbstractMonster){
            if (this.monsterGroup.containsKey(info.owner.name)){
                int toReduce = monsterGroup.get(info.owner.name);
                return damageAmount - toReduce;
            }
        }
        return damageAmount;
    }

}

