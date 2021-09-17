 package shadowverse.powers;
 


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;


 public class HeavensDoorInvocationPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:HeavensDoorInvocationPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:HeavensDoorInvocationPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   private AbstractCard card;

   public HeavensDoorInvocationPower(AbstractCreature owner, int amount, AbstractCard card) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     this.card = card;
     updateDescription();
     this.img = new Texture("img/powers/HeavensDoorPower.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }

   
   public void atEndOfTurn(boolean isPlayer) {
     if (EnergyPanel.getCurrentEnergy() >=2 ){
       if (!AbstractDungeon.player.hasPower("shadowverse:HeavensDoorPower")){
         if (AbstractDungeon.player.drawPile.contains(card)){
           addToBot((AbstractGameAction)new ExhaustSpecificCardAction(card, AbstractDungeon.player.drawPile));
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new HeavensDoorPower((AbstractCreature)AbstractDungeon.player, 1), 1));
         }else if (AbstractDungeon.player.discardPile.contains(card)){
           addToBot((AbstractGameAction)new ExhaustSpecificCardAction(card, AbstractDungeon.player.discardPile));
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new HeavensDoorPower((AbstractCreature)AbstractDungeon.player, 1), 1));
         }
       }
     }
     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "shadowverse:HeavensDoorInvocationPower"));
   }
 }


