package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheEnding;

public class TheEndPatch {
    @SpirePatch(clz = TheEnding.class, method = "initializeBoss")
    public static class InsertKMR {
        @SpirePrefixPatch
        public static SpireReturn DeleteHeart(TheEnding ending) {
            return SpireReturn.Return(null);
        }
    }

    @SpirePatch(clz = TheBeyond.class, method = "initializeBoss")
    public static class InsertSV {
        @SpirePrefixPatch
        public static SpireReturn DeleteBeyond(TheBeyond beyond) {
            return SpireReturn.Return(null);
        }
    }
}
