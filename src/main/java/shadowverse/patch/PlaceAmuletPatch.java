package shadowverse.patch;

import charbosses.actions.RealWaitAction;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.AbstractAmuletCard;

public class PlaceAmuletPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class placeCardPatch {
        @SpirePostfixPatch
        public static void placeA(AbstractPlayer p, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            if (c instanceof AbstractAmuletCard){
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RealWaitAction(0.6F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new PlaceAmulet(c,p.hand));
            }
        }
    }
}
