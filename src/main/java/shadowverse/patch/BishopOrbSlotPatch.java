package shadowverse.patch;

import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.vfx.BobEffect;
import shadowverse.characters.Bishop;

public class BishopOrbSlotPatch {
    private static final Texture sequenceSlot = ImageMaster.loadImage("img/ui/sequenceSlot.png");

    private static final BobEffect bobEffect = new BobEffect(3.0F * Settings.scale, 3.0F);

    @SpirePatch(clz = EmptyOrbSlot.class, method = "render")
    public static class AmuletSlot {
        @SpirePrefixPatch
        public static SpireReturn ReplaceSlot(EmptyOrbSlot slot, SpriteBatch sb) {
            if (AbstractDungeon.player != null && AbstractDungeon.player instanceof Bishop){
                sb.setColor(Color.WHITE.cpy());
                sb.draw(sequenceSlot, slot.cX - 48.0F - bobEffect.y / 8.0F, slot.cY - 48.0F + bobEffect.y / 8.0F, 0.0F, 0.0F, sequenceSlot.getWidth() * Settings.scale, sequenceSlot.getHeight() * Settings.scale, 1.0F, 1.0F, 0.0F, 0, 0, sequenceSlot.getWidth(), sequenceSlot.getHeight(), false, false);
                slot.hb.render(sb);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }
    }
}
