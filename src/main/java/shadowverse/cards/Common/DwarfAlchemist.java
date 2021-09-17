/*    */ package shadowverse.cards.Common;
/*    */ 
/*    */ import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.EarthEssence;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */ 
/*    */ public class DwarfAlchemist extends CustomCard {
/*    */   public static final String ID = "shadowverse:DwarfAlchemist";
/* 21 */   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DwarfAlchemist");
/* 22 */   public static final String NAME = cardStrings.NAME;
/* 23 */   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
/*    */   public static final String IMG_PATH = "img/cards/DwarfAlchemist.png";
/*    */   
/*    */   public DwarfAlchemist() {
/* 27 */     super("shadowverse:DwarfAlchemist", NAME, "img/cards/DwarfAlchemist.png", 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.ENEMY);
/* 28 */     this.baseBlock = 5;
/*    */   }
/*    */ 
/*    */   
/*    */   public void upgrade() {
/* 33 */     if (!this.upgraded) {
/* 34 */       upgradeName();
/* 35 */       upgradeBlock(2);
/* 36 */       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
/* 37 */       initializeDescription();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
/* 43 */     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
/* 44 */     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, 1), 1));
/* 45 */     if (this.upgraded) {
/* 46 */       addToBot((AbstractGameAction)new DrawPileToHandAction_Tag(1, AbstractShadowversePlayer.Enums.EARTH_RITE, null));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AbstractCard makeCopy() {
/* 52 */     return (AbstractCard)new DwarfAlchemist();
/*    */   }
/*    */ }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Common\DwarfAlchemist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */