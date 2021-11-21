package shadowverse.patch;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.orbs.AmuletOrb;

@SpirePatch(clz = AbstractPlayer.class, method = "render")
public class RenderStasisPreviewInFrontPatch {
    @SpirePostfixPatch
    public static void Postfix(AbstractPlayer obj, SpriteBatch sb) {
        for (AbstractOrb o : obj.orbs) {
            if (o instanceof AmuletOrb)
                ((AmuletOrb)o).renderPreview(sb);
        }
    }
}
