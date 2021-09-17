package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;

public class GemLightAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");

    public static final String[] TEXT = uiStrings.TEXT;

    private static final float DURATION_PER_CARD = 0.25F;

    private AbstractPlayer p;

    private boolean isCrystalBright;

    public GemLightAction(AbstractPlayer p,int amount,boolean isCrystalBright) {
        setValues((AbstractCreature) AbstractDungeon.player, source, amount);
        this.actionType = ActionType.EXHAUST;
        this.duration = 0.25F;
        this.p = AbstractDungeon.player;
        this.amount = amount;
        this.isCrystalBright = isCrystalBright;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.group.size() == 0) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.group.size() > 1) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false, false, false, false);
                tickDuration();
                return;
            }
            if (this.p.hand.group.size() == 1) {
                addToBot((AbstractGameAction)new ExhaustSpecificCardAction(this.p.hand.getTopCard(),this.p.hand,true));
                this.p.hand.moveToExhaustPile(this.p.hand.getTopCard());
                if (!isCrystalBright)
                    for (int i = 0; i < this.amount; i++)
                        addToTop((AbstractGameAction)new MakeTempCardInHandAction(generateCard()));
                else
                    for (int i = 0; i < this.amount; i++)
                        addToTop((AbstractGameAction)new MakeTempCardInHandAction(generateCard2()));
                this.isDone = true;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,this.p.hand,true));
                this.p.hand.moveToExhaustPile(c);
                if (!isCrystalBright)
                for (int i = 0; i < this.amount; i++)
                    addToTop((AbstractGameAction)new MakeTempCardInHandAction(generateCard()));
                else
                    for (int i = 0; i < this.amount; i++)
                        addToTop((AbstractGameAction)new MakeTempCardInHandAction(generateCard2()));
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            this.isDone = true;
        }
        tickDuration();
    }

    private AbstractCard generateCard() {
            AbstractCard.CardRarity cardRarity;
            AbstractCard c = null;
            int roll = AbstractDungeon.cardRandomRng.random(99);
            if (roll < 55) {
                cardRarity = AbstractCard.CardRarity.COMMON;
            } else if (roll < 85) {
                cardRarity = AbstractCard.CardRarity.UNCOMMON;
            } else {
                cardRarity = AbstractCard.CardRarity.RARE;
            }
        c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        while(c.rarity != cardRarity){
            c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        }
        if (c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE)&&!(c.cardID=="shadowverse:Satan")&&!(c.cardID=="shadowverse:Technolord")){
            if (Shadowverse.Accelerate((AbstractCard)c)) {
                c.setCostForTurn(0);
                c.type = AbstractCard.CardType.SKILL;
            } else {
                c.type = AbstractCard.CardType.ATTACK;
            }
            c.applyPowers();
        }else if (c.cardID=="shadowverse:Technolord"){
            if (Shadowverse.Accelerate((AbstractCard)c)) {
                c.setCostForTurn(1);
                c.type = AbstractCard.CardType.SKILL;
            } else {
                c.type = AbstractCard.CardType.ATTACK;
            }
            c.applyPowers();
        }
        else if (c.hasTag(AbstractShadowversePlayer.Enums.ENHANCE)){
            if (Shadowverse.Enhance(2)) {
                c.setCostForTurn(2);
                c.applyPowers();
            }
        }else if (c.hasTag(AbstractShadowversePlayer.Enums.CRYSTALLIZE)){
            if (Shadowverse.Accelerate((AbstractCard)c)) {
                c.setCostForTurn(0);
                c.type = AbstractCard.CardType.POWER;
            } else {
                c.type = AbstractCard.CardType.ATTACK;
            }
            c.applyPowers();
        }
            return c;
    }

    private AbstractCard generateCard2() {
        AbstractCard.CardRarity cardRarity;
        AbstractCard c = null;
        int roll = AbstractDungeon.cardRandomRng.random(99);
        if (roll < 50) {
            cardRarity = AbstractCard.CardRarity.UNCOMMON;
        } else {
            cardRarity = AbstractCard.CardRarity.RARE;
        }
        c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        while(c.rarity != cardRarity){
            c = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
        }
        if (c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE)&&!(c.cardID=="shadowverse:Satan")&&!(c.cardID=="shadowverse:Technolord")){
            if (Shadowverse.Accelerate((AbstractCard)c)) {
                c.setCostForTurn(0);
                c.type = AbstractCard.CardType.SKILL;
            } else {
                c.type = AbstractCard.CardType.ATTACK;
            }
            c.applyPowers();
        }else if (c.cardID=="shadowverse:Technolord"){
            if (Shadowverse.Accelerate((AbstractCard)c)) {
                c.setCostForTurn(1);
                c.type = AbstractCard.CardType.SKILL;
            } else {
                c.type = AbstractCard.CardType.ATTACK;
            }
            c.applyPowers();
        }
        else if (c.hasTag(AbstractShadowversePlayer.Enums.ENHANCE)){
            if (Shadowverse.Enhance(2)) {
                c.setCostForTurn(2);
                c.applyPowers();
            }
        }else if (c.hasTag(AbstractShadowversePlayer.Enums.CRYSTALLIZE)){
            if (Shadowverse.Accelerate((AbstractCard)c)) {
                c.setCostForTurn(0);
                c.type = AbstractCard.CardType.POWER;
            } else {
                c.type = AbstractCard.CardType.ATTACK;
            }
            c.applyPowers();
        }
        return c;
    }

}
