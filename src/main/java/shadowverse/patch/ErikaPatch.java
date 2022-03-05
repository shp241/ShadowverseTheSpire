package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.orbs.AmbushMinion;
import shadowverse.orbs.ErikaOrb;

public class ErikaPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "channelOrb")
    public static class ErikaBuffPatch {
        @SpirePostfixPatch
        public static void buff(AbstractPlayer p, AbstractOrb orb) {
            if ((!(orb instanceof AmbushMinion) ||
                    ((AmbushMinion) orb).card.type != AbstractCard.CardType.ATTACK) && !(orb instanceof ErikaOrb)){
                for (AbstractOrb o : p.orbs){
                    if (o instanceof ErikaOrb){
                        ((ErikaOrb) o).onChannelOtherOrb(orb);
                    }
                }
            }
        }
    }
}
