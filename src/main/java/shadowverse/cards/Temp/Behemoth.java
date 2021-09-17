/*    */ package shadowverse.cards.Temp;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import shadowverse.powers.BehemothPower;
/*    */ 
/*    */ public class Behemoth
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:Behemoth";
/* 19 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Behemoth");
/* 20 */   public static final String NAME = cardStrings.NAME;
/* 21 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/Behemoth.png";
/*    */   
/*    */   public Behemoth() {
/* 25 */     super("shadowverse:Behemoth", NAME, "img/cards/Behemoth.png", 3, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
/* 26 */     this.baseMagicNumber = 35;
/* 27 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 32 */     if (!this.upgraded) {
/* 33 */       upgradeName();
/* 34 */       upgradeMagicNumber(8);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 40 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new BehemothPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 45 */     return (AbstractCard)new Behemoth();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Temp\Behemoth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */