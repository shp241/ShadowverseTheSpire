 package shadowverse.cards.Temp;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;


 public class MagnaGiant_Acc extends CustomCard {
   public static final String ID = "shadowverse:MagnaGiant_Acc";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MagnaGiant");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MagnaGiant.png";

   public MagnaGiant_Acc() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 20;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.baseMagicNumber = 3;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(5);
       upgradeMagicNumber(1);
     } 
   }

     public void applyPowers() {
         super.applyPowers();
         int count = 0;
         for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
             if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c.type!=CardType.SKILL){
                 count++;
             }
         }
         this.rawDescription = cardStrings.DESCRIPTION;
         this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
         this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
         initializeDescription();
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("MagnaGiant_Acc"));
       addToBot((AbstractGameAction)new DrawPileToHandAction_Tag(1,AbstractShadowversePlayer.Enums.MACHINE,null));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MagnaGiant_Acc();
   }
 }
