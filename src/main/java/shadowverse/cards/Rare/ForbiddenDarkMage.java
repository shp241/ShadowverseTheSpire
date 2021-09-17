 package shadowverse.cards.Rare;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
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
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

 import shadowverse.Shadowverse;
import shadowverse.cards.Temp.ForbiddenDarkMage_Accelerate;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 import shadowverse.powers.DarkMagePower;
 import shadowverse.powers.EarthEssence;
 
 
 
 
 public class ForbiddenDarkMage
   extends CustomCard
 {
   public static final String ID = "shadowverse:ForbiddenDarkMage";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ForbiddenDarkMage");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ForbiddenDarkMage.png";
     public boolean doubleCheck = false;
   
   public ForbiddenDarkMage() {
     super("shadowverse:ForbiddenDarkMage", NAME, "img/cards/ForbiddenDarkMage.png", 3, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 7;
     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
     this.tags.add(AbstractShadowversePlayer.Enums.CRYSTALLIZE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

   
   public void applyPowers() {
       AbstractShadowversePlayer w = (AbstractShadowversePlayer)AbstractDungeon.player;
     int realBaseDamage = this.baseDamage;
     this.baseDamage += w.earthCount * this.baseDamage;
     super.applyPowers();
     this.baseDamage = realBaseDamage;
     this.isDamageModified = (this.damage != this.baseDamage);
   }
   
   public void calculateCardDamage(AbstractMonster mo) {
       AbstractShadowversePlayer w = (AbstractShadowversePlayer)AbstractDungeon.player;
     int realBaseDamage = this.baseDamage;
     this.baseDamage += w.earthCount * this.baseDamage;
     super.calculateCardDamage(mo);
     this.baseDamage = realBaseDamage;
     this.isDamageModified = (this.damage != this.baseDamage);
   }
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
       if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
           doubleCheck = true;
           if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
               setCostForTurn(0);
               this.type = CardType.POWER;
               applyPowers();
           }
       }else {
           if (doubleCheck) {
               doubleCheck = false;
           }else {
               if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                   setCostForTurn(0);
                   this.type = CardType.POWER;
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
       this.type = CardType.POWER;
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
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.POWER) {
       addToBot((AbstractGameAction)new SFXAction("DarkMagePower"));
       boolean powerExists = false;
       AbstractPower earthEssence = null;
       for (AbstractPower pow : abstractPlayer.powers) {
         if (pow.ID.equals("shadowverse:EarthEssence")) {
           earthEssence = pow;
           powerExists = true;
           break;
         } 
       } 
       if (powerExists) {
         ((AbstractShadowversePlayer)abstractPlayer).earthCount += earthEssence.amount;
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, -earthEssence.amount), -earthEssence.amount));
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new DarkMagePower((AbstractCreature)abstractPlayer, earthEssence.amount), earthEssence.amount));
       } 
     } else {
       addToBot((AbstractGameAction)new SFXAction("ForbiddenDarkMage"));
       addToBot((AbstractGameAction)new WaitAction(0.8F));
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
       calculateCardDamage(abstractMonster);
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
     } 
   }

     @Override
     public void atTurnStart() {
         if (AbstractDungeon.player.hand.group.contains(this)){
             if (EnergyPanel.getCurrentEnergy()<3) {
                 setCostForTurn(0);
                 this.type = CardType.POWER;
             } else {
                 resetAttributes();
                 this.type = CardType.ATTACK;
             }
             applyPowers();
         }
     }
   
   public AbstractCard makeSameInstanceOf() {
     AbstractCard card = null;
     if (Shadowverse.Accelerate((AbstractCard)this) && this.type == CardType.POWER) {
       card = (new ForbiddenDarkMage_Accelerate()).makeStatEquivalentCopy();
       card.uuid = (new ForbiddenDarkMage_Accelerate()).uuid;
     } else {
       card = makeStatEquivalentCopy();
       card.uuid = this.uuid;
     } 
     return card;
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ForbiddenDarkMage();
   }
 }



