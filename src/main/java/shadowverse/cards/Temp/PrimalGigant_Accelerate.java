 package shadowverse.cards.Temp;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Elf;


 public class PrimalGigant_Accelerate extends CustomCard {
   public static final String ID = "shadowverse:PrimalGigant_Accelerate";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PrimalGigant");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PrimalGigant.png";
   public boolean doubleCheck = false;

   public PrimalGigant_Accelerate() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.SELF);
     this.exhaust = true;
     this.baseBlock = 35;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(7);
     } 
   }


   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new HealAction(abstractPlayer,abstractPlayer,4));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new PrimalGigant_Accelerate();
   }
 }

