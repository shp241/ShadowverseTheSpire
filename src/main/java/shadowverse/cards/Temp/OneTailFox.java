 package shadowverse.cards.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.VampireDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Necromancer;


 public class OneTailFox
   extends CustomCard
 {
   public static final String ID = "shadowverse:OneTailFox";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OneTailFox");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/OneTailFox.png";

   public OneTailFox() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 2;
     this.baseBlock = 6;
     this.isEthereal = true;
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
       upgradeBlock(2);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new VampireDamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new OneTailFox();
   }
 }

