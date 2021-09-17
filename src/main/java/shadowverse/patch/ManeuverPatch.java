package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.AbstractVehicleCard;

public class ManeuverPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class useCardPatch {
        @SpirePrefixPatch
        public static SpireReturn useM(AbstractPlayer p, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            for (AbstractCard card:p.hand.group){
                if (card instanceof AbstractVehicleCard){
                    if (!((AbstractVehicleCard) card).maneuver&&((AbstractVehicleCard) card).predicate.test(c)){
                        card.triggerOnOtherCardPlayed(c);
                        if (c.costForTurn > 0 && !c.freeToPlay() && !c.isInAutoplay && (
                                !p.hasPower("Corruption") || c.type != AbstractCard.CardType.SKILL))
                            p.energy.use(c.costForTurn);
                        return SpireReturn.Return(null);
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }
}
