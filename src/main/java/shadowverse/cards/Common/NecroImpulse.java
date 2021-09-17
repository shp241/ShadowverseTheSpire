 package shadowverse.cards.Common;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.action.NecromanceAction;
import shadowverse.characters.Necromancer;


 public class NecroImpulse
   extends CustomCard {
   public static final String ID = "shadowverse:NecroImpulse";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NecroImpulse");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NecroImpulse.png";

   public NecroImpulse() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.ALL_ENEMY);
     this.baseDamage = 3;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(1);
       upgradeMagicNumber(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     for (int i = 0; i < 4; i++) {
       addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)this, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     }
     addToBot((AbstractGameAction)new NecromanceAction(10,null,(AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new StrengthPower(abstractPlayer,this.magicNumber),this.magicNumber)));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NecroImpulse();
   }
 }

