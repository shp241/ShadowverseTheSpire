 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.characters.AbstractShadowversePlayer;












 
 public class FaustPower
   extends AbstractPower {
   public static final String POWER_ID = "shadowverse:FaustPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:FaustPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public FaustPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = "shadowverse:FaustPower";
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/FaustPower.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (this.amount / 2) + DESCRIPTIONS[2];
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     if (isPlayer) {
       AbstractPlayer p = (AbstractPlayer)this.owner;
       boolean powerExists = false;
       for (AbstractPower pow : p.powers) {
         if (pow.ID.equals("shadowverse:EarthEssence")) {
           powerExists = true;
           break;
         } 
       } 
       if (powerExists) {
         ((AbstractShadowversePlayer)p).earthCount++;
         flash();
         addToBot((AbstractGameAction)new SFXAction("FaustPower"));
         addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, this.amount), this.amount));
         addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new DexterityPower(this.owner, this.amount / 2), this.amount / 2));
         addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new EarthEssence(this.owner, -1), -1));
       } 
     } 
   }
 }

