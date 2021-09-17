 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;


 public class ArisaBOSS
   extends CustomRelic
 {
   public static final String ID = "shadowverse:ArisaBOSS";
   public static final String IMG = "img/relics/ArisaBOSS.png";
   public static final String OUTLINE_IMG = "img/relics/outline/ArisaBOSS_Outline.png";
   private boolean triggeredThisTurn = false;

   public ArisaBOSS() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     public void atTurnStart() {
         this.triggeredThisTurn = false;
         this.counter = 0;
         this.outlineImg = new Texture(OUTLINE_IMG);
     }

     @Override
     public void onUseCard(AbstractCard card, UseCardAction action) {
         this.counter = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
         if (this.counter%6 == 0){
             if (!this.triggeredThisTurn) {
                 this.triggeredThisTurn = true;
                 flash();
                 addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
                 addToBot((AbstractGameAction)new GainEnergyAction(1));
             }
         }
     }

     @Override
     public void obtain() {
         if (AbstractDungeon.player.hasRelic(Offensive2.ID)) {
             instantObtain(AbstractDungeon.player, 0, false);
         } else {
             super.obtain();
         }
     }

     public void onVictory() {
         this.counter = -1;
     }

     @Override
     public boolean canSpawn(){
         return AbstractDungeon.player.hasRelic(Offensive2.ID);
     }

 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new ArisaBOSS();
   }
 }


