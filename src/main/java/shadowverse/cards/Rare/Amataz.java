 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.DexterityPower;
 import com.megacrit.cardcrawl.powers.PlatedArmorPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import shadowverse.cards.Temp.Fairy;
 import shadowverse.characters.Elf;
 import shadowverse.powers.AmatazPower;


 public class Amataz extends CustomCard {
   public static final String ID = "shadowverse:Amataz";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Amataz");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Amataz.png";

   public Amataz() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
     this.baseBlock = 7;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(5);
       upgradeMagicNumber(2);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("Amataz"));
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
       int count = 0;
       for (AbstractCard c:abstractPlayer.hand.group){
           if (c instanceof Fairy)
               count++;
       }
       if (count>=2){
           addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,3));
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature) abstractPlayer,(AbstractCreature) abstractPlayer,(AbstractPower)new PlatedArmorPower((AbstractCreature)abstractPlayer,2),2));
       }
       if ((count>=4)){
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new StrengthPower((AbstractCreature)abstractPlayer, 1), 1));
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new DexterityPower((AbstractCreature)abstractPlayer, 1), 1));
       }
       if (count>=6){
           addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer,(AbstractCreature)abstractPlayer,(AbstractPower)new AmatazPower((AbstractCreature)abstractPlayer,this.magicNumber),this.magicNumber));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Amataz();
   }
 }

