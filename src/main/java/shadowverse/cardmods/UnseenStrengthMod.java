package shadowverse.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.characters.AbstractShadowversePlayer;

public class UnseenStrengthMod extends AbstractCardModifier {
    public static String ID = "shadowverse:UnseenStrengthMod";

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return  rawDescription + CardCrawlGame.languagePack.getUIString("shadowverse:UnseenStrengthMod").TEXT[0];
    }

    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, ID);
    }


    @Override
    public AbstractCardModifier makeCopy() {
        return new UnseenStrengthMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
