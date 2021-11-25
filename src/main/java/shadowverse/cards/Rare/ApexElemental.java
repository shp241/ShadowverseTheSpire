 package shadowverse.cards.Rare;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.orbs.AbstractOrb;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

 import shadowverse.Shadowverse;
import shadowverse.cards.Temp.ApexElemental_Accelerate;
 import shadowverse.cards.Temp.NaterranGreatTree;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 import shadowverse.orbs.AmuletOrb;

 public class ApexElemental
   extends CustomCard {
   public static final String ID = "shadowverse:ApexElemental";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ApexElemental");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ApexElemental.png";
   public boolean doubleCheck = false;

   public ApexElemental() {
     super("shadowverse:ApexElemental", NAME, "img/cards/ApexElemental.png", 3, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 15;
     this.cardsToPreview = (AbstractCard)new NaterranGreatTree();
     this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
     this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(5);
     } 
   }


     @Override
     public void atTurnStart() {
         if (AbstractDungeon.player.hand.group.contains(this)){
             if (EnergyPanel.getCurrentEnergy()<3) {
                 setCostForTurn(0);
                 this.type = CardType.SKILL;
             } else {
                 this.type = CardType.ATTACK;
             }
             applyPowers();
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
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     boolean powerExists = false;
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:NaterranTree")) {
         powerExists = true;
         break;
       } 
     } 
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       if (powerExists) {
           for (AbstractOrb o:abstractPlayer.orbs){
               if (o instanceof AmuletOrb){
                   if (((AmuletOrb) o).amulet instanceof NaterranGreatTree){
                       abstractPlayer.orbs.remove(o);
                       AbstractDungeon.player.orbs.add(0, o);
                       AbstractDungeon.player.evokeOrb();
                   }
               }
           }
         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, 9, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
         addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, "shadowverse:NaterranTree"));
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
       } else {
         addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, 6, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
       }
     
     } else if (powerExists) {
       addToBot((AbstractGameAction)new RemoveSpecificPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, "shadowverse:NaterranTree"));
       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new CleaveEffect(), 0.1F));
       addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage * 2, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
     } else {
       addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new CleaveEffect(), 0.1F));
       addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)abstractPlayer, DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
     } 
   }
 
 
   
   public AbstractCard makeSameInstanceOf() {
     AbstractCard card = null;
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.SKILL) {
       card = (new ApexElemental_Accelerate()).makeStatEquivalentCopy();
       card.uuid = (new ApexElemental_Accelerate()).uuid;
     } else {
       card = makeStatEquivalentCopy();
       card.uuid = this.uuid;
     } 
     return card;
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ApexElemental();
   }
 }

