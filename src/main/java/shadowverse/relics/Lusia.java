 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.stances.AbstractStance;
 import shadowverse.stance.Vengeance;


 public class Lusia
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Lusia";
   public static final String IMG = "img/relics/Lusia.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Lusia_Outline.png";

   public Lusia() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.UNCOMMON, LandingSound.FLAT);
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
     return (AbstractRelic)new Lusia();
   }
 }


