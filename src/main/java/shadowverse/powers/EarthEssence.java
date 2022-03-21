 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.TalkAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 
 public class EarthEssence extends AbstractPower {
   public static final String POWER_ID = "shadowverse:EarthEssence";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:EarthEssence");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public EarthEssence(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = "shadowverse:EarthEssence";
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     if (this.amount >= 5)
       this.amount = 5; 
     updateDescription();
     this.img = new Texture("img/powers/EarthEssence.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount == 0)
       addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "shadowverse:EarthEssence")); 
     if (this.amount >= 5) {
       this.amount = 5;
     }
   }
   
   public boolean canPlayCard(AbstractCard card) {
     if (this.amount == 5 && (card.type == AbstractCard.CardType.ATTACK||card.type== AbstractCard.CardType.POWER)) {
       return false;
     }
     return true;
   }

     @Override
     public void onPlayCard(AbstractCard card, AbstractMonster m) {
         if (this.amount == 5 && (card.type == AbstractCard.CardType.ATTACK||card.type== AbstractCard.CardType.POWER)){
             addToBot((AbstractGameAction)new TalkAction(true, DESCRIPTIONS[2], 1.0F, 2.0F));
         }
     }

     public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 
   
   public void playApplyPowerSfx() {
     CardCrawlGame.sound.play("EarthEssence", 0.0F);
   }
 }


