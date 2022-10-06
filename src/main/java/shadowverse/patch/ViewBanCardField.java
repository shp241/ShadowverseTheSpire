package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.ui.panels.TopPanel;
import shadowverse.helper.ViewBanCardOption;

@SpirePatch(
        clz = TopPanel.class,
        method = "<class>"
)
public class ViewBanCardField {
    public static SpireField<ViewBanCardOption> banCard = new SpireField(() -> {
        return null;
    });

    public ViewBanCardField() {
    }
}