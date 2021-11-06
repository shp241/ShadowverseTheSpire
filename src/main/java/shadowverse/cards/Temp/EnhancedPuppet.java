 package shadowverse.cards.Temp;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Nemesis;


 public class EnhancedPuppet
   extends Puppet
 {
   public static final String ID = "shadowverse:EnhancedPuppet";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EnhancedPuppet");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/EnhancedPuppet.png";

   public EnhancedPuppet() {
     super();
     this.cardID = ID;
     this.textureImg = IMG_PATH;
     if (IMG_PATH != null) {
       this.loadCardImage(IMG_PATH);
     }
     this.rawDescription = DESCRIPTION;
     initializeDescription();
     this.baseDamage = 6;
     this.exhaust = true;
     this.isEthereal = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new EnhancedPuppet();
   }
 }

