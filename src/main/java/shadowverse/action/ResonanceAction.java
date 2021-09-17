package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.PrismaticShard;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.stance.Resonance;

public class ResonanceAction extends AbstractGameAction {

    public ResonanceAction(){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }
    @Override
    public void update() {
        if (AbstractDungeon.player instanceof Nemesis||AbstractDungeon.player.hasRelic(PrismaticShard.ID)){
            if (AbstractDungeon.player.drawPile.group.size()%2==0&&!AbstractDungeon.player.stance.ID.equals(Resonance.STANCE_ID)){
                addToBot((AbstractGameAction)new ChangeStanceAction((AbstractStance)new Resonance()));
                if (AbstractDungeon.player instanceof AbstractShadowversePlayer)
                    ((AbstractShadowversePlayer) AbstractDungeon.player).resonanceCount++;
            }else if (AbstractDungeon.player.drawPile.group.size()%2!=0&&AbstractDungeon.player.stance.ID.equals(Resonance.STANCE_ID)){
                addToBot((AbstractGameAction)new ChangeStanceAction((AbstractStance)new NeutralStance()));
            }
        }
        this.isDone = true;
    }

}
