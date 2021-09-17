 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
 
 public class ShopPower extends AbstractPower {
   public static final String POWER_ID = "shadowverse:ShopPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ShopPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public ShopPower(AbstractCreature owner, int amount) {
     this.name = NAME;
     this.ID = "shadowverse:ShopPower";
     this.owner = owner;
     this.amount = amount;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/ShopPower.png");
   }
   
   public void stackPower(int stackAmount) {
     this.amount += stackAmount;
     if (this.amount >= 999)
       this.amount = 999; 
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (this.amount / 3) + DESCRIPTIONS[2];
   }
 
   
   public void onUseCard(AbstractCard card, UseCardAction action) {
     boolean deckCheck = true;
     for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
       if (c.type == AbstractCard.CardType.ATTACK || c.type== AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE || c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE) ||c.hasTag(AbstractShadowversePlayer.Enums.CRYSTALLIZE)) {
         deckCheck = false;
         break;
       } 
     } 
     for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
       if (c.type == AbstractCard.CardType.ATTACK || c.type== AbstractCard.CardType.STATUS || c.type == AbstractCard.CardType.CURSE || c.hasTag(AbstractShadowversePlayer.Enums.ACCELERATE)||c.hasTag(AbstractShadowversePlayer.Enums.CRYSTALLIZE)) {
         deckCheck = false;
         break;
       } 
     } 
     if (deckCheck) {
       addToBot((AbstractGameAction)new GainEnergyAction(this.amount / 3));
     }
     flash();
     addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
   }
 }


