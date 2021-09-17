 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.core.Settings;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;


 public class TheLoversPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:TheLoversPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:TheLoversPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public TheLoversPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/TheLoversPower.png");
   }

   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     if (isPlayer) {
       if (Settings.FAST_MODE) {
         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new GrandFinalEffect(), 0.7F));
       } else {
         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new GrandFinalEffect(), 1.0F));
       }
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
         int astarothDamage = mo.currentBlock + mo.currentHealth - 6;
         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)mo, new DamageInfo((AbstractCreature)this.owner, astarothDamage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
       }
       addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner,this.owner,this));
     } 
   }
 }

