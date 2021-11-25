 package shadowverse.cards.Rare;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;


 public class Charaton extends CustomCard {
   public static final String ID = "shadowverse:Charaton";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Charaton");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Charaton.png";

   public Charaton() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 5;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
     } 
   }

   public void applyPowers() {
     super.applyPowers();
     AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
     int amt = w.amuletCount;
     this.rawDescription = cardStrings.DESCRIPTION;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0]+amt;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1]+amt;
     this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[2];
     this.initializeDescription();
   }


   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("Charaton"));
     AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
     addToBot((AbstractGameAction)new GainEnergyAction(w.amuletCount));
     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BlizzardEffect(w.amuletCount, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.5F));
     for (int i =0;i<w.amuletCount;i++){
       addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Charaton();
   }
 }

