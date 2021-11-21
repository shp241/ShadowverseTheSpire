 package shadowverse.cards.Common;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ReduceCountDownAction;
import shadowverse.characters.Bishop;


 public class HallowedDogma
   extends CustomCard {
   public static final String ID = "shadowverse:HallowedDogma";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:HallowedDogma");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/HallowedDogma.png";

   public HallowedDogma() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.NONE);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new ReduceCountDownAction(2));
     addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new HallowedDogma();
   }
 }

