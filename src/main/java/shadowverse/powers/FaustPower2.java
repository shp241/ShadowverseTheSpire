 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;


 public class FaustPower2
   extends AbstractPower {
   public static final String POWER_ID = "shadowverse:FaustPower2";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:FaustPower2");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public FaustPower2(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
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
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     if (isPlayer) {
       AbstractPlayer p = (AbstractPlayer)this.owner;
       AbstractPower power = null;
       for (AbstractPower pow : p.powers) {
         if (pow.ID.equals("shadowverse:EarthEssence")) {
           power = pow;
           break;
         } 
       } 
       if (power!=null&&power.amount>=2) {
         if (p instanceof  AbstractShadowversePlayer){
           ((AbstractShadowversePlayer)p).earthCount+=2;
         }
         flash();
         addToBot((AbstractGameAction)new SFXAction("FaustPower2"));
         addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
         addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new EarthEssence(this.owner, -2), -2));
       } else {
         addToBot((AbstractGameAction)new SFXAction("FaustPower3"));
         addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new EarthEssence(this.owner, 2), 2));
       }
     } 
   }
 }

