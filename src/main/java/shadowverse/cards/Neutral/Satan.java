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
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;
 
 public class Satan extends AbstractNeutralCard {
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Satan"); public static final String ID = "shadowverse:Satan";
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION; public static final String IMG_PATH = "img/cards/Satan.png";
   public boolean doubleCheck = false;
   
   public static ArrayList<AbstractCard> returnCocytusDeck() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new Flamelord());
     list.add(new Desire());
     list.add(new Scorpion());
     list.add(new HellBeast());
     list.add(new WrathfulIcefiend());
     list.add(new ViciousCommander());
     list.add(new DemonOfPurgatory());
     list.add(new Behemoth());
     list.add(new InfernalGaze());
     list.add(new InfernalSurge());
     list.add(new HeavenFall());
     list.add(new EarthFall());
     list.add(new Astaroth());
     return list;
   }
   public static AbstractCard returnCocytusCard(Random rng) {
     return returnCocytusDeck().get(rng.random(returnCocytusDeck().size() - 1));
   }
   
   public Satan() {
     super("shadowverse:Satan", NAME, "img/cards/Satan.png", 4, DESCRIPTION, CardType.POWER, CardColor.COLORLESS, CardRarity.RARE, CardTarget.NONE);
     this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(3);
     } 
   }

     @Override
     public void atTurnStart() {
         if (AbstractDungeon.player.hand.group.contains(this)){
             this.type = CardType.POWER;
             resetAttributes();
             applyPowers();
         }
     }

   public void triggerOnOtherCardPlayed(AbstractCard c) {
       if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
           doubleCheck = true;
           if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
               setCostForTurn(1);
               this.type = CardType.SKILL;
               applyPowers();
           }
       }else {
           if (doubleCheck) {
               doubleCheck = false;
           }else {
               if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                   setCostForTurn(1);
                   this.type = CardType.SKILL;
                   applyPowers();
               }
           }
       }
   }
   
   public void triggerOnGainEnergy(int e, boolean dueToCard) {
     if (this.upgraded) {
       if (EnergyPanel.getCurrentEnergy()  >= 3 && this.type != CardType.POWER) {
         resetAttributes();
         this.type = CardType.POWER;
         applyPowers();
       }
     
     } else if (EnergyPanel.getCurrentEnergy()  >= 4 && this.type != CardType.POWER) {
       resetAttributes();
       this.type = CardType.POWER;
       applyPowers();
     } 
   }

     public void onMoveToDiscard() {
         resetAttributes();
         this.type = CardType.POWER;
         applyPowers();
     }

   public void triggerWhenDrawn() {
     if (Shadowverse.Accelerate((AbstractCard)this)) {
       super.triggerWhenDrawn();
       setCostForTurn(1);
       this.type = CardType.SKILL;
     } else {
       this.type = CardType.POWER;
     } 
     applyPowers();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       addToBot((AbstractGameAction)new SFXAction("Satan_Accelerate"));
       ArrayList<String> l = new ArrayList<String>();
       while (true){
           AbstractCard c = returnCocytusCard(AbstractDungeon.cardRandomRng).makeCopy();
           if (!l.contains(c.cardID)){
               l.add(c.cardID);
               addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(c, 1, true, true));
           }
           if (l.size() >= 4){
               break;
           }
       }
     } else {
       addToBot((AbstractGameAction)new SFXAction("Satan"));
       for (AbstractCard c : abstractPlayer.drawPile.group) {
         addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c, abstractPlayer.drawPile));
       }
       ArrayList<AbstractCard> cocytusDeck = returnCocytusDeck();
       for (AbstractCard ca : cocytusDeck) {
         addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(ca, 1, true, true));
       }
     } 
   }
 
   
   public AbstractCard makeSameInstanceOf() {
     AbstractCard card = null;
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       card = (new Satan_Accelerate()).makeStatEquivalentCopy();
       card.uuid = (new Satan_Accelerate()).uuid;
     } else {
       card = makeStatEquivalentCopy();
       card.uuid = this.uuid;
     } 
     return card;
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Satan();
   }
 }

