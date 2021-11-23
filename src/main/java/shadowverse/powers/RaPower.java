 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
 import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import shadowverse.characters.AbstractShadowversePlayer;


 public class RaPower
   extends TwoAmountPower
 {
   public static final String POWER_ID = "shadowverse:RaPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:RaPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public RaPower(AbstractCreature owner,int amount, int amount2) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.amount2 = amount2;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/RaPower.png");
   }

   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999) {
       this.amount = 999;
     }
   }

   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount * this.amount2 + DESCRIPTIONS[1];
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     if (isPlayer) {
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
         if (!mo.isDeadOrEscaped())
           addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(mo.drawX, mo.drawY), 0.05F));
       }
       addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(this.amount*this.amount2, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
       this.amount++;
       updateDescription();
     } 
   }
 }

