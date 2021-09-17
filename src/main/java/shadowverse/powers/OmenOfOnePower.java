 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.DexterityPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 
 
 public class OmenOfOnePower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:OmenOfOnePower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:OmenOfOnePower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public OmenOfOnePower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = "shadowverse:OmenOfOnePower";
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/OmenOfOnePower.png");
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     if (isPlayer) {
       AbstractPlayer p = (AbstractPlayer)this.owner;
       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new StrengthPower(this.owner, 2), 2));
       addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, (AbstractPower)new DexterityPower(this.owner, 2), 2));
       addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(this.owner, 12, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
     } 
   }
 }


