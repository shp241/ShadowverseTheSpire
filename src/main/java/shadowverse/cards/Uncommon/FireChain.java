 package shadowverse.cards.Uncommon;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 
 
 
 public class FireChain
   extends CustomCard
 {
   public static final String ID = "shadowverse:FireChain";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FireChain");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/FireChain.png";
   
   public FireChain() {
     super("shadowverse:FireChain", NAME, "img/cards/FireChain.png", 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
     this.baseDamage = 3;
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST_ATTACK);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.type == CardType.SKILL) {
       flash();
       
       this.magicNumber = ++this.baseMagicNumber;
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     for (int i = 0; i < this.magicNumber; i++) {
       addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)this, AbstractGameAction.AttackEffect.FIRE));
     }
   }
 
   
   public void applyPowers() {
     super.applyPowers();
     int count = this.magicNumber;
     this.rawDescription = cardStrings.DESCRIPTION;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
     initializeDescription();
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new FireChain();
   }
 }

