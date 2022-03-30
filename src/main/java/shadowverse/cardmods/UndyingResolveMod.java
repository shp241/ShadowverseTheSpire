package shadowverse.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.action.NecromanceAction;
import shadowverse.characters.AbstractShadowversePlayer;

public class UndyingResolveMod extends AbstractCardModifier {
    public static String ID = "shadowverse:UndyingResolveMod";

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString("shadowverse:UndyingResolveMod").TEXT[0];
    }

    public void onInitialApplication(AbstractCard card) {
        if (!card.hasTag(AbstractShadowversePlayer.Enums.LASTWORD)) {
            card.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        }
    }

    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, ID);
    }

    public void onExhausted(AbstractCard card) {
        super.onExhausted(card);
        card.baseDamage *= 2;
        card.applyPowers();
        AbstractDungeon.actionManager.addToBottom(new NecromanceAction(6,null,new MakeTempCardInHandAction(card.makeSameInstanceOf())));
    }


    @Override
    public AbstractCardModifier makeCopy() {
        return new UndyingResolveMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
