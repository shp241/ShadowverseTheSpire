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
/*    */ import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.EarthEssence;

/*    */
/*    */ public class Petrification
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:Petrification";
/* 21 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Petrification");
/* 22 */   public static final String NAME = cardStrings.NAME;
/* 23 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/Petrification.png";
/*    */   
/*    */   public Petrification() {
/* 27 */     super("shadowverse:Petrification", NAME, "img/cards/Petrification.png", 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.ENEMY);
/* 28 */     this.baseMagicNumber = 2;
/* 29 */     this.magicNumber = this.baseMagicNumber;
/* 30 */     this.exhaust = true;
/* 31 */     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 36 */     if (!this.upgraded) {
/* 37 */       upgradeName();
/* 38 */       upgradeBaseCost(1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 44 */     boolean powerExists = false;
/* 45 */     for (AbstractPower pow : abstractPlayer.powers) {
/* 46 */       if (pow.ID.equals("shadowverse:EarthEssence")) {
/* 47 */         powerExists = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 51 */     if (powerExists) {
/* 52 */       ((AbstractShadowversePlayer)abstractPlayer).earthCount++;
/* 53 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new IntangiblePlayerPower((AbstractCreature)abstractPlayer, 1), 1));
               addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, -1), -1));
/*    */     } else {
/* 55 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new StrengthPower((AbstractCreature)abstractMonster, -this.magicNumber), -this.magicNumber));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 61 */     return (AbstractCard)new Petrification();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Common\Petrification.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */