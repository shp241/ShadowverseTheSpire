 package shadowverse.cards.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;

 
 
 
 
 
 public class Technolord_Accelerate
   extends CustomCard
 {
   public static final String ID = "shadowverse:Technolord_Accelerate";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Technolord");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Clarke.png";
   
   public Technolord_Accelerate() {
       super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
       this.baseDamage = 2;
       this.baseMagicNumber = 0;
       this.magicNumber = this.baseMagicNumber;
       this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
   }
 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(1);
     } 
   }

     public void applyPowers() {
         AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
         super.applyPowers();
         int count = ((AbstractShadowversePlayer) AbstractDungeon.player).mechaCount;
         this.rawDescription = cardStrings.DESCRIPTION;
         this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
         this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
         initializeDescription();
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       this.magicNumber = ((AbstractShadowversePlayer)abstractPlayer).mechaCount;
       for (int i = 0; i < this.magicNumber; i++) {
           addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)this, AbstractGameAction.AttackEffect.LIGHTNING));
       }
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Technolord_Accelerate();
   }
 }

