 package shadowverse.cards.Common;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Vampire;

 public class BloodPact
   extends CustomCard
 {
   public static final String ID = "shadowverse:BloodPact";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BloodPact");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/BloodPact.png";
   public static final int BASE_COST = 3;

   public BloodPact() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
   }
 

 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)p, (AbstractCreature)p, 2));
       addToBot((AbstractGameAction)new DrawCardAction(this.magicNumber));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new BloodPact();
   }
 }


