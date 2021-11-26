package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.defect.EvokeAllOrbsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.orbs.AmuletOrb;

public class SkullFaneAction extends AbstractGameAction {
    private int str;
    private int block;
    public SkullFaneAction(int str,int block) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.BLOCK;
        this.str = str;
        this.block = block;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            int orbCount = 0;
            for (AbstractOrb o : AbstractDungeon.player.orbs){
                if (o instanceof AmuletOrb){
                    if (((AmuletOrb) o).amulet.type== AbstractCard.CardType.CURSE)
                        orbCount++;
                }
            }
            if (orbCount>0){
                addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,orbCount*this.block));
                addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,(AbstractPower)new StrengthPower(AbstractDungeon.player,orbCount*this.block)));
            }
            addToBot(new EvokeAllOrbsAction());

        }
        tickDuration();
    }
}
