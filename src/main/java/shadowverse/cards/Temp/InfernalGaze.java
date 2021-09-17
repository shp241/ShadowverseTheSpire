/*    */ package shadowverse.cards.Temp;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DrawCardAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.GainStrengthPower;
/*    */ import com.megacrit.cardcrawl.powers.StrengthPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
/*    */ 
/*    */ public class InfernalGaze
/*    */   extends CustomCard {
/*    */   public static final String ID = "shadowverse:InfernalGaze";
/* 25 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:InfernalGaze");
/* 26 */   public static final String NAME = cardStrings.NAME;
/* 27 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/InfernalGaze.png";
/*    */   
/*    */   public InfernalGaze() {
/* 31 */     super("shadowverse:InfernalGaze", NAME, "img/cards/InfernalGaze.png", 0, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
/* 32 */     this.baseMagicNumber = 10;
/* 33 */     this.magicNumber = this.baseMagicNumber;
/* 34 */     this.exhaust = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 39 */     if (!this.upgraded) {
/* 40 */       upgradeName();
/* 41 */       upgradeMagicNumber(2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 47 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartBuffEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY)));
/* 48 */     addToBot((AbstractGameAction)new SFXAction("InfernalGaze"));
/* 49 */     addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)abstractPlayer, 1));
/* 50 */     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters)
/* 51 */       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)abstractPlayer, (AbstractPower)new StrengthPower((AbstractCreature)mo, -this.magicNumber), -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE)); 
/* 52 */     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
/* 53 */       if (!mo.hasPower("Artifact")) {
/* 54 */         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)abstractPlayer, (AbstractPower)new GainStrengthPower((AbstractCreature)mo, this.magicNumber), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 60 */     return (AbstractCard)new InfernalGaze();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Temp\InfernalGaze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */