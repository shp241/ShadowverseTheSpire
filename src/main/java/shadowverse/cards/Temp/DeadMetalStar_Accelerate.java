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
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


 public class DeadMetalStar_Accelerate
   extends CustomCard
 {
   public static final String ID = "shadowverse:DeadMetalStar_Accelerate";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DeadMetalStar");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DeadMetalStar.png";

   public DeadMetalStar_Accelerate() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.cardsToPreview = (AbstractCard)new ProductMachine();
     this.tags.add((AbstractShadowversePlayer.Enums.MACHINE));
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
       upgradeBlock(5);
     } 
   }
 
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
       addToBot((AbstractGameAction)new SFXAction("DeadMetalStar_Acc"));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 3));
   }
 
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DeadMetalStar_Accelerate();
   }
 }


