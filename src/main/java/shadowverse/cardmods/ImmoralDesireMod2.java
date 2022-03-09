package shadowverse.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.characters.AbstractShadowversePlayer;

public class ImmoralDesireMod2 extends AbstractCardModifier {
    public static String ID = "shadowverse:ImmoralDesireMod2";

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return  rawDescription + CardCrawlGame.languagePack.getUIString("shadowverse:ImmoralDesireMod2").TEXT[0];
    }

    public void onInitialApplication(AbstractCard card) {
        if (card.hasTag(AbstractShadowversePlayer.Enums.LASTWORD)){
            card.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        }
    }

    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, ID);
    }

    public void onExhausted(AbstractCard card) {
        AbstractDungeon.actionManager.addToBottom(((AbstractGameAction)new AddTemporaryHPAction(AbstractDungeon.player,AbstractDungeon.player,card.baseBlock)));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ImmoralDesireMod2();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
