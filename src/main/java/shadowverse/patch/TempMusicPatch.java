package shadowverse.patch;

import com.badlogic.gdx.audio.Music;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.audio.MainMusic;
import com.megacrit.cardcrawl.audio.TempMusic;
import shadowverse.Shadowverse;


@SpirePatch(clz = TempMusic.class, method = "getSong")
public class TempMusicPatch {
    @SpirePostfixPatch
    public static SpireReturn<Music> Prefix(TempMusic __instance, String key) {
        if (Shadowverse.tempmusic.containsKey(key)) {
            System.out.println("Contains key in tempmusic");
            return SpireReturn.Return(MainMusic.newMusic((String) Shadowverse.tempmusic.get(key)));
        }
        return SpireReturn.Continue();
    }
}

