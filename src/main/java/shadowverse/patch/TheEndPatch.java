package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import shadowverse.cards.Temp.BlackArtifact;
import shadowverse.cards.Temp.WhiteArtifact;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class TheEndPatch {
    @SpirePatch(clz = TheEnding.class, method = "initializeBoss")
    public static class InsertKMR {
        @SpirePostfixPatch
        public static void DeleteHeart(TheEnding ending) throws NoSuchFieldException, IllegalAccessException {
            Field bossList = ending.getClass().getDeclaredField("bossList");
            bossList.setAccessible(true);
            ArrayList<String> list = (ArrayList<String>) bossList.get(ending);
            if (list != null){
                for (int i=0;i<list.size();i++){
                    if ("The Heart".equals(list.get(i)))
                        list.remove(i);
                }
            }
        }
    }
}
