 package shadowverse.cards.Temp;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Common.GreenWoodGuardian;
import shadowverse.characters.Elf;


 public class Lymaga_Accelerate
   extends CustomCard
 {
   public static final String ID = "shadowverse:Lymaga_Accelerate";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lymaga");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Lymaga.png";

   public Lymaga_Accelerate() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 32;
     this.cardsToPreview = (AbstractCard)new GreenWoodGuardian();
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(10);
     } 
   }

   
   public void applyPowers() {
       super.applyPowers();
       int count = 0;
       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
           if (c instanceof GreenWoodGuardian)
               count++;
       }
       this.rawDescription = cardStrings.DESCRIPTION;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
       initializeDescription();
   }


   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("Lymaga_Acc"));
           AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
           addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Lymaga_Accelerate();
   }
 }


