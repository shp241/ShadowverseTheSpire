/*    */ package shadowverse.cards.Rare;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
/*    */ import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.status.VoidCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import shadowverse.action.OzSetCostAction;
/*    */ import shadowverse.characters.Witchcraft;
/*    */ import shadowverse.powers.OzPower;
/*    */ 
/*    */ 
/*    */ public class Oz
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:Oz";
/* 26 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Oz");
/* 27 */   public static final String NAME = cardStrings.NAME;
/* 28 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/Oz.png";
/*    */   
/*    */   public Oz() {
/* 32 */     super("shadowverse:Oz", NAME, "img/cards/Oz.png", 2, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.NONE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 37 */     if (!this.upgraded) {
/* 38 */       upgradeName();
/* 39 */       upgradeBaseCost(1);
/* 40 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 41 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 47 */     addToBot((AbstractGameAction)new SFXAction("Oz"));
/* 48 */     addToBot((AbstractGameAction)new ExpertiseAction((AbstractCreature)abstractPlayer, 8));
/* 49 */     addToBot((AbstractGameAction)new OzSetCostAction(abstractPlayer, abstractPlayer.hand.group));
/* 50 */     boolean powerExists = false;
/* 51 */     for (AbstractPower pow : abstractPlayer.powers) {
/* 52 */       if (pow.ID.equals("shadowverse:OzPower")) {
/* 53 */         powerExists = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 57 */     if (!powerExists) {
/* 58 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new OzPower((AbstractCreature)abstractPlayer)));
/*    */     }
/* 60 */     if (this.upgraded) {
/* 61 */       addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new VoidCard(), 1, true, true));
/*    */     } else {
/* 63 */       addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction((AbstractCard)new VoidCard(), 2, true, true));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 69 */     return (AbstractCard)new Oz();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Rare\Oz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */