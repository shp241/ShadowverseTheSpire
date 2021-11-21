package shadowverse.action;
import charbosses.actions.RealWaitAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import shadowverse.Shadowverse;
import shadowverse.orbs.AmuletOrb;

public class PlaceAmulet extends AbstractGameAction {
    private final AbstractCard card;

    private final CardGroup source;

    private final boolean hadRetain;

    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:AmuletText")).TEXT;

    public PlaceAmulet(AbstractCard card) {
        this(card, (CardGroup)null);
    }

    public PlaceAmulet(AbstractCard card, CardGroup source) {
        this.card = card;
        this.source = source;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.hadRetain = card.retain;
        if (source != null && source.type == CardGroup.CardGroupType.HAND)
            card.retain = true;
    }

    public void update() {
        if (Shadowverse.canSpawnAmuletOrb()) {
            if (!AbstractDungeon.player.hasEmptyOrb())
                for (AbstractOrb o : AbstractDungeon.player.orbs) {
                    if (!(o instanceof AmuletOrb)) {
                        AbstractDungeon.player.orbs.remove(o);
                        AbstractDungeon.player.orbs.add(0, o);
                        AbstractDungeon.player.evokeOrb();
                        break;
                    }
                }
            AbstractDungeon.actionManager.addToTop((AbstractGameAction)new WaitAction(0.1F));
            AbstractDungeon.actionManager.addToTop((AbstractGameAction)new ChannelAction((AbstractOrb)new AmuletOrb(this.card, this.source)));
        } else {
            this.card.retain = this.hadRetain;
            if (!AbstractDungeon.player.hasEmptyOrb())
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[0], true));
        }
        this.isDone = true;
    }
}