 package shadowverse.cards.Uncommon;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.AerialCraftDamageAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;


 public class AerialCraft extends CustomCard {
   public static final String ID = "shadowverse:AerialCraft";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:AerialCraft");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/AerialCraft.png";
   private boolean doubleCheck = false;

   public AerialCraft() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.baseDamage = 10;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(3);
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
   public void applyPowers() {
     if (Shadowverse.Enhance(3))
       setCostForTurn(3);
     else
       resetAttributes();
     super.applyPowers();
   }

   @Override
   public void atTurnStart() {
     if (AbstractDungeon.player.hand.group.contains(this)) {
       if (Shadowverse.Enhance(3)) {
         super.triggerWhenDrawn();
         setCostForTurn(3);
         applyPowers();
       }
     }
   }

   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (AbstractDungeon.player.hasPower("Burst") || AbstractDungeon.player.hasPower("Double Tap") || AbstractDungeon.player.hasPower("Amplified")) {
       doubleCheck = true;
       if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 3) {
         resetAttributes();
         applyPowers();
       }
     } else {
       if (doubleCheck) {
         doubleCheck = false;
       } else {
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
     } else {
       resetAttributes();
     }
     applyPowers();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (this.costForTurn == 3 && Shadowverse.Enhance(3)) {
       addToBot((AbstractGameAction)new AerialCraftDamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
     }else
       addToBot((AbstractGameAction)new AerialCraftDamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new AerialCraft();
   }
 }

