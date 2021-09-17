/*    */ package shadowverse.cards.Temp;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
/*    */ import com.megacrit.cardcrawl.actions.common.GainBlockAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
/*    */ import shadowverse.powers.EarthEssence;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class OrichalcumGolem_Accelerate
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:OrichalcumGolem_Accelerate";
/* 26 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OrichalcumGolem");
/* 27 */   public static final String NAME = cardStrings.NAME;
/* 28 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/OrichalcumGolem.png";
/*    */   
/*    */   public OrichalcumGolem_Accelerate() {
/* 32 */     super("shadowverse:OrichalcumGolem", NAME, "img/cards/OrichalcumGolem.png", 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.SELF);
/* 33 */     this.baseBlock = 18;
/* 34 */     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
/* 35 */     this.baseDamage = 7;
/* 36 */     this.baseMagicNumber = 2;
/* 37 */     this.magicNumber = this.baseMagicNumber;
/* 38 */     this.cardsToPreview = (AbstractCard)new VeridicRitual();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 44 */     if (!this.upgraded) {
/* 45 */       upgradeName();
/* 46 */       upgradeBlock(2);
/* 47 */       this.cardsToPreview.upgrade();
/* 48 */       upgradeDamage(3);
/* 49 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 50 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 56 */     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
/* 57 */     boolean powerExists = false;
/* 58 */     AbstractPower earthEssence = null;
/* 59 */     for (AbstractPower pow : abstractPlayer.powers) {
/* 60 */       if (pow.ID.equals("shadowverse:EarthEssence")) {
/* 61 */         earthEssence = pow;
/* 62 */         powerExists = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 66 */     if (powerExists) {
/* 67 */       ((AbstractShadowversePlayer)abstractPlayer).earthCount += earthEssence.amount;
/* 68 */       int x = AbstractDungeon.cardRandomRng.random(earthEssence.amount);
/* 69 */       int y = AbstractDungeon.cardRandomRng.random(earthEssence.amount - x);
/* 70 */       int z = earthEssence.amount - x - y;
/* 71 */       if (this.upgraded) {
/* 72 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, x * 8));
/*    */       } else {
/* 74 */         addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, x * 6));
/*    */       } 
/* 76 */       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, y));
/* 77 */       for (int i = 0; i < z; i++) {
/* 78 */         addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)this, AbstractGameAction.AttackEffect.LIGHTNING));
/*    */       }
/* 80 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, -earthEssence.amount), -earthEssence.amount));
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 87 */     return (AbstractCard)new OrichalcumGolem_Accelerate();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Temp\OrichalcumGolem_Accelerate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */