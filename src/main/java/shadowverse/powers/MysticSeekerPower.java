/*    */ package shadowverse.powers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import shadowverse.action.SpellBoostAction;
/*    */ 
/*    */ public class MysticSeekerPower
/*    */   extends AbstractPower {
/*    */   public static final String POWER_ID = "shadowverse:MysticSeeker";
/* 18 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:MysticSeeker");
/* 19 */   public static final String NAME = powerStrings.NAME;
/* 20 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/* 21 */   public int counter = 0;
/*    */   private AbstractCard card;
/*    */   
/*    */   public MysticSeekerPower(AbstractCreature owner, AbstractCard card) {
/* 25 */     this.name = NAME;
/* 26 */     this.ID = "shadowverse:MysticSeeker";
/* 27 */     this.owner = owner;
/* 28 */     this.amount = -1;
/* 29 */     this.type = PowerType.BUFF;
/* 30 */     this.card = card;
/* 31 */     updateDescription();
/* 32 */     this.img = new Texture("img/powers/MysticSeekerPower.png");
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlayCard(AbstractCard card, AbstractMonster m) {
/* 37 */     this.counter++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void atStartOfTurnPostDraw() {
/* 42 */     flash();
/* 43 */     if (this.counter <= 3) {
/* 44 */       addToBot((AbstractGameAction)new SpellBoostAction(AbstractDungeon.player, this.card, AbstractDungeon.player.hand.group));
/*    */     } else {
/* 46 */       addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)AbstractDungeon.player, 3));
/*    */     } 
/* 48 */     this.counter = 0;
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 52 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\powers\MysticSeekerPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */