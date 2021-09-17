 package shadowverse.cards.Temp;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.action.BounceAction;
 import shadowverse.characters.Elf;


 public class WindFairy_Accelerate
   extends CustomCard
 {
   public static final String ID = "shadowverse:WindFairy_Accelerate";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WindFairy");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/WindFairy.png";

   public WindFairy_Accelerate() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.ENEMY);
       this.baseDamage = 12;
       this.baseMagicNumber = 1;
       this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new Whisp();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
     } 
   }
 
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
       addToBot((AbstractGameAction)new BounceAction(1));
       addToBot((AbstractGameAction)new SFXAction("WindFairy_Accelerate"));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
   }
 
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new WindFairy_Accelerate();
   }
 }


