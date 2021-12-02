 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.TalkAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.actions.utility.UseCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.action.ReduceAllCountDownAction;


 public class Savior
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Savior";
   public static final String IMG = "img/relics/Savior.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Savior_Outline.png";
   private  boolean attacked = false;

   public Savior() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.COMMON, LandingSound.MAGICAL);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     @Override
     public void atTurnStart() {
       if (!attacked){
           flash();
           addToBot((AbstractGameAction) new GainBlockAction(AbstractDungeon.player,4));
       }
       attacked = false;
     }

     @Override
     public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
       if (targetCard.type== AbstractCard.CardType.ATTACK)
           attacked=true;
     }

     public AbstractRelic makeCopy() {
     return (AbstractRelic)new Savior();
   }
 }

