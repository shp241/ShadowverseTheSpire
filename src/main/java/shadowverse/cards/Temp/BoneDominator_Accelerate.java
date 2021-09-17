 package shadowverse.cards.Temp;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.BurialAction;
import shadowverse.cards.Rare.HinterlandGhoul;
import shadowverse.characters.Necromancer;


 public class BoneDominator_Accelerate
   extends CustomCard
 {
   public static final String ID = "shadowverse:BoneDominator_Accelerate";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BoneDominator");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/BoneDominator.png";

   public BoneDominator_Accelerate() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.SELF);
     this.cardsToPreview = (AbstractCard)new HinterlandGhoul();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
         upgradeBlock(5);
         upgradeMagicNumber(2);
     } 
   }
 
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("BoneDominator_Acc"));
       addToBot((AbstractGameAction)new DrawCardAction(1));
       addToBot((AbstractGameAction)new BurialAction(1,(AbstractGameAction)new GainBlockAction(abstractPlayer,this.magicNumber)));
   }
 
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new BoneDominator_Accelerate();
   }
 }


