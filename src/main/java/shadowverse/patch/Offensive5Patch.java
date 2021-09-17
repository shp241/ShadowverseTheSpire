package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.GameOverScreen;
import shadowverse.relics.Offensive5;

public class Offensive5Patch {
    @SpirePatch(clz = GameOverScreen.class, method = "checkScoreBonus")
    public static class O5 {
        @SpirePostfixPatch
        public static void Check(boolean victory) {
            if (AbstractDungeon.player.hasRelic(Offensive5.ID)){
                for (AbstractRelic r:AbstractDungeon.player.relics){
                    if (r instanceof Offensive5){
                       ((Offensive5) r).initGroup(true);
                    }
                }
            }
        }
    }
}
