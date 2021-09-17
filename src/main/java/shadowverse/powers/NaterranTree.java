/*    */ package shadowverse.powers;
/*    */ 
/*    */ import com.badlogic.gdx.graphics.Texture;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.PowerStrings;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ 
/*    */ 
/*    */ public class NaterranTree
/*    */   extends AbstractPower
/*    */ {
/*    */   public static final String POWER_ID = "shadowverse:NaterranTree";
/* 14 */   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:NaterranTree");
/* 15 */   public static final String NAME = powerStrings.NAME;
/* 16 */   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
/*    */   
/*    */   public NaterranTree(AbstractCreature owner) {
/* 19 */     this.name = NAME;
/* 20 */     this.ID = "shadowverse:NaterranTree";
/* 21 */     this.owner = owner;
/* 22 */     this.amount = -1;
/* 23 */     this.type = PowerType.BUFF;
/* 24 */     updateDescription();
/* 25 */     this.img = new Texture("img/powers/NaterranTree.png");
/*    */   }
/*    */   
/*    */   public void updateDescription() {
/* 29 */     this.description = DESCRIPTIONS[0];
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\powers\NaterranTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */