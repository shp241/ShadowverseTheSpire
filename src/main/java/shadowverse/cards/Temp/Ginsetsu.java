 package shadowverse.cards.Temp;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.Necromancer;


 public class Ginsetsu
   extends CustomCard
 {
   public static final String ID = "shadowverse:Ginsetsu";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ginsetsu");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Ginsetsu.png";

   public Ginsetsu() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 9;
     this.baseBlock = 18;
     this.cardsToPreview = (AbstractCard)new OneTailFox();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
       upgradeBlock(9);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Ginsetsu"));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
     addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     c.setCostForTurn(0);
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,4));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Ginsetsu();
   }
 }

