/*    */ package shadowverse.cards.Common;
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
/*    */ import shadowverse.characters.Witchcraft;
/*    */ import shadowverse.powers.EarthEssence;
/*    */ 
/*    */ public class CommenceExperiment
/*    */   extends CustomCard {
/*    */   public static final String ID = "shadowverse:CommenceExperiment";
/* 19 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CommenceExperiment");
/* 20 */   public static final String NAME = cardStrings.NAME;
/* 21 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/CommenceExperiment.png";
/*    */   
/*    */   public CommenceExperiment() {
/* 25 */     super("shadowverse:CommenceExperiment", NAME, "img/cards/CommenceExperiment.png", 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.SELF);
/* 26 */     this.baseMagicNumber = 1;
/* 27 */     this.magicNumber = this.baseMagicNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 32 */     if (!this.upgraded) {
/* 33 */       upgradeName();
/* 34 */       this.selfRetain = true;
/* 35 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 36 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 42 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 47 */     return (AbstractCard)new CommenceExperiment();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Common\CommenceExperiment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */