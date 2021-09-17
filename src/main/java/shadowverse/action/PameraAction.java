package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.DoubleYourBlockAction;
import com.megacrit.cardcrawl.actions.unique.LimitBreakAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PameraAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;

    public PameraAction() {
        if (Settings.FAST_MODE) {
            this.duration = this.startDuration = Settings.ACTION_DUR_XFAST;
        } else {
            this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        }
    }

    @Override
    public void update() {
        if (this.duration == this.startDuration) {
            int count = 0;
            for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                count++;
            }
            if (count>=4){
                addToBot((AbstractGameAction)new SFXAction("PameraPower"));
                addToBot((AbstractGameAction)new DoubleYourBlockAction((AbstractCreature)this.p));
                addToBot((AbstractGameAction)new LimitBreakAction());
            }
        }
        tickDuration();
    }
}
