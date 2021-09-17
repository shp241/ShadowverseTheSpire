 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.powers.EpitaphPower;
 import shadowverse.stance.Vengeance;


 public class Offensive4
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Offensive4";
   public static final String IMG = "img/relics/Offensive.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Offensive_Outline.png";

   public Offensive4() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.STARTER, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   public void onVictory() {
       flash();
       if (AbstractDungeon.player.stance.ID.equals(Vengeance.STANCE_ID)||AbstractDungeon.player.hasPower(EpitaphPower.POWER_ID)){
           AbstractDungeon.player.heal(7);
       }else {
           AbstractDungeon.player.heal(4);
       }
   }

   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Offensive4();
   }
 }


