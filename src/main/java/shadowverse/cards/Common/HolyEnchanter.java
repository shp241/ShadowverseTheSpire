 package shadowverse.cards.Common;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Bishop;


 public class HolyEnchanter extends CustomCard {
   public static final String ID = "shadowverse:HolyEnchanter";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HolyEnchanter");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/HolyEnchanter.png";

   public HolyEnchanter() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 6;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("HolyEnchanter"));
     addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
     addToBot((AbstractGameAction)new DrawCardAction(1));
     addToBot((AbstractGameAction)new DrawCardAction(abstractPlayer.currentBlock/this.block));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new HolyEnchanter();
   }
 }

