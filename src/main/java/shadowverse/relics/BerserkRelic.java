 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.HealAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.stances.AbstractStance;
 import shadowverse.powers.WrathPower;
 import shadowverse.stance.Vengeance;


 public class BerserkRelic
   extends CustomRelic implements OnReceivePowerPower
 {
   public static final String ID = "shadowverse:BerserkRelic";
   public static final String IMG = "img/relics/BerserkRelic.png";
   public static final String OUTLINE_IMG = "img/relics/outline/BerserkRelic_Outline.png";

   public BerserkRelic() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.FLAT);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
       if (newStance.ID.equals(Vengeance.STANCE_ID)){
           addToBot((AbstractGameAction)new GainEnergyAction(1));
       }
   }


   public AbstractRelic makeCopy() {
     return (AbstractRelic)new BerserkRelic();
   }

     @Override
     public boolean onReceivePower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
       if (abstractPower.ID.equals(WrathPower.POWER_ID)){
           addToBot((AbstractGameAction)new HealAction(AbstractDungeon.player,AbstractDungeon.player,7));
       }
         return true;
     }
 }


