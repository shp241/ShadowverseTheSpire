 package shadowverse.cards.Temp;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.SuicideAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import shadowverse.characters.Witchcraft;
import shadowverse.orbs.Minion;


 public class ShadowBahmut
   extends CustomCard
 {
   public static final String ID = "shadowverse:ShadowBahmut";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ShadowBahmut");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ShadowBahmut.png";

   public ShadowBahmut() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.ENEMY);
     this.baseDamage = 80;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeDamage(20);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       for (AbstractPower pow : abstractMonster.powers){
             if (pow.type != AbstractPower.PowerType.DEBUFF && pow.ID!="Invincible" &&pow.ID!="Mode Shift"&&pow.ID!="Split"&&pow.ID!="Unawakened"&&pow.ID!="Life Link"&&pow.ID!="Fading"&&pow.ID!="Stasis"&&pow.ID!="Minion"&&pow.ID!="Shifting"
                 && pow.ID != StrengthPower.POWER_ID && pow.ID != DexterityPower.POWER_ID){
                 addToBot((AbstractGameAction) new RemoveSpecificPowerAction(pow.owner,abstractPlayer,pow.ID));
             }
        }
       if (abstractMonster != null)
           addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new WeightyImpactEffect(abstractMonster.hb.cX, abstractMonster.hb.cY)));
       addToBot((AbstractGameAction)new WaitAction(0.8F));
       abstractMonster.currentHealth-=this.damage;
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
           if (!mo.isDeadOrEscaped() && mo.hasPower(MinionPower.POWER_ID)) {
               addToBot((AbstractGameAction) new SuicideAction(mo));
           }
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ShadowBahmut();
   }
 }

