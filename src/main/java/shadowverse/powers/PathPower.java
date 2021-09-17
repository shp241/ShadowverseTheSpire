 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;


 public class PathPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:PathPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:PathPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   private AbstractPlayer p = AbstractDungeon.player;

   public PathPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = "shadowverse:PathPower";
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/PathPower.png");
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
       int playerNecromance = 0;
       if (this.owner.hasPower(Cemetery.POWER_ID)){
         for (AbstractPower p :this.owner.powers){
           if (p.ID.equals(Cemetery.POWER_ID))
             playerNecromance = p.amount;
         }
       }
       if (playerNecromance>=30){
         addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
         addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new CleaveEffect(), 0.1F));
         addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
       }
     } 
   }
 }

