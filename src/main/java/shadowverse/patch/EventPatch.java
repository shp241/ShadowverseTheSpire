package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.EventHelper;
import shadowverse.characters.Vampire;
import shadowverse.events.ShadowverseVampiresEvent;

public class EventPatch {
    @SpirePatch(clz = EventHelper.class, method = "getEvent")
    public static class GetEvent {
        public static AbstractEvent Postfix(AbstractEvent __result, String key) {
            if (key.equals(ShadowverseVampiresEvent.ID))
                return (AbstractEvent)new ShadowverseVampiresEvent();
            return __result;
        }
    }

    @SpirePatch(clz = TheCity.class, method = "initializeEventList")
    public static class CityEventPatch {
        @SpirePostfixPatch
        public static void Postfix(TheCity __instance) {
            if (AbstractDungeon.player instanceof Vampire) {
                AbstractDungeon.eventList.remove("Vampires");
                AbstractDungeon.eventList.add(ShadowverseVampiresEvent.ID);
            }
        }
    }
}
