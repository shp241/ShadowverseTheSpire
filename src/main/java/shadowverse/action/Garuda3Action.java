package shadowverse.action;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.orbs.AmuletOrb;

import java.util.ArrayList;
import java.util.Collections;

public class Garuda3Action extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;

    public Garuda3Action() {
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            ArrayList<AbstractCard> list = new ArrayList<>();
            ArrayList<AbstractCard> list2 = new ArrayList<>();
            int amuletAmt = 0;
            if (p instanceof AbstractShadowversePlayer)
                amuletAmt = ((AbstractShadowversePlayer) p).amuletCount;
            for (AbstractCard card: AbstractDungeon.player.drawPile.group){
                if (card.type == AbstractCard.CardType.ATTACK && card.cost<= amuletAmt){
                    list.add(card);
                }
                if (card instanceof AbstractAmuletCard){
                    list2.add(card);
                }
            }
            if (list.size()>0){
                ArrayList<Integer> costTmp = new ArrayList<>();
                ArrayList<AbstractCard> finalList = new ArrayList<>();
                for (AbstractCard c : list) {
                    costTmp.add(c.cost);
                }
                int max = Collections.max(costTmp);
                for (AbstractCard finalCard : list){
                    if (finalCard.cost == max){
                        finalList.add(finalCard);
                    }
                }
                Collections.shuffle(finalList);
                AbstractCard tempCard = finalList.get(0);
                tempCard.setCostForTurn(0);
                addToBot((AbstractGameAction)new BetterAutoPlayCardAction(tempCard,AbstractDungeon.player.drawPile));
            }
            if (list2.size()>0){
                ArrayList<Integer> costTmp = new ArrayList<>();
                ArrayList<AbstractCard> finalList = new ArrayList<>();
                for (AbstractCard c : list2) {
                    costTmp.add(c.cost);
                }
                int max = Collections.max(costTmp);
                for (AbstractCard finalCard : list2){
                    if (finalCard.cost == max){
                        finalList.add(finalCard);
                    }
                }
                Collections.shuffle(finalList);
                AbstractCard tempCard = finalList.get(0);
                tempCard.setCostForTurn(0);
                addToBot((AbstractGameAction)new BetterAutoPlayCardAction(tempCard,AbstractDungeon.player.drawPile));
            }
        }
        tickDuration();
    }
}
