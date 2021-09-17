/*    */ package shadowverse.cards.Temp;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
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
/*    */ public class VeridicRitual
/*    */   extends CustomCard {
/*    */   public static final String ID = "shadowverse:VeridicRitual";
/* 22 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:VeridicRitual");
/* 23 */   public static final String NAME = cardStrings.NAME;
/* 24 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/VeridicRitual.png";
/*    */   
/*    */   public VeridicRitual() {
/* 28 */     super("shadowverse:VeridicRitual", NAME, "img/cards/VeridicRitual.png", 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
/* 29 */     this.baseDamage = 9;
/* 30 */     this.baseMagicNumber = 1;
/* 31 */     this.magicNumber = this.baseMagicNumber;
/* 32 */     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
/* 33 */     this.exhaust = true;
/* 34 */     this.selfRetain = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 39 */     if (!this.upgraded) {
/* 40 */       upgradeName();
/* 41 */       upgradeDamage(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 47 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/* 48 */     boolean powerExists = false;
/* 49 */     for (AbstractPower pow : abstractPlayer.powers) {
/* 50 */       if (pow.ID.equals("shadowverse:EarthEssence")) {
/* 51 */         powerExists = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 55 */     if (powerExists) {
/* 56 */       ((AbstractShadowversePlayer)abstractPlayer).earthCount++;
/* 57 */       addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, this.magicNumber));
/* 58 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, -1), -1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 64 */     return (AbstractCard)new VeridicRitual();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Temp\VeridicRitual.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */