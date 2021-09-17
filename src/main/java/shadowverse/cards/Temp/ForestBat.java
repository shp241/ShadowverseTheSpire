 package shadowverse.cards.Temp;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import shadowverse.characters.Vampire;


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
     } else{
       this.baseDamage = 4;
     }
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(2);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(abstractMonster.hb.cX, abstractMonster.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.2F));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ForestBat();
   }
 }

