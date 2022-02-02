 package shadowverse.cards.Uncommon;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.Purity;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.action.NecromanceAction;
import shadowverse.characters.Necromancer;


 public class ResentfulScreaming extends CustomCard {
   public static final String ID = "shadowverse:ResentfulScreaming";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ResentfulScreaming");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ResentfulScreaming.png";

   public ResentfulScreaming() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.exhaust = false;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     addToBot((AbstractGameAction)new SFXAction("ResentfulScreaming"));
     if (abstractMonster.hasPower("Artifact")) {
       addToBot((AbstractGameAction) new RemoveSpecificPowerAction(abstractMonster, abstractPlayer, "Artifact"));
     } else {
       for (AbstractPower pow : abstractMonster.powers) {
         if (pow.type == AbstractPower.PowerType.BUFF && pow.ID != "Invincible" && pow.ID != "Mode Shift" && pow.ID != "Split" && pow.ID != "Unawakened" && pow.ID != "Life Link" && pow.ID != "Fading" && pow.ID != "Stasis" && pow.ID != "Minion" && pow.ID != "Shifting" && pow.ID != "shadowverse:chushouHealPower" && !(pow instanceof StrengthPower) && !(pow instanceof DexterityPower)) {
           addToBot((AbstractGameAction) new RemoveSpecificPowerAction(pow.owner, abstractPlayer, pow.ID));
           break;
         }
       }
     }
     this.addToBot(new ExhaustAction(1, false, true, true));;
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ResentfulScreaming();
   }
 }

