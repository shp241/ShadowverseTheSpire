 package shadowverse.cards.Temp;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import shadowverse.characters.Vampire;
import shadowverse.powers.EpitaphPower;
import shadowverse.powers.NightVampirePower;
import shadowverse.powers.WrathPower;
import shadowverse.stance.Vengeance;


 public class ForestBat
   extends CustomCard {
   public static final String ID = "shadowverse:ForestBat";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ForestBat");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ForestBat.png";

   public ForestBat() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.ENEMY);
     if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("shadowverse:OldBloodKingPower")){
       this.baseDamage = 4 + (AbstractDungeon.player.getPower("shadowverse:OldBloodKingPower")).amount;
     } else if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower(NightVampirePower.POWER_ID)){
       this.baseDamage = 8;
     }else {
       this.baseDamage = 4;
     }
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&AbstractDungeon.player.hasPower(NightVampirePower.POWER_ID)){
         upgradeDamage(4);
       }else {
         upgradeDamage(2);
       }
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(abstractMonster.hb.cX, abstractMonster.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.2F));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
     if (abstractPlayer.hasPower(NightVampirePower.POWER_ID)){
       addToBot((AbstractGameAction)new SFXAction("NightVampirePower"));
       if (abstractPlayer.hasPower(EpitaphPower.POWER_ID)||abstractPlayer.hasPower(WrathPower.POWER_ID)){
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, (AbstractPower)new PoisonPower((AbstractCreature)abstractMonster, (AbstractCreature)abstractPlayer, 2)));
         addToBot((AbstractGameAction)new HealAction(abstractPlayer,abstractPlayer,2));
       }else {
         addToBot((AbstractGameAction)new LoseHPAction(abstractPlayer,abstractPlayer,1));
       }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ForestBat();
   }
 }

