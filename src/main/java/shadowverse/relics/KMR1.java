 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;


 public class KMR1
   extends CustomRelic
 {
   public static final String ID = "shadowverse:KMR1";
   public static final String IMG = "img/relics/KMR.png";
   public static final String OUTLINE_IMG = "img/relics/outline/KMR_Outline.png";

   public KMR1() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public void atBattleStart() {
     flash();
     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
     for(AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
         if (!mo.isDeadOrEscaped()){
             addToBot((AbstractGameAction)new GainBlockAction(mo,8));
         }
     }
   }
 
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new KMR1();
   }
 }

