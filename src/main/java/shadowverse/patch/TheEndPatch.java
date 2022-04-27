package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.map.MapEdge;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.room.TheHeartRoom;

import java.util.ArrayList;

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

    @SpirePatch(clz = TheEnding.class, method = "generateSpecialMap")
    public static class TheEnding_GenerateSpecialMap {
        @SpirePostfixPatch
        public static void Postfix(TheEnding __instance) {
            MapRoomNode elite = ((ArrayList<MapRoomNode>) __instance.getMap().get(2)).get(3);
            MapRoomNode boss = ((ArrayList<MapRoomNode>) __instance.getMap().get(3)).get(3);
            MapRoomNode node = ((ArrayList<MapRoomNode>) __instance.getMap().get(2)).get(5);
            node.room = (AbstractRoom) new TheHeartRoom();
            node.room.setMapImg(ImageMaster.loadImage("img/ui/heart.png"), ImageMaster.loadImage("img/ui/heartOutline.png"));
            connectNode(elite, node);
            connectNode(node, boss);
        }

        private static void connectNode(MapRoomNode src, MapRoomNode dst) {
            src.addEdge(new MapEdge(src.x, src.y, src.offsetX, src.offsetY, dst.x, dst.y, dst.offsetX, dst.offsetY, false));
        }
    }
}
