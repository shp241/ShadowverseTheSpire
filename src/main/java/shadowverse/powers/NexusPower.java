package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.HashMap;
import java.util.Map;


public class NexusPower
        extends AbstractPower {
    public static final String POWER_ID = "shadowverse:NexusPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:NexusPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public HashMap<String,Integer> monsterGroup;

    public NexusPower(AbstractCreature owner, HashMap<String,Integer> monsterGroup) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.monsterGroup = monsterGroup;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/NervaPower.png");
    }

    public void updateDescription() {
        StringBuilder nexusSb = new StringBuilder("已肃清：");
        if (this.monsterGroup.size()!=0){
            for (Map.Entry<String,Integer> nexus :this.monsterGroup.entrySet()){
                nexusSb.append(nexus.getKey());
                nexusSb.append("\b #b");
                nexusSb.append(nexus.getValue());
                nexusSb.append(" 次 NL ");
            }
        }
        this.description = DESCRIPTIONS[0]+nexusSb;
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        int buffedDamage = 0;
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (!mo.isDeadOrEscaped()){
                if (this.monsterGroup.containsKey(mo.name)){
                    buffedDamage += (float)this.monsterGroup.get(mo.name);
                }
            }
        }
        return damage+buffedDamage;
    }

}

