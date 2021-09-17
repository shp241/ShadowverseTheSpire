package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.action.ResonanceAction;

import java.lang.reflect.Field;

public class ResonancePatch {
    @SpirePatch(clz = DrawCardAction.class, method = "endActionWithFollowUp")
    public static class RPatch{
        @SpireInsertPatch(rloc = 4)
        public static void Insert(DrawCardAction drawCardAction) throws NoSuchFieldException, IllegalAccessException {
            Field followUpAction = drawCardAction.getClass().getDeclaredField("followUpAction");
            followUpAction.setAccessible(true);
            AbstractGameAction f = (AbstractGameAction) followUpAction.get(drawCardAction);
            if (f == null){
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ResonanceAction());
            }
        }
    }
    @SpirePatch(clz = MakeTempCardInDrawPileAction.class, method = "update")
    public static class ResonancePatch2{
        @SpirePostfixPatch
        public static void CheckResonance(MakeTempCardInDrawPileAction makeTempCardInDrawPileAction) {
             AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ResonanceAction());
        }
    }
}
