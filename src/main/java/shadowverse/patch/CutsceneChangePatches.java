//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package shadowverse.patch;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.cutscenes.Cutscene;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import shadowverse.characters.Royal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SpirePatch(
        clz = Cutscene.class,
        method = "<ctor>"
)
public class CutsceneChangePatches {
    public CutsceneChangePatches() {
    }

    @SpirePostfixPatch
    public static void patch(Cutscene __instance, PlayerClass chosenClass) {
        if (chosenClass == Royal.Enums.Royal) {
            Texture customBg = ImageMaster.loadImage("images/scenes/royalBg.jpg");
            if (customBg != null) {
                try {
                    Field f = Cutscene.class.getDeclaredField("bgImg");
                    f.setAccessible(true);
                    Texture oldBg = (Texture) f.get(__instance);
                    oldBg.dispose();
                    f.set(__instance, customBg);
                } catch (NoSuchFieldException | IllegalAccessException var8) {
                    var8.printStackTrace();
                }
            }

            List<CutscenePanel> customPanels = new ArrayList();
            customPanels.add(new CutscenePanel("img/scene/royal1.png"));
            customPanels.add(new CutscenePanel("img/scene/royal2.png"));
            customPanels.add(new CutscenePanel("img/scene/royal3.png"));
            if (customPanels != null) {
                try {
                    Field f = Cutscene.class.getDeclaredField("panels");
                    f.setAccessible(true);
                    ArrayList<CutscenePanel> panels = (ArrayList) f.get(__instance);
                    Iterator var6 = panels.iterator();

                    while (var6.hasNext()) {
                        CutscenePanel panel = (CutscenePanel) var6.next();
                        panel.dispose();
                    }

                    panels.clear();
                    panels.addAll(customPanels);
                } catch (NoSuchFieldException | IllegalAccessException var9) {
                    var9.printStackTrace();
                }
            }
        }

    }
}
