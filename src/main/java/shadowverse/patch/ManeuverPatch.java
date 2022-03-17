package shadowverse.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import shadowverse.action.NahtAction;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.cards.AbstractVehicleCard;
import shadowverse.characters.Bishop;
import shadowverse.powers.NahtPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.ALL_ENEMY;
import static com.megacrit.cardcrawl.cards.AbstractCard.CardTarget.ENEMY;

public class ManeuverPatch {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:AmuletText")).TEXT;

    @SpirePatch(clz = AbstractPlayer.class, method = "useCard")
    public static class useCardPatch {
        @SpirePrefixPatch
        public static SpireReturn useM(AbstractPlayer p, AbstractCard c, AbstractMonster monster, int energyOnUse) {
            for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (m.isDying || m.isDead) {
                    continue;
                }
                if (m.hasPower("shadowverse:NahtPower")) {
                    if (c.target.equals(ALL_ENEMY) || c.target.equals(ENEMY) && monster.equals(m)) {
                        for (AbstractPower power : m.powers) {
                            if (power instanceof NahtPower && !((NahtPower) power).triggered) {
                                if (c.costForTurn > 0 && !c.freeToPlay() && !c.isInAutoplay && (
                                        !p.hasPower("Corruption") || c.type != AbstractCard.CardType.SKILL)){
                                    p.energy.use(c.costForTurn);
                                }
                                ((NahtPower) power).triggered = true;
                                ((NahtPower) power).boxed.add(c);
                                power.updateDescription();
                                AbstractDungeon.actionManager.addToBottom(new RollMoveAction(m));
                                AbstractDungeon.actionManager.addToBottom(new NahtAction(c));
                                return SpireReturn.Return(null);
                            }
                        }
                    }
                }
            }
            for (AbstractCard card : p.hand.group) {
                if (card instanceof AbstractVehicleCard) {
                    if (!((AbstractVehicleCard) card).maneuver && ((AbstractVehicleCard) card).predicate.test(c)) {
                        card.triggerOnOtherCardPlayed(c);
                        if (c.costForTurn > 0 && !c.freeToPlay() && !c.isInAutoplay && (
                                !p.hasPower("Corruption") || c.type != AbstractCard.CardType.SKILL)) {
                            p.energy.use(c.costForTurn);
                        }
                        return SpireReturn.Return(null);
                    }
                }
            }
            if (p instanceof Bishop) {
                if (!AbstractDungeon.player.hasEmptyOrb()) {
                    if (c instanceof AbstractAmuletCard || c instanceof AbstractNoCountDownAmulet || (c instanceof AbstractCrystalizeCard && c.type == AbstractCard.CardType.POWER)) {
                        AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, TEXT[0], true));
                        return SpireReturn.Return(null);
                    }
                }
            }
            return SpireReturn.Continue();
        }
    }
}
