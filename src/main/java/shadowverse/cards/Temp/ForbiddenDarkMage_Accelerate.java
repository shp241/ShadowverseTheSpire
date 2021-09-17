/*    */ package shadowverse.cards.Temp;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
/*    */ import shadowverse.powers.DarkMagePower;
/*    */ import shadowverse.powers.EarthEssence;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ForbiddenDarkMage_Accelerate
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:ForbiddenDarkMage";
/* 30 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ForbiddenDarkMage");
/* 31 */   public static final String NAME = cardStrings.NAME;
/* 32 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/ForbiddenDarkMage.png";
/*    */   
/*    */   public ForbiddenDarkMage_Accelerate() {
/* 36 */     super("shadowverse:ForbiddenDarkMage", NAME, "img/cards/ForbiddenDarkMage.png", 0, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
/* 37 */     this.baseDamage = 7;
/* 38 */     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);

/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 43 */     if (!this.upgraded) {
/* 44 */       upgradeName();
/* 45 */       upgradeDamage(2);
/* 46 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 47 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 55 */     addToBot((AbstractGameAction)new SFXAction("DarkMagePower"));
/* 56 */     boolean powerExists = false;
/* 57 */     AbstractPower earthEssence = null;
/* 58 */     for (AbstractPower pow : abstractPlayer.powers) {
/* 59 */       if (pow.ID.equals("shadowverse:EarthEssence")) {
/* 60 */         earthEssence = pow;
/* 61 */         powerExists = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 65 */     if (powerExists) {
/* 66 */       ((AbstractShadowversePlayer)abstractPlayer).earthCount += earthEssence.amount;
/* 67 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, -earthEssence.amount), -earthEssence.amount));
/* 68 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new DarkMagePower((AbstractCreature)abstractPlayer, earthEssence.amount), earthEssence.amount));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 74 */     return (AbstractCard)new ForbiddenDarkMage_Accelerate();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Temp\ForbiddenDarkMage_Accelerate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */