 package shadowverse.powers;


 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;


 public class DeathPartyPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:DeathPartyPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:DeathPartyPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   public int turnCount = 0;
   public int deathCount = 8;
   private AbstractPlayer p = AbstractDungeon.player;

   public DeathPartyPower(AbstractCreature owner,int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
       loadRegion("minion");
   }

   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0]+this.amount+DESCRIPTIONS[1]+this.amount*6+DESCRIPTIONS[2]+this.amount*3+DESCRIPTIONS[3]+this.deathCount+DESCRIPTIONS[4];
   }


   @Override
   public void atEndOfTurn(boolean isPlayer) {
       turnCount++;
       deathCount--;
       flash();
       updateDescription();
       if (deathCount<=0){
           addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(this.owner.hb.cX, this.owner.hb.cY)));
           addToBot((AbstractGameAction)new LoseHPAction(this.owner, this.owner, 99999));
       }
       switch (turnCount){
           case 1:
               addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this.owner, (AbstractCreature)this.owner, (AbstractPower)new DrawCardNextTurnPower((AbstractCreature)this.owner, this.amount), this.amount));
               break;
           case 2:
               addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount*6, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
               break;
           case 3:
               addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new Cemetery((AbstractCreature)p, this.amount*3), this.amount*3));
               turnCount = 0;
               break;
           default:break;
       }
   }
 }
