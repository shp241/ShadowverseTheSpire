 package shadowverse.cards.Uncommon;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.GetEPAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;

 public class Aerin
   extends CustomCard {
   public static final String ID = "shadowverse:Aerin";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Aerin");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Aerin.png";
   private boolean doubleCheck = false;

   public Aerin() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
     this.baseBlock = 12;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(4);
     } 
   }
   
   public void triggerWhenDrawn() {
       if (Shadowverse.Enhance(3)) {
           super.triggerWhenDrawn();
           setCostForTurn(3);
           applyPowers();
       }
   }
     @Override
     public void applyPowers(){
         if (Shadowverse.Enhance(3))
             setCostForTurn(3);
         else
             resetAttributes();
         super.applyPowers();
     }

     @Override
     public void atTurnStart() {
       if (AbstractDungeon.player.hand.group.contains(this)){
           if (Shadowverse.Enhance(3)) {
               super.triggerWhenDrawn();
               setCostForTurn(3);
               applyPowers();
           }
       }
     }

     public void triggerOnOtherCardPlayed(AbstractCard c) {
         if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
             doubleCheck = true;
             if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 3) {
                 resetAttributes();
                 applyPowers();
             }
         }else {
             if (doubleCheck) {
                 doubleCheck = false;
             }else {
                 if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 3) {
                     resetAttributes();
                     applyPowers();
                 }
             }
         }
     }
   
   public void triggerOnGainEnergy(int e, boolean dueToCard) {
     if (EnergyPanel.getCurrentEnergy() >= 3) {
       setCostForTurn(3);
     }  else {
         resetAttributes();
     }
       applyPowers();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("Aerin"));
       addToBot((AbstractGameAction)new HealAction(abstractPlayer,abstractPlayer,3));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
     if (this.costForTurn == 3 && Shadowverse.Enhance(3)) {
       addToBot((AbstractGameAction)new GetEPAction(true,1));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Aerin();
   }
 }


