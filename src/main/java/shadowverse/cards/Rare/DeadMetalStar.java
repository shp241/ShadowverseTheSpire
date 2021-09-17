 package shadowverse.cards.Rare;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
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
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import shadowverse.Shadowverse;
import shadowverse.action.ReanimateAction;
 import shadowverse.cards.Common.DemonicProcession;
 import shadowverse.cards.Common.HungrySlash;
 import shadowverse.cards.Common.SpiritCurator;
 import shadowverse.cards.Temp.DeadMetalStar_Accelerate;
 import shadowverse.cards.Temp.InstantPotion;
 import shadowverse.cards.Temp.ProductMachine;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


 public class DeadMetalStar
   extends CustomCard
 {
   public static final String ID = "shadowverse:DeadMetalStar";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DeadMetalStar");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DeadMetalStar.png";
   public boolean doubleCheck = false;



   public DeadMetalStar() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 25;
     this.baseBlock = 25;
     this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
     this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
     this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
     this.cardsToPreview = (AbstractCard)new ProductMachine();
   }
 
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
       upgradeBlock(5);
     } 
   }
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
       if (c instanceof DemonicProcession||c instanceof TheLovers||c instanceof HungrySlash||c instanceof SpiritCurator||c instanceof Ferry||c instanceof InstantPotion){
           this.type = CardType.ATTACK;
           this.resetAttributes();
           return;
       }
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
     if (EnergyPanel.getCurrentEnergy() >= 4 && this.type != CardType.ATTACK) {
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
               resetAttributes();
               this.type = CardType.ATTACK;
           applyPowers();
       }
   }

     public void onMoveToDiscard() {
         resetAttributes();
         this.type = CardType.ATTACK;
         applyPowers();
     }

     @Override
     public void triggerOnExhaust() {
       addToBot((AbstractGameAction)new ReanimateAction(3));
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       addToBot((AbstractGameAction)new SFXAction("DeadMetalStar_Acc"));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 3));
     } else {
         AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
         addToBot((AbstractGameAction)new SFXAction("DeadMetalStar"));
         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
         addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
     } 
   }
 
   
   public AbstractCard makeSameInstanceOf() {
     AbstractCard card = null;
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       card = (new DeadMetalStar_Accelerate()).makeStatEquivalentCopy();
       card.uuid = (new DeadMetalStar_Accelerate()).uuid;
     } else {
       card = makeStatEquivalentCopy();
       card.uuid = this.uuid;
     } 
     return card;
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DeadMetalStar();
   }
 }

