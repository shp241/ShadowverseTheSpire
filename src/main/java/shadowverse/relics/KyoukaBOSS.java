 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Basic.Insight;


 public class KyoukaBOSS
   extends CustomRelic
 {
   public static final String ID = "shadowverse:KyoukaBOSS";
   public static final String IMG = "img/relics/KyoukaBOSS.png";
   public static final String OUTLINE_IMG = "img/relics/outline/KyoukaBOSS_Outline.png";


   public KyoukaBOSS() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.MAGICAL);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   @Override
   public void atTurnStart() {
     flash();
       AbstractCard c = (AbstractCard)new Insight();
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c));
   }

     @Override
     public void obtain() {
         if (AbstractDungeon.player.hasRelic(Offensive.ID)) {
             instantObtain(AbstractDungeon.player, 0, false);
         } else {
             super.obtain();
         }
     }

     @Override
     public boolean canSpawn(){
         return AbstractDungeon.player.hasRelic(Offensive.ID);
     }


     public AbstractRelic makeCopy() {
     return (AbstractRelic)new KyoukaBOSS();
   }
 }

