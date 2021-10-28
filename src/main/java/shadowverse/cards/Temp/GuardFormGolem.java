 package shadowverse.cards.Temp;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;


 public class GuardFormGolem
   extends CustomCard
 {
   public static final String ID = "shadowverse:GuardFormGolem";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GuardFormGolem");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/GuardFormGolem.png";
   private boolean doubleCheck = false;

   public GuardFormGolem() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 12;
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.ARTIFACT);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
     } 
   }
   public void triggerWhenDrawn() {
     if (Shadowverse.Enhance(2)) {
       super.triggerWhenDrawn();
       setCostForTurn(2);
       applyPowers();
     }
   }

   @Override
   public void applyPowers() {
     if (Shadowverse.Enhance(2))
       setCostForTurn(2);
     else if (this.costForTurn>0)
       resetAttributes();
     super.applyPowers();
   }

   @Override
   public void atTurnStart() {
     if (AbstractDungeon.player.hand.group.contains(this)) {
       if (Shadowverse.Enhance(2)) {
         super.triggerWhenDrawn();
         setCostForTurn(2);
         applyPowers();
       }
     }
   }

   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (AbstractDungeon.player.hasPower("Burst") || AbstractDungeon.player.hasPower("Double Tap") || AbstractDungeon.player.hasPower("Amplified")) {
       doubleCheck = true;
       if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
         resetAttributes();
         applyPowers();
       }
     } else {
       if (doubleCheck) {
         doubleCheck = false;
       } else {
         if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
           resetAttributes();
           applyPowers();
         }
       }
     }
   }

   public void triggerOnGainEnergy(int e, boolean dueToCard) {
     if (EnergyPanel.getCurrentEnergy() >= 2) {
       setCostForTurn(2);
     } else {
       resetAttributes();
     }
     applyPowers();
   }

   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (this.costForTurn == 2 && Shadowverse.Enhance(2)) {
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block*2));
     }else {
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new GuardFormGolem();
   }
 }

