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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import shadowverse.characters.*;

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
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            Texture customBg = null;
            if (AbstractDungeon.player instanceof Royal){
                customBg = ImageMaster.loadImage("img/scene/royalBg.jpg");
            }else if (AbstractDungeon.player instanceof Witchcraft){
                customBg = ImageMaster.loadImage("img/scene/witchBg.png");
            }else if (AbstractDungeon.player instanceof Elf){
                customBg = ImageMaster.loadImage("img/scene/elfBg.png");
            }else if (AbstractDungeon.player instanceof Necromancer){
                customBg = ImageMaster.loadImage("img/scene/necroBg.png");
            }else if (AbstractDungeon.player instanceof Vampire){
                customBg = ImageMaster.loadImage("img/scene/vampBg.png");
            }else if (AbstractDungeon.player instanceof Nemesis){
                customBg = ImageMaster.loadImage("img/scene/nemesisBg.png");
            }else if (AbstractDungeon.player instanceof Bishop){
                customBg = ImageMaster.loadImage("img/scene/bishopBg.png");
            }
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
            if (AbstractDungeon.player instanceof Royal){
                customPanels.add(new CutscenePanel("img/scene/royal1.png"));
                customPanels.add(new CutscenePanel("img/scene/royal2.png"));
                customPanels.add(new CutscenePanel("img/scene/royal3.png"));
            }else if (AbstractDungeon.player instanceof Witchcraft){
                customPanels.add(new CutscenePanel("img/scene/witch1.png"));
                customPanels.add(new CutscenePanel("img/scene/witch2.png"));
                customPanels.add(new CutscenePanel("img/scene/witch3.png"));
            }else if (AbstractDungeon.player instanceof Elf){
                customPanels.add(new CutscenePanel("img/scene/elf1.png"));
                customPanels.add(new CutscenePanel("img/scene/elf2.png"));
                customPanels.add(new CutscenePanel("img/scene/elf3.png"));
            }else if (AbstractDungeon.player instanceof Necromancer){
                customPanels.add(new CutscenePanel("img/scene/necro1.png"));
                customPanels.add(new CutscenePanel("img/scene/necro2.png"));
                customPanels.add(new CutscenePanel("img/scene/necro3.png"));
            }else if (AbstractDungeon.player instanceof Vampire){
                customPanels.add(new CutscenePanel("img/scene/vamp1.png"));
                customPanels.add(new CutscenePanel("img/scene/vamp2.png"));
                customPanels.add(new CutscenePanel("img/scene/vamp3.png"));
            }else if (AbstractDungeon.player instanceof Nemesis){
                customPanels.add(new CutscenePanel("img/scene/nemesis1.png"));
                customPanels.add(new CutscenePanel("img/scene/nemesis2.png"));
                customPanels.add(new CutscenePanel("img/scene/nemesis3.png"));
            }else if (AbstractDungeon.player instanceof Bishop){
                customPanels.add(new CutscenePanel("img/scene/bishop1.png"));
                customPanels.add(new CutscenePanel("img/scene/bishop2.png"));
                customPanels.add(new CutscenePanel("img/scene/bishop3.png"));
            }
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
