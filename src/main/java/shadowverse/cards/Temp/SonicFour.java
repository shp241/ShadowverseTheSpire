/*    */ package shadowverse.cards.Temp;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.core.Settings;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
/*    */ import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
/*    */ import shadowverse.powers.SonicFourPower;
/*    */ 
/*    */ public class SonicFour
/*    */   extends CustomCard
/*    */ {
/*    */   public static final String ID = "shadowverse:SonicFour";
/* 27 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SonicFour");
/* 28 */   public static final String NAME = cardStrings.NAME;
/* 29 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/SonicFour.png";
/*    */   
/*    */   public SonicFour() {
/* 33 */     super("shadowverse:SonicFour", NAME, "img/cards/SonicFour.png", 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
/* 34 */     this.baseDamage = 8;
/* 35 */     this.baseMagicNumber = 1;
/* 36 */     this.magicNumber = this.baseMagicNumber;
/* 37 */     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
/* 38 */     this.exhaust = true;
/* 39 */     this.selfRetain = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 44 */     if (!this.upgraded) {
/* 45 */       upgradeName();
/* 46 */       upgradeDamage(4);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 52 */     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
/* 53 */     addToBot((AbstractGameAction)new SFXAction("SonicFour"));
/* 54 */     if (Settings.FAST_MODE) {
/* 55 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, abstractPlayer.hb.cX, abstractPlayer.hb.cY), 0.1F));
/*    */     } else {
/* 57 */       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, abstractPlayer.hb.cX, abstractPlayer.hb.cY), 0.3F));
/*    */     } 
/* 59 */     addToBot((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
/* 60 */     boolean powerExists = false;
/* 61 */     for (AbstractPower pow : abstractPlayer.powers) {
/* 62 */       if (pow.ID.equals("shadowverse:SonicFourPower")) {
/* 63 */         powerExists = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 67 */     if (!powerExists) {
/* 68 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new SonicFourPower((AbstractCreature)abstractPlayer)));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 74 */     return (AbstractCard)new SonicFour();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Temp\SonicFour.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */