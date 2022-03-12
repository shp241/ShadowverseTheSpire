 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
 import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.status.Dazed;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.stances.AbstractStance;
 import shadowverse.patch.CharacterSelectScreenPatches;
 import shadowverse.stance.Resonance;
 import shadowverse.stance.Vengeance;


 public class NemesisBOSS
   extends CustomRelic
 {
   public static final String ID = "shadowverse:NemesisBOSS";
   public static final String IMG = "img/relics/nemesisBOSS.png";
   public static final String OUTLINE_IMG = "img/relics/outline/nemesisBOSS_Outline.png";

   public NemesisBOSS() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.FLAT);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   public void onChangeStance(AbstractStance prevStance, AbstractStance newStance) {
       if (newStance.ID.equals(Resonance.STANCE_ID)){
           addToBot((AbstractGameAction)new GainEnergyAction(1));
       }else if (prevStance.ID.equals(Resonance.STANCE_ID)){
           addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Dazed(),1));
       }
   }
     @Override
     public void obtain() {
         if (AbstractDungeon.player.hasRelic(Offensive5.ID)) {
             instantObtain(AbstractDungeon.player, 0, false);
         } else {
             super.obtain();
         }
     }
     @Override
     public boolean canSpawn(){
         return AbstractDungeon.player.hasRelic(Offensive5.ID)
                 &&(CharacterSelectScreenPatches.characters[6]).reskinCount == 0;
     }

   public AbstractRelic makeCopy() {
     return (AbstractRelic)new NemesisBOSS();
   }
 }


