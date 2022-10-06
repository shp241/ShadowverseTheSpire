package shadowverse.helper;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import javassist.CtBehavior;
import shadowverse.Shadowverse;

import java.util.HashMap;

public class SaveData {
    public static boolean[] group_active;

    public SaveData() {
    }


    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "loadSave"
    )
    public static class loadSave {
        public loadSave() {
        }

        @SpirePostfixPatch
        public static void loadSave(AbstractDungeon __instance, SaveFile file) {
            Shadowverse.groupActive=SaveData.group_active;

        }
    }

    @SpirePatch(
            clz = SaveAndContinue.class,
            method = "loadSaveFile",
            paramtypez = {String.class}
    )
    public static class LoadDataFromFile {
        public LoadDataFromFile() {
        }

        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"gson", "savestr"}
        )
        public static void loadCustomSaveData(String path, Gson gson, String savestr) {
            try {
                ShadowverseSaveData data = (ShadowverseSaveData)gson.fromJson(savestr, ShadowverseSaveData.class);
                SaveData.group_active = data.group_active;
            } catch (Exception var4) {
                var4.printStackTrace();
            }

        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(Gson.class, "fromJson");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = SaveAndContinue.class,
            method = "save",
            paramtypez = {SaveFile.class}
    )
    public static class SaveDataToFile {
        public SaveDataToFile() {
        }

        @SpireInsertPatch(
                locator = Locator.class,
                localvars = {"params"}
        )
        public static void addCustomSaveData(SaveFile save, HashMap<Object, Object> params) {
            params.put("group_active", SaveData.group_active);
        }

        private static class Locator extends SpireInsertLocator {
            private Locator() {
            }

            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(GsonBuilder.class, "create");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = SaveFile.class,
            method = "<ctor>",
            paramtypez = {SaveFile.SaveType.class}
    )
    public static class SaveTheSaveData {
        public SaveTheSaveData() {
        }

        @SpirePostfixPatch
        public static void saveAllTheSaveData(SaveFile __instance, SaveFile.SaveType type) {
            SaveData.group_active=Shadowverse.groupActive;
        }
    }
}
