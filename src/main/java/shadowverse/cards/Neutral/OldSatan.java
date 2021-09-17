 package shadowverse.cards.Neutral;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.random.Random;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import shadowverse.Shadowverse;
 import shadowverse.cards.Temp.*;
 import shadowverse.characters.Witchcraft;

 import java.util.ArrayList;

 public class OldSatan extends AbstractNeutralCard {
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OldSatan"); public static final String ID = "shadowverse:OldSatan";
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION; public static final String IMG_PATH = "img/cards/OldSatan.png";

   public static ArrayList<AbstractCard> returnApocalypseDeck() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new Servant());
     list.add(new Servant());
     list.add(new Servant());
     list.add(new SilentRider());
     list.add(new SilentRider());
     list.add(new SilentRider());
     list.add(new Dis());
     list.add(new Dis());
     list.add(new Dis());
     list.add(new Astaroth());
     return list;
   }

   public OldSatan() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.POWER, CardColor.COLORLESS, CardRarity.RARE, CardTarget.NONE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(3);
     } 
   }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("OldSatan"));
       for (AbstractCard c : abstractPlayer.drawPile.group) {
         addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c, abstractPlayer.drawPile));
       }
       ArrayList<AbstractCard> apocalypseDeck = returnApocalypseDeck();
       for (AbstractCard ca : apocalypseDeck) {
         addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(ca, 1, true, true));
       }

   }
 

 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new OldSatan();
   }
 }

