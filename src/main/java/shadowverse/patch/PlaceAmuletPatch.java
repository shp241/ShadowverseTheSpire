package shadowverse.patch;

import charbosses.actions.RealWaitAction;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.Temp.NaterranGreatTree;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;
import shadowverse.powers.NaterranTree;

public class PlaceAmuletPatch {
    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class placeCardPatch {
        @SpirePostfixPatch
        public static void placeA(AbstractPlayer p, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            if (c instanceof AbstractAmuletCard){
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RealWaitAction(0.6F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new PlaceAmulet(c,p.hand));
            }
            if (p instanceof Bishop && c instanceof AbstractNoCountDownAmulet){
                if (c instanceof NaterranGreatTree){
                    if (!p.hasPower(NaterranTree.POWER_ID)){
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RealWaitAction(0.6F));
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new PlaceAmulet(c,p.hand));
                    }
                }else {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RealWaitAction(0.6F));
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new PlaceAmulet(c,p.hand));
                }
            }
        }

        @SpirePostfixPatch
        public static void trigger(AbstractPlayer p, AbstractCard c, AbstractMonster monster, int energyOnUse){
            for (AbstractOrb o : p.orbs){
                if (o instanceof AmuletOrb){
                    if (((AmuletOrb) o).amulet instanceof AbstractAmuletCard){
                        ((AbstractAmuletCard)(((AmuletOrb) o).amulet)).onOtherCardPlayed(c,(AmuletOrb) o);
                    }
                    if (((AmuletOrb) o).amulet instanceof AbstractCrystalizeCard){
                        ((AbstractCrystalizeCard)(((AmuletOrb) o).amulet)).onOtherCardPlayed(c,(AmuletOrb) o);
                    }
                    if (((AmuletOrb) o).amulet instanceof AbstractNoCountDownAmulet){
                        ((AbstractNoCountDownAmulet)(((AmuletOrb) o).amulet)).onOtherCardPlayed(c,(AmuletOrb) o);
                    }
                }
            }
        }
    }
}
