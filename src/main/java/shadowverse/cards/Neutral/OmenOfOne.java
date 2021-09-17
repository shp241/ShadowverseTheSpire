 package shadowverse.cards.Neutral;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import java.util.ArrayList;
 import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import shadowverse.powers.OmenOfOnePower;
 
 
 public class OmenOfOne
   extends AbstractNeutralCard
 {
   public static final String ID = "shadowverse:OmenOfOne";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfOne");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/OmenOfOne.png";
   
   public OmenOfOne() {
     super("shadowverse:OmenOfOne", NAME, "img/cards/OmenOfOne.png", 2, DESCRIPTION, CardType.POWER, CardColor.COLORLESS, CardRarity.RARE, CardTarget.SELF);
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     boolean powerExists = false;
     boolean deckCheck = true;
     ArrayList<String> tmp = new ArrayList<>();
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:OmenOfOnePower")) {
         powerExists = true;
         break;
       } 
     } 
     for (AbstractCard c : abstractPlayer.drawPile.group) {
       if (tmp.contains(c.cardID)) {
         deckCheck = false;
         break;
       } 
       tmp.add(c.cardID);
     } 
     if (!powerExists && deckCheck) {
               AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
       addToBot((AbstractGameAction)new SFXAction("OmenOfOne"));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new OmenOfOnePower((AbstractCreature)abstractPlayer)));
     } 
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new OmenOfOne();
   }
 }

