 package shadowverse.relics;
 
 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.action.ReanimateAction;


 public class InfernalCrown
   extends CustomRelic
 {
   public static final String ID = "shadowverse:InfernalCrown";
   public static final String IMG = "img/relics/InfernalCrown.png";
   public static final String OUTLINE_IMG = "img/relics/outline/InfernalCrown_Outline.png";



   public InfernalCrown() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.HEAVY);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public void atBattleStart() {
     this.counter = 0;
     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
   }
   
   public void atTurnStart() {
     if (!this.grayscale) {
       this.counter++;
       switch (this.counter){
         case 2:
           addToBot((AbstractGameAction)new ReanimateAction(3));
           break;
         case 3:
           addToBot((AbstractGameAction)new ReanimateAction(4));
           break;
         case 4:
           addToBot((AbstractGameAction)new ReanimateAction(5));
           break;
         default:break;
       }
     } 
     if (this.counter == 5) {
       flash();
       this.counter = -1;
       this.grayscale = true;
     } 
   }
 
   
   public void onVictory() {
     this.counter = -1;
     this.grayscale = false;
   }
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new InfernalCrown();
   }
 }
