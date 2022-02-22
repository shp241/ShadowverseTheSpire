package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import shadowverse.powers.CarnivalNecromancerPower;
import shadowverse.powers.Cemetery;
import shadowverse.powers.EverdarkStrixPower;

import java.util.ArrayList;

public class BurialAction extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:Burial")).TEXT;

    private AbstractPlayer p = AbstractDungeon.player;
    private AbstractGameAction action;
    private ArrayList<AbstractCard> cannotChose = new ArrayList<>();

    public BurialAction(int amount,AbstractGameAction action){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.amount = amount;
        this.action = action;
        this.duration = 0.25F;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.group.size() == 0) {
                this.isDone = true;
                return;
            }
            for (AbstractCard c : this.p.hand.group) {
                if (c.type!= AbstractCard.CardType.ATTACK) {
                    this.cannotChose.add(c);
                }
            }
            if (this.cannotChose.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.group.size()-this.cannotChose.size()<this.amount){
                this.isDone = true;
                return;
            }
            this.p.hand.group.removeAll(this.cannotChose);
            if (this.p.hand.group.size() > this.amount) {
                AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, false, false, false);
                tickDuration();
                return;
            }
            if (this.p.hand.group.size() == this.amount) {
                ArrayList<AbstractCard> cardToRemove = new ArrayList<>();
                for (AbstractCard c:this.p.hand.group){
                    AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
                    AbstractDungeon.actionManager.cardsPlayedThisCombat.add(c);
                    cardToRemove.add(c);
                }
                for (AbstractCard c:cardToRemove){
                    this.p.hand.removeCard(c);
                }
                returnCards();
                if (null!=action){
                    addToBot(action);
                }
                addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Cemetery((AbstractCreature)p, 1), 1));
                if (this.p.hasPower(CarnivalNecromancerPower.POWER_ID)){
                    int drawAmt = 0;
                    for (AbstractPower power:this.p.powers){
                        if (power instanceof CarnivalNecromancerPower)
                            drawAmt = power.amount;
                    }
                    addToBot((AbstractGameAction)new SFXAction("CarnivalNecromancerPower"));
                    addToBot((AbstractGameAction)new DrawCardAction(drawAmt));
                }
                if (this.p.hasPower(EverdarkStrixPower.POWER_ID)){
                    int blockAmt = 0;
                    for (AbstractPower power:this.p.powers){
                        if (power instanceof EverdarkStrixPower)
                            blockAmt = power.amount;
                    }
                    addToBot((AbstractGameAction)new GainBlockAction(this.p,blockAmt));
                }
                this.isDone = true;
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                if (c.type== AbstractCard.CardType.ATTACK){
                    AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
                    this.p.hand.removeCard(c);
                    AbstractDungeon.actionManager.cardsPlayedThisCombat.add(c);
                }
            }
            returnCards();
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
            if (null!=action){
                addToBot(action);
            }
            addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Cemetery((AbstractCreature)p, 1), 1));
            if (this.p.hasPower(CarnivalNecromancerPower.POWER_ID)){
                int drawAmt = 0;
                for (AbstractPower power:this.p.powers){
                    if (power instanceof CarnivalNecromancerPower)
                        drawAmt = power.amount;
                }
                addToBot((AbstractGameAction)new SFXAction("CarnivalNecromancerPower"));
                addToBot((AbstractGameAction)new DrawCardAction(drawAmt));
            }
            if (this.p.hasPower(EverdarkStrixPower.POWER_ID)){
                int blockAmt = 0;
                for (AbstractPower power:this.p.powers){
                    if (power instanceof EverdarkStrixPower)
                        blockAmt = power.amount;
                }
                addToBot((AbstractGameAction)new GainBlockAction(this.p,blockAmt));
            }
            this.isDone = true;
        }
        tickDuration();
    }

    private void returnCards() {
        for (AbstractCard c : this.cannotChose)
            this.p.hand.addToTop(c);
        this.p.hand.refreshHandLayout();
    }
}
