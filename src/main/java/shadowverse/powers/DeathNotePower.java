 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.HealAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import shadowverse.action.BetterAutoPlayCardAction;
 import shadowverse.effect.RedLaserBeamEffect;

 import java.util.ArrayList;
 import java.util.Collections;


 public class DeathNotePower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:DeathNotePower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:DeathNotePower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public DeathNotePower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     loadRegion("minion");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     if (isPlayer) {
       flash();
       ArrayList<AbstractCard> list = new ArrayList<>();
       for (AbstractCard card: AbstractDungeon.player.drawPile.group){
         if (card.type == AbstractCard.CardType.ATTACK && card.cost<= AbstractDungeon.player.energy.energyMaster){
           list.add(card);
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
     } 
   }

   public void atStartOfTurnPostDraw() {
     this.amount -= 1;
     if (this.amount == 0){
       flash();
       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
     }
     updateDescription();
   }
 }


