/*    */ package shadowverse.cards.Uncommon;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
/*    */ import com.megacrit.cardcrawl.actions.AbstractGameAction;
/*    */ import com.megacrit.cardcrawl.actions.common.*;
/*    */
/*    */
/*    */
/*    */ import com.megacrit.cardcrawl.actions.utility.SFXAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
/*    */ import com.megacrit.cardcrawl.cards.DamageInfo;
/*    */ import com.megacrit.cardcrawl.characters.AbstractPlayer;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
/*    */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.CardStrings;
/*    */ import com.megacrit.cardcrawl.monsters.AbstractMonster;
/*    */ import com.megacrit.cardcrawl.powers.AbstractPower;
/*    */ import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import shadowverse.cards.Temp.NaterranGreatTree;
/*    */ import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
/*    */ 
/*    */ public class Stormelementalist extends CustomCard {
/*    */   public static final String ID = "shadowverse:Stormelementalist";
/* 24 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Stormelementalist");
/* 25 */   public static final String NAME = cardStrings.NAME;
/* 26 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/Stormelementalist.png";
/*    */   
/*    */   public Stormelementalist() {
/* 30 */     super("shadowverse:Stormelementalist", NAME, "img/cards/Stormelementalist.png", 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
/* 31 */     this.baseBlock = 7;
/* 32 */     this.cardsToPreview = (AbstractCard)new NaterranGreatTree();
/* 33 */     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 38 */     if (!this.upgraded) {
/* 39 */       upgradeName();
/* 40 */       upgradeBlock(3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 46 */     boolean powerExists = false;
/* 47 */     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
/* 48 */     addToBot((AbstractGameAction)new SFXAction("Stormelementalist"));
/* 49 */     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
/* 50 */     for (AbstractPower pow : abstractPlayer.powers) {
/* 51 */       if (pow.ID.equals("shadowverse:NaterranTree")) {
/* 52 */         powerExists = true;
/*    */         break;
/*    */       } 
/*    */     } 
/* 56 */     if (powerExists) {
/* 57 */       addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, "shadowverse:NaterranTree"));
/* 58 */             addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new PlatedArmorPower((AbstractCreature)abstractPlayer, 2), 2));
/* 59 */       addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo((AbstractCreature)abstractPlayer, 4, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
/* 60 */       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 66 */     return (AbstractCard)new Stormelementalist();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Uncommon\Stormelementalist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */