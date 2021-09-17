 package shadowverse.cards.Temp;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.HealAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
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
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 
 public class ArsMagna
   extends CustomCard {
   public static final String ID = "shadowverse:ArsMagna";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ArsMagna");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ArsMagna.png";
   private boolean canEnhance = false;
   private boolean doubleCheck = false;
   
   public ArsMagna() {
     super("shadowverse:ArsMagna", NAME, "img/cards/ArsMagna.png", 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 12;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
     this.exhaust = true;
     this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
   }
   
   public void triggerWhenDrawn() {
     if (Shadowverse.Enhance(2)) {
       super.triggerWhenDrawn();
       setCostForTurn(2);
       this.canEnhance = true;
       applyPowers();
     } 
   }

     @Override
     public void atTurnStart() {
         if (AbstractDungeon.player.hand.group.contains(this)){
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

     public void triggerOnOtherCardPlayed(AbstractCard c) {
         if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
             doubleCheck = true;
             if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                 resetAttributes();
                 this.canEnhance = false;
                 applyPowers();
             }
         }else {
             if (doubleCheck) {
                 doubleCheck = false;
             }else {
                 if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                     resetAttributes();
                     this.canEnhance = false;
                     applyPowers();
                 }
             }
         }
     }
   
   public void triggerOnGainEnergy(int e, boolean dueToCard) {
     if (EnergyPanel.getCurrentEnergy() >= 2) {
       setCostForTurn(2);
       this.canEnhance = true;
     } else {
         resetAttributes();
         this.canEnhance =false;
     }
       applyPowers();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(4);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("ArsMagna"));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
     if (this.costForTurn == 2 && Shadowverse.Enhance(2)) {
       addToBot((AbstractGameAction)new HealAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, 3));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ArsMagna();
   }
 }

