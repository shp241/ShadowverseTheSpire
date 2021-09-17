 package shadowverse.relics;
 


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import shadowverse.reward.NeutralReward;


 public class NeutralBook
   extends CustomRelic
 {
   public static final String ID = "shadowverse:NeutralBook";
   public static final String IMG = "img/relics/NeutralBook.png";
   public static final String OUTLINE_IMG = "img/relics/outline/NeutralBook_Outline.png";

   
   public NeutralBook() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.FLAT);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     public void onEquip() {
         this.counter = 3;
     }
 
   
   public void onVictory() {
       if (!this.grayscale){
           if (this.counter>0){
               flash();
               AbstractDungeon.getCurrRoom().addCardReward((RewardItem)new NeutralReward());
           }
           this.counter--;
           if (this.counter<=0)
               this.grayscale = true;
       }else if(this.grayscale && (AbstractDungeon.floorNum==16||AbstractDungeon.floorNum==33||AbstractDungeon.floorNum==50)){
           this.grayscale = false;
           this.counter=3;
       }
   }
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new NeutralBook();
   }
 }

