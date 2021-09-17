 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.TalkAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;

 
 
 
 public class BlackPinya
   extends CustomRelic
 {
   public static final String ID = "shadowverse:BlackPinya";
   public static final String IMG = "img/relics/BlackPinya.png";
   public static final String OUTLINE_IMG = "img/relics/outline/BlackPinya_Outline.png";
   
   public BlackPinya() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public void atBattleStart() {
     flash();
     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
       addToBot((AbstractGameAction)new SFXAction("BlackPinya"));
       addToBot((AbstractGameAction)new TalkAction(true, this.DESCRIPTIONS[1], 1.0F, 2.0F));
   }
 
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new BlackPinya();
   }
 }

