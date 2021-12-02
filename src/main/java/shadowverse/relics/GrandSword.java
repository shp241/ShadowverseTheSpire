package shadowverse.relics;


import basemod.abstracts.CustomRelic;
import com.evacipated.cardcrawl.mod.stslib.relics.OnLoseBlockRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class GrandSword
        extends CustomRelic implements OnLoseBlockRelic {
    public static final String ID = "shadowverse:GrandSword";
    public static final String IMG = "img/relics/GrandSword.png";
    public static final String OUTLINE_IMG = "img/relics/outline/GrandSword_Outline.png";
    private boolean triggered = false;

    public GrandSword() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.SOLID);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public void atTurnStart() {
        triggered = false;
    }

    public AbstractRelic makeCopy() {
        return (AbstractRelic) new GrandSword();
    }

    @Override
    public int onLoseBlock(DamageInfo damageInfo, int i) {
        if (!triggered){
            if (damageInfo.owner!=AbstractDungeon.player&&damageInfo.type== DamageInfo.DamageType.NORMAL){
                flash();
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)damageInfo.owner, new DamageInfo((AbstractCreature)AbstractDungeon.player, i, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                triggered = true;
            }
        }
        return i;
    }
}

