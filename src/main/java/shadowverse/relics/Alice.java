 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;


 public class Alice
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Alice";
   public static final String IMG = "img/relics/Alice.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Alice_Outline.png";

   public Alice() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.UNCOMMON, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Alice();
   }
 }


