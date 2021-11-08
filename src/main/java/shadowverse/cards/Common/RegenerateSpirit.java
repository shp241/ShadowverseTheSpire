 package shadowverse.cards.Common;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.HealAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.tempCards.Miracle;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import shadowverse.Shadowverse;
 import shadowverse.action.ReanimateAction;
 import shadowverse.cards.Status.EvolutionPoint;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Elf;
 import shadowverse.characters.Necromancer;

 public class RegenerateSpirit
   extends CustomCard {
   public static final String ID = "shadowverse:RegenerateSpirit";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RegenerateSpirit");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/RegenerateSpirit.png";
   private boolean doubleCheck = false;

   public RegenerateSpirit() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.NONE);
     this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
     this.isEthereal = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.isEthereal = false;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;;
       initializeDescription();
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
     public void applyPowers(){
         if (Shadowverse.Enhance(2))
             setCostForTurn(2);
         else
             resetAttributes();
         super.applyPowers();
     }

     @Override
     public void atTurnStart() {
       if (AbstractDungeon.player.hand.group.contains(this)){
           if (Shadowverse.Enhance(2)) {
               super.triggerWhenDrawn();
               setCostForTurn(2);
               applyPowers();
           }
       }
     }

     public void triggerOnOtherCardPlayed(AbstractCard c) {
         if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
             doubleCheck = true;
             if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                 resetAttributes();
                 applyPowers();
             }
         }else {
             if (doubleCheck) {
                 doubleCheck = false;
             }else {
                 if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                     resetAttributes();
                     applyPowers();
                 }
             }
         }
     }
   
   public void triggerOnGainEnergy(int e, boolean dueToCard) {
     if (EnergyPanel.getCurrentEnergy() >= 2) {
       setCostForTurn(1);
     }  else {
         resetAttributes();
     }
       applyPowers();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (this.costForTurn == 2 && Shadowverse.Enhance(2)) {
         addToBot((AbstractGameAction)new ReanimateAction(2));
     }
     addToBot((AbstractGameAction)new ReanimateAction(1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new RegenerateSpirit();
   }
 }


