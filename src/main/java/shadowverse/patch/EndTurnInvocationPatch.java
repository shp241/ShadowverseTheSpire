package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.cards.AbstractEndTurnInvocationCard;

import java.util.Iterator;

public class EndTurnInvocationPatch {
    @SpirePatch(clz = AbstractCreature.class, method = "applyEndOfTurnTriggers")
    public static class InvocationPatch{
        @SpireInsertPatch(rloc = 1)
        public static void Insert(AbstractCreature creature){
            Iterator var1 = AbstractDungeon.player.drawPile.group.iterator();

            AbstractCard c;
            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (c != null && c instanceof AbstractEndTurnInvocationCard) {
                    ((AbstractEndTurnInvocationCard) c).atEndOfTurn(creature.isPlayer);
                }
            }


            var1 = AbstractDungeon.player.discardPile.group.iterator();

            while(var1.hasNext()) {
                c = (AbstractCard)var1.next();
                if (c != null && c instanceof AbstractEndTurnInvocationCard) {
                    ((AbstractEndTurnInvocationCard) c).atEndOfTurn(creature.isPlayer);
                }
            }

        }
    }
}
