 package shadowverse.cards.Rare;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
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
import shadowverse.action.InvocationAction;
import shadowverse.cards.Common.GreenWoodGuardian;
import shadowverse.cards.Temp.Lymaga_Accelerate;
import shadowverse.cards.Temp.Lymaga_NoAcc;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;


 public class Lymaga
   extends CustomCard
 {
   public static final String ID = "shadowverse:Lymaga";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Lymaga");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Lymaga.png";
   public static boolean dupCheck = true;
     public boolean doubleCheck = false;

   public Lymaga() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 32;
     this.cardsToPreview = (AbstractCard)new GreenWoodGuardian();
     this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(10);
     } 
   }

     public void triggerOnOtherCardPlayed(AbstractCard c) {
         dupCheck = true;
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
         if (EnergyPanel.getCurrentEnergy() >= 3 && this.type != CardType.ATTACK) {
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

     public void onMoveToDiscard() {
         resetAttributes();
         this.type = CardType.ATTACK;
         applyPowers();
     }
   
   public void applyPowers() {
       super.applyPowers();
       int count = 0;
       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
           if (c instanceof GreenWoodGuardian)
               count++;
       }
       this.rawDescription = cardStrings.DESCRIPTION;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
       this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
       initializeDescription();
   }

   
   public void atTurnStart() {
       if (AbstractDungeon.player.hand.group.contains(this)){
               resetAttributes();
               this.type = CardType.ATTACK;
           applyPowers();
       }
       int count = 0;
       for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
           if (c instanceof GreenWoodGuardian)
               count++;
       }
     if (count >= 6 && dupCheck) {
         dupCheck = false;
       if (AbstractDungeon.player.discardPile.contains((AbstractCard)this)) {
         addToBot((AbstractGameAction)new ReduceCostForTurnAction((AbstractCard)this, 9));
         addToBot((AbstractGameAction)new DiscardToHandAction((AbstractCard)this));
       } else if (AbstractDungeon.player.drawPile.contains((AbstractCard)this)) {
         addToBot((AbstractGameAction)new ReduceCostForTurnAction((AbstractCard)this, 9));
         addToBot((AbstractGameAction)new InvocationAction((AbstractCard)this));
       } 
     }else if (count < 6){
       dupCheck = true;
     }
   }

     @Override
     public void triggerOnCardPlayed(AbstractCard cardPlayed) {
         dupCheck = true;
     }

   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
           addToBot((AbstractGameAction)new SFXAction("Lymaga_Acc"));
           AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
           AbstractCard a = (AbstractCard)new Lymaga_NoAcc();
           if (this.upgraded)
               a.upgrade();
           addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
           addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(a,1));
       }else {
           addToBot((AbstractGameAction)new SFXAction("Lymaga"));
           addToBot((AbstractGameAction)new WaitAction(0.8F));
           addToBot((AbstractGameAction)new SFXAction("ATTACK_HEAVY"));
           addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
       }
   }

     public AbstractCard makeSameInstanceOf() {
         AbstractCard card = null;
         if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
             card = (new Lymaga_Accelerate()).makeStatEquivalentCopy();
             card.uuid = (new Lymaga_Accelerate()).uuid;
         } else {
             card = makeStatEquivalentCopy();
             card.uuid = this.uuid;
         }
         return card;
     }
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Lymaga();
   }
 }


