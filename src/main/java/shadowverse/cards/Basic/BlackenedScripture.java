 package shadowverse.cards.Basic;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import shadowverse.characters.Bishop;


 public class BlackenedScripture
   extends CustomCard {
   public static final String ID = "shadowverse:BlackenedScripture";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:BlackenedScripture");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/BlackenedScripture.png";

   public BlackenedScripture() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.BASIC, CardTarget.ENEMY);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.baseDamage = 9;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeMagicNumber(1);
       upgradeDamage(3);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
     if (m != null && !m.isDeadOrEscaped()) {
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new WeakPower((AbstractCreature)m, this.magicNumber, false), this.magicNumber));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)p, (AbstractPower)new FrailPower((AbstractCreature)m, this.magicNumber, false), this.magicNumber));
     }
     addToBot((AbstractGameAction)new JudgementAction((AbstractCreature)m, this.damage));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new BlackenedScripture();
   }
 }

