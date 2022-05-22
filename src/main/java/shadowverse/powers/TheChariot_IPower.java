 package shadowverse.powers;

 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.GainStrengthPower;
 import com.megacrit.cardcrawl.powers.LoseStrengthPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

 public class TheChariot_IPower extends AbstractPower {
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:TheChariot_IPower");
   public static final String POWER_ID = "shadowverse:TheChariot_IPower";
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public TheChariot_IPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/TheChariot_IPower.png");
   }
 
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.owner.maxHealth/2 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
   }

     @Override
     public void atEndOfTurn(boolean isPlayer) {
         if (isPlayer){
             flash();
             addToBot((AbstractGameAction)new SFXAction("TheChariot_IPower"));
             for (int i=0;i<2;i++){
                 int rand = AbstractDungeon.cardRandomRng.random(1);
                 if (rand == 1){
                     addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new WeightyImpactEffect(this.owner.hb.cX, this.owner.hb.cY)));
                     addToBot(new DamageAction(this.owner, new DamageInfo(this.owner, this.owner.maxHealth/2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                 }else {
                     AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
                     if (m!=null) {
                         addToBot((AbstractGameAction) new VFXAction((AbstractGameEffect) new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
                         addToBot(new DamageAction(m, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                     }
                 }
             }
             addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner,this.owner,this));
         }
     }
 }


