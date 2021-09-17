 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Elf;


 public class Cassiopeia extends CustomCard {
   public static final String ID = "shadowverse:Cassiopeia";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cassiopeia");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Cassiopeia.png";

   public Cassiopeia() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ALL_ENEMY);
     this.baseDamage = 5;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("Cassiopeia"));
       for (int i = 0; i < abstractPlayer.hand.group.size()-1; i++) {
           addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)this, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Cassiopeia();
   }
 }

