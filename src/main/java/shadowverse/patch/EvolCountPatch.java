package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.cards.Common.DancingMiniSoulDevil;
import shadowverse.cards.Rare.Signa;
import shadowverse.characters.AbstractShadowversePlayer;

public class EvolCountPatch {
    @SpirePatch(clz = AbstractCard.class, method = "upgradeName")
    public static class Count {
        @SpirePostfixPatch
        public static void CountEvol(AbstractCard card) {
            if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
                    if (card.type == AbstractCard.CardType.ATTACK){
                        ((AbstractShadowversePlayer) AbstractDungeon.player).upgradedThisCombat++;
                        if (AbstractDungeon.player.hand.group.contains(card)){
                            for (AbstractCard c : AbstractDungeon.player.hand.group){
                                if (c instanceof DancingMiniSoulDevil){
                                    c.upgrade();
                                    AbstractDungeon.actionManager.addToBottom(new SFXAction("DancingMiniSoulDevil_Eff"));
                                }
                                if (c instanceof Signa){
                                    c.upgrade();
                                    AbstractDungeon.actionManager.addToBottom(new SFXAction("Signa_Eff"));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
