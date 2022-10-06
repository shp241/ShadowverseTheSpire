package shadowverse.patch;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import shadowverse.helper.ViewBanCardOption;

public class ViewBanCardPatch {
    public ViewBanCardPatch() {
    }

    @SpirePatch(
            clz = TopPanel.class,
            method = "render"
    )
    public static class render {
        public render() {
        }

        @SpireInsertPatch(
                rloc = 62
        )
        public static void insert(TopPanel _inst, SpriteBatch sb) {
            ((ViewBanCardOption) ViewBanCardField.banCard.get(_inst)).render(sb);
        }
    }

    @SpirePatch(
            clz = TopPanel.class,
            method = "updateButtons"
    )
    public static class update {
        public update() {
        }

        @SpirePrefixPatch
        public static void prefix(TopPanel _inst) {
            ((ViewBanCardOption) ViewBanCardField.banCard.get(_inst)).update();
        }
    }

    @SpirePatch(
            clz = TopPanel.class,
            method = "unhoverHitboxes"
    )
    public static class unhoverHitboxes {
        public unhoverHitboxes() {
        }

        @SpirePrefixPatch
        public static void prefix(TopPanel _inst) {
            ((ViewBanCardOption) ViewBanCardField.banCard.get(_inst)).unhoverHitboxes();
        }
    }

    @SpirePatch(
            clz = TopPanel.class,
            method = "<ctor>"
    )
    public static class initialize {
        public initialize() {
        }

        @SpirePrefixPatch
        public static void prefix(TopPanel _inst) {
            ViewBanCardField.banCard.set(_inst, new ViewBanCardOption());
        }
    }
}
