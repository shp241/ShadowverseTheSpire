 package shadowverse.cards.Neutral;

 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.TechnolordAction;
import shadowverse.cards.Temp.Technolord_Accelerate;
import shadowverse.characters.AbstractShadowversePlayer;


 public class Technolord
   extends AbstractNeutralCard
 {
   public static final String ID = "shadowverse:Technolord";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Technolord");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Technolord.png";
   public boolean doubleCheck = false;

   public Technolord() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ALL_ENEMY);
     this.baseDamage = 2;
     this.baseMagicNumber = 0;
     this.magicNumber = this.baseMagicNumber;
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(1);
     } 
   }


   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
       doubleCheck = true;
       if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 3) {
         setCostForTurn(1);
         this.type = CardType.SKILL;
         applyPowers();
       }
     }else {
       if (doubleCheck) {
         doubleCheck = false;
       }else {
         if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 3) {
           setCostForTurn(1);
           this.type = CardType.SKILL;
           applyPowers();
         }
       }
     }
   }

   public void triggerOnGainEnergy(int e, boolean dueToCard) {
     if (EnergyPanel.getCurrentEnergy() >= 3 && this.type != CardType.ATTACK) {
       resetAttributes();
       this.type = CardType.ATTACK;
       applyPowers();
     }
   }

   public void triggerWhenDrawn() {
     if (Shadowverse.Accelerate((AbstractCard)this)) {
       super.triggerWhenDrawn();
       setCostForTurn(1);
       this.type = CardType.SKILL;
     } else {
       this.type = CardType.ATTACK;
     }
     applyPowers();
   }

   @Override
   public void atTurnStart() {
     if (AbstractDungeon.player.hand.group.contains(this)){
         this.type = CardType.ATTACK;
         resetAttributes();
       applyPowers();
     }
   }

   public void onMoveToDiscard() {
     resetAttributes();
     this.type = CardType.ATTACK;
     applyPowers();
   }
   
   public void applyPowers() {
     AbstractShadowversePlayer w = (AbstractShadowversePlayer)AbstractDungeon.player;
     super.applyPowers();
       int count = 0;
       for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
         if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c.type!=CardType.SKILL)
           count++;
       }
       this.rawDescription = cardStrings.DESCRIPTION;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
       initializeDescription();
   }
   


   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL){
       int count = 0;
       for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
         if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c.type!=CardType.SKILL)
           count++;
       }
       this.magicNumber = count;
       for (int i = 0; i < this.magicNumber; i++) {
         addToBot((AbstractGameAction)new AttackDamageRandomEnemyAction((AbstractCard)this, AbstractGameAction.AttackEffect.LIGHTNING));
       }
     }else {
       addToBot((AbstractGameAction)new TechnolordAction());
     }
   }

   public AbstractCard makeSameInstanceOf() {
     AbstractCard card = null;
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       card = (new Technolord_Accelerate()).makeStatEquivalentCopy();
       card.uuid = (new Technolord_Accelerate()).uuid;
       if (this.upgraded){
         card.upgrade();
       }
     } else {
       card = makeStatEquivalentCopy();
       card.uuid = this.uuid;
     }
     return card;
   }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Technolord();
   }
 }


