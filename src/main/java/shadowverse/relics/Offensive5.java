package shadowverse.relics;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.stances.AbstractStance;
import shadowverse.cards.Temp.Nerva;
import shadowverse.cards.Temp.Nexus;
import shadowverse.powers.NervaPower;
import shadowverse.powers.NexusPower;
import shadowverse.stance.Resonance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Offensive5
        extends CustomRelic {
    public static final String ID = "shadowverse:Offensive5";
    public static final String IMG = "img/relics/Offensive.png";
    public static final String OUTLINE_IMG = "img/relics/outline/Offensive_Outline.png";
    public static HashMap<String, Integer> nexusGroup = new HashMap<>();
    public static HashMap<String, Integer> nervaGroup = new HashMap<>();
    private boolean first = true;

    public Offensive5() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.STARTER, LandingSound.CLINK);
    }


    public String getUpdatedDescription() {
        StringBuilder nervaSb = new StringBuilder("");
        StringBuilder nexusSb = new StringBuilder("");
        if(AbstractDungeon.player!=null&&AbstractDungeon.player.hasRelic(this.relicId)){
            if (!nervaGroup.isEmpty()&& !(nervaGroup.size() <=0)) {
                for (Map.Entry<String, Integer> nerva : nervaGroup.entrySet()) {
                    nervaSb.append(nerva.getKey());
                    nervaSb.append("：\b #b");
                    nervaSb.append(nerva.getValue());
                    nervaSb.append(" 次 NL ");
                }
            }
            if (!nexusGroup.entrySet().isEmpty()&&!(nexusGroup.size()<=0)) {
                for (Map.Entry<String, Integer> nexus : nexusGroup.entrySet()) {
                    nexusSb.append(nexus.getKey());
                    nexusSb.append("：\b #b");
                    nexusSb.append(nexus.getValue());
                    nexusSb.append(" 次 NL ");
                }
            }
        }
        String des = this.DESCRIPTIONS[0] + "已肃清：" + nexusSb + " NL 已安宁：" + nervaSb;
        return des;
    }

    public void initGroup(boolean isEnd){
        if (isEnd){
            nervaGroup.clear();
            nexusGroup.clear();
        }
    }

    public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
        if (newStance.ID.equals(Resonance.STANCE_ID) && first) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            stanceChoices.add(new Nexus());
            stanceChoices.add(new Nerva());
            addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
            first = false;
        }
    }

    public void onMonsterDeath(AbstractMonster m) {
        if (!m.hasPower(MinionPower.POWER_ID)) {
            if (AbstractDungeon.player.hasPower(NervaPower.POWER_ID)) {
                if (!this.nervaGroup.containsKey(m.name)) {
                    this.nervaGroup.put(m.name, 1);
                } else {
                    int val = this.nervaGroup.get(m.name) + 1;
                    this.nervaGroup.remove(m.name);
                    this.nervaGroup.put(m.name, val);
                }
            } else if (AbstractDungeon.player.hasPower(NexusPower.POWER_ID)) {
                if (!this.nexusGroup.containsKey(m.name)) {
                    this.nexusGroup.put(m.name, 1);
                } else {
                    int val = this.nexusGroup.get(m.name) + 1;
                    this.nexusGroup.remove(m.name);
                    this.nexusGroup.put(m.name,val);
                }
            }
        }
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        initializeTips();
    }

    @Override
    public void onVictory() {
        this.first = true;
    }

    public void onEnterRoom(AbstractRoom room) {
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        initializeTips();
    }

    public void atBattleStart() {
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        initializeTips();
    }


    public AbstractRelic makeCopy() {
        return (AbstractRelic) new Offensive5();
    }
}


