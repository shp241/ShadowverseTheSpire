 package shadowverse.cards.Uncommon;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.DiscardAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.unique.ExpertiseAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Vampire;


 public class EternalContract
   extends CustomCard
 {
   public static final String ID = "shadowverse:EternalContract";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EternalContract");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/EternalContract.png";

   public EternalContract() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.NONE);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.FES);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("EternalContract"));
       addToBot((AbstractGameAction)new DrawCardAction((AbstractCreature)p, this.magicNumber));
       addToBot((AbstractGameAction)new DiscardAction((AbstractCreature)p, (AbstractCreature)p, this.magicNumber, false));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new EternalContract();
   }
 }

