/*    */ package shadowverse.cards.Temp;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
/*    */ import shadowverse.powers.EarthEssence;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Clarke_Accelerate
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:Clarke_Accelerate";
/* 26 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Clarke");
/* 27 */   public static final String NAME = cardStrings.NAME;
/* 28 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/Clarke.png";
/*    */   
/*    */   public Clarke_Accelerate() {
/* 32 */     super("shadowverse:Clarke", NAME, "img/cards/Clarke.png", 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.SELF);
/* 33 */     this.baseBlock = 3;
/* 34 */     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
/* 35 */     this.baseMagicNumber = 1;
/* 36 */     this.magicNumber = this.baseMagicNumber;
/* 37 */     this.cardsToPreview = (AbstractCard)new VeridicRitual();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 43 */     if (!this.upgraded) {
/* 44 */       upgradeName();
/* 45 */       upgradeBlock(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 51 */     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
/* 52 */     addToBot((AbstractGameAction)new SFXAction("Clarke_Accelerate"));
/* 53 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, 1), 1));
/* 54 */     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 60 */     return (AbstractCard)new Clarke_Accelerate();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Temp\Clarke_Accelerate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */