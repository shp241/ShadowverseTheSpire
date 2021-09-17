 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;


 public class KMR2
   extends CustomRelic
 {
   public static final String ID = "shadowverse:KMR2";
   public static final String IMG = "img/relics/KMR.png";
   public static final String OUTLINE_IMG = "img/relics/outline/KMR_Outline.png";

   public KMR2() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public void atBattleStart() {
     flash();
     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
     for(AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
         if (!mo.isDeadOrEscaped()){
             addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)mo, (AbstractPower)new StrengthPower((AbstractCreature)mo, 1), 1));
         }
     }
   }
 
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new KMR2();
   }
 }

