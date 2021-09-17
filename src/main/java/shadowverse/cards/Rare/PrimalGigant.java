 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.PrimalGigantAction;
import shadowverse.cards.Temp.PrimalGigant_Accelerate;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;


 public class PrimalGigant extends CustomCard {
   public static final String ID = "shadowverse:PrimalGigant";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:PrimalGigant");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/PrimalGigant.png";
   public boolean doubleCheck = false;

   public PrimalGigant() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
     this.exhaust = true;
     this.baseBlock = 35;
     this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(7);
     } 
   }


     public void triggerOnOtherCardPlayed(AbstractCard c) {
         if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
             doubleCheck = true;
             if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                 setCostForTurn(0);
                 this.type = CardType.SKILL;
                 applyPowers();
             }
         }else {
             if (doubleCheck) {
                 doubleCheck = false;
             }else {
                 if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                     setCostForTurn(0);
                     this.type = CardType.SKILL;
                     applyPowers();
                 }
             }
         }
     }

     public void triggerOnGainEnergy(int e, boolean dueToCard) {
         if (EnergyPanel.getCurrentEnergy() >= 4 && this.type != CardType.ATTACK) {
             resetAttributes();
             this.type = CardType.ATTACK;
             applyPowers();
         }
     }

     public void triggerWhenDrawn() {
         if (Shadowverse.Accelerate((AbstractCard)this)) {
             super.triggerWhenDrawn();
             setCostForTurn(0);
             this.type = CardType.SKILL;
         } else {
             this.type = CardType.ATTACK;
         }
         applyPowers();
     }

     @Override
     public void atTurnStart() {
         if (AbstractDungeon.player.hand.group.contains(this)){
             if (EnergyPanel.getCurrentEnergy()<4) {
                 setCostForTurn(0);
                 this.type = CardType.SKILL;
             } else {
                 resetAttributes();
                 this.type = CardType.ATTACK;
             }
             applyPowers();
         }
     }

     public void onMoveToDiscard() {
         resetAttributes();
         this.type = CardType.ATTACK;
         applyPowers();
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
           addToBot((AbstractGameAction)new HealAction(abstractPlayer,abstractPlayer,4));
       }else {
           addToBot((AbstractGameAction)new PrimalGigantAction());
           addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
       }
   }

     public AbstractCard makeSameInstanceOf() {
         AbstractCard card = null;
         if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
             card = (new PrimalGigant_Accelerate()).makeStatEquivalentCopy();
             card.uuid = (new PrimalGigant_Accelerate()).uuid;
         } else {
             card = makeStatEquivalentCopy();
             card.uuid = this.uuid;
         }
         return card;
     }

   public AbstractCard makeCopy() {
     return (AbstractCard)new PrimalGigant();
   }
 }

