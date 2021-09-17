 package shadowverse.powers;
 


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


 public class PrimeArtifactPower
   extends AbstractPower {
   public static final String POWER_ID = "shadowverse:PrimeArtifactPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:PrimeArtifactPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   private AbstractCard c;
   private boolean upgraded;

   public PrimeArtifactPower(AbstractCreature owner, int amount, AbstractCard c, boolean upgraded) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     this.upgraded = upgraded;
     this.c = c.makeSameInstanceOf();
     updateDescription();
     loadRegion("carddraw");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999) {
       this.amount = 999;
     }
   }
   
   public void atStartOfTurn() {
     flash();
     if (this.upgraded)
       c.upgrade();
     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, this));
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, this.amount));
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 }

