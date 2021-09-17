/*    */ package shadowverse.cards.Temp;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.random.Random;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Satan_Accelerate
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:Satan_Accelerate";
/* 23 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Satan");
/* 24 */   public static final String NAME = cardStrings.NAME; public static final String IMG_PATH = "img/cards/Satan.png";
/* 25 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   
/*    */   public static ArrayList<AbstractCard> returnCocytusDeck() {
/* 28 */     ArrayList<AbstractCard> list = new ArrayList<>();
/* 29 */     list.add(new Flamelord());
/* 30 */     list.add(new Desire());
/* 31 */     list.add(new Scorpion());
/* 32 */     list.add(new HellBeast());
/* 33 */     list.add(new WrathfulIcefiend());
/* 34 */     list.add(new ViciousCommander());
/* 35 */     list.add(new DemonOfPurgatory());
/* 36 */     list.add(new Behemoth());
/* 37 */     list.add(new InfernalGaze());
/* 38 */     list.add(new InfernalSurge());
/* 39 */     list.add(new HeavenFall());
/* 40 */     list.add(new EarthFall());
/* 41 */     list.add(new Astaroth());
/* 42 */     return list;
/*    */   }
/*    */   public static AbstractCard returnCocytusCard(Random rng) {
/* 45 */     return returnCocytusDeck().get(rng.random(returnCocytusDeck().size() - 1));
/*    */   }
/*    */   
/*    */   public Satan_Accelerate() {
/* 49 */     super("shadowverse:Satan", NAME, "img/cards/Satan.png", 1, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.NONE);
             this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 54 */     if (!this.upgraded) {
/* 55 */       upgradeName();
/* 56 */       upgradeBaseCost(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 63 */     addToBot((AbstractGameAction)new SFXAction("Satan_Accelerate"));
/* 64 */     for (int i = 0; i < 4; i++) {
/* 65 */       AbstractCard c = returnCocytusCard(AbstractDungeon.cardRandomRng).makeCopy();
/* 66 */       addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(c, 1, true, true));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 72 */     return (AbstractCard)new Satan_Accelerate();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Temp\Satan_Accelerate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */