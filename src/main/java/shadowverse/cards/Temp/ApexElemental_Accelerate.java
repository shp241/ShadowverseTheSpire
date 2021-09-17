 package shadowverse.cards.Temp;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 
 
 
 
 
 public class ApexElemental_Accelerate
   extends CustomCard
 {
   public static final String ID = "shadowverse:ApexElemental_Accelerate";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ApexElemental");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ApexElemental.png";
   
   public ApexElemental_Accelerate() {
     super("shadowverse:ApexElemental", NAME, "img/cards/ApexElemental.png", 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 15;
     this.cardsToPreview = (AbstractCard)new NaterranGreatTree();
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
     } 
   }
 
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     boolean powerExists = false;
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:NaterranTree")) {
         powerExists = true;
         break;
       } 
     } 
     if (powerExists) {
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, 9, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
       addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, "shadowverse:NaterranTree"));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
     } else {
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, 6, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
     } 
   }
 
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ApexElemental_Accelerate();
   }
 }


