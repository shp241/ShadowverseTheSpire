package shadowverse.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import shadowverse.characters.AbstractShadowversePlayer;

public class NobilisMod extends AbstractCardModifier {
    public static String ID = "shadowverse:NobilisMod";

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return  rawDescription + CardCrawlGame.languagePack.getUIString("shadowverse:NobilisMod").TEXT[0];
    }

    public void onInitialApplication(AbstractCard card) {
        if (!card.hasTag(AbstractShadowversePlayer.Enums.LASTWORD)){
            card.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        }
    }

    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, ID);
    }

    public void onExhausted(AbstractCard card) {
        for (AbstractMonster mo : AbstractDungeon.getMonsters().monsters){
            if (mo != null && !mo.isDeadOrEscaped()){
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo,AbstractDungeon.player, new PoisonPower(mo,AbstractDungeon.player,4),4));
            }
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new NobilisMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
