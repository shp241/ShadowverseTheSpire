 package shadowverse.powers;

 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;


 public class WindFallFay
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:WindFallFay";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:WindFallFay");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public WindFallFay(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/WindFallFay.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 
   @Override
   public void onPlayCard(AbstractCard card, AbstractMonster m) {
       int count = 0;
       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
           count++;
       }
       if (count >= 4){
           addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, this.amount));
       }
   }

   @Override
   public void atStartOfTurn() {
       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player,AbstractDungeon.player,this));
       updateDescription();
   }
 }

