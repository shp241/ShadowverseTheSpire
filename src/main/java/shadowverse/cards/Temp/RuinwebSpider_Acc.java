 package shadowverse.cards.Temp;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.RuinwebSpiderPower;


 public class RuinwebSpider_Acc
   extends CustomCard
 {
   public static final String ID = "shadowverse:RuinwebSpider_Acc";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RuinwebSpider");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RuinwebSpider.png";

   public RuinwebSpider_Acc() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.SELF);
       this.baseMagicNumber = 10;
       this.magicNumber = this.baseMagicNumber;
       this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 
   
   public void upgrade() {
       if (!this.upgraded) {
           upgradeName();
           upgradeMagicNumber(-1);
           this.isInnate = true;
           this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
           initializeDescription();
       }
   }
 
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new RuinwebSpiderPower(abstractPlayer,this.magicNumber),this.magicNumber));
   }
 
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new RuinwebSpider_Acc();
   }
 }


