/*    */ package shadowverse.cards.Uncommon;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.animations.VFXAction;
/*    */ import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
/*    */ import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
/*    */ import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
/*    */ import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
/*    */ import shadowverse.cards.Temp.NaterranGreatTree;
/*    */ import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
/*    */ 
/*    */ public class Pyromancer extends CustomCard {
/* 24 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Pyromancer"); public static final String ID = "shadowverse:Pyromancer";
/* 25 */   public static final String NAME = cardStrings.NAME;
/* 26 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/Pyromancer.png";
/*    */   
/*    */   public Pyromancer() {
/* 30 */     super("shadowverse:Pyromancer", NAME, "img/cards/Pyromancer.png", 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
/* 31 */     this.baseDamage = 9;
/* 32 */     this.cardsToPreview = (AbstractCard)new NaterranGreatTree();
/* 33 */     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 38 */     if (!this.upgraded) {
/* 39 */       upgradeName();
/* 40 */       upgradeDamage(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 46 */     boolean powerExists = false;
/* 47 */     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
/* 48 */     addToBot((AbstractGameAction)new SFXAction("Pyromancer"));
/* 49 */     for (AbstractPower pow : abstractPlayer.powers) {
/* 50 */       if (pow.ID.equals("shadowverse:NaterranTree")) {
/* 51 */         powerExists = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 55 */     if (powerExists) {
/* 56 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, "shadowverse:NaterranTree"));
/* 57 */       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new CleaveEffect(), 0.1F));
/* 58 */       addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage * 2, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
/* 59 */       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
/*    */     } else {
/* 61 */       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new CleaveEffect(), 0.1F));
/* 62 */       addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 68 */     return (AbstractCard)new Pyromancer();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Uncommon\Pyromancer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */