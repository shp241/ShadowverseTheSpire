 package shadowverse.powers;
 


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;


 public class GlassPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:GlassPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:GlassPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public GlassPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/GlassPower.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
   }

   public void onAfterCardPlayed(AbstractCard card) {
     if (card.type == AbstractCard.CardType.ATTACK){
       flash();
       if (Settings.FAST_MODE) {
         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BlizzardEffect( 1,
                 AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
       } else {
         addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BlizzardEffect(1, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
       }
       addToBot((AbstractGameAction)new DamageAllEnemiesAction(this.owner, DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
     }
   }
   
   public void atEndOfTurn(boolean isPlayer) {
     addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "shadowverse:GlassPower"));
   }
 }


