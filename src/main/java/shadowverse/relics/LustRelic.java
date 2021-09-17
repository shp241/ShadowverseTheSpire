 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.helpers.PowerTip;
 import com.megacrit.cardcrawl.helpers.TipHelper;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.powers.DrainPower;


 public class LustRelic
   extends CustomRelic
 {
   public static final String ID = "shadowverse:LustRelic";
   public static final String IMG = "img/relics/LustRelic.png";
   public static final String OUTLINE_IMG = "img/relics/outline/LustRelic.png";
     public static final PowerStrings powStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:DrainPower");

   public LustRelic() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.UNCOMMON, LandingSound.MAGICAL);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   public void atBattleStart() {
       flash();
       addToBot((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player,(AbstractCreature)AbstractDungeon.player,(AbstractPower)new DrainPower((AbstractCreature)AbstractDungeon.player)));
   }

     @Override
     protected void initializeTips(){
         super.initializeTips();
         this.tips.add(new PowerTip(
                 TipHelper.capitalize(powStrings.NAME), powStrings.DESCRIPTIONS[0]));
     }

   public AbstractRelic makeCopy() {
     return (AbstractRelic)new LustRelic();
   }
 }


