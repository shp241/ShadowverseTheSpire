 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.helpers.PowerTip;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.stances.AbstractStance;
 import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
 import shadowverse.powers.EpitaphPower;
 import shadowverse.stance.Vengeance;


 public class Offensive5
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Offensive5";
   public static final String IMG = "img/relics/Offensive.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Offensive_Outline.png";

   public Offensive5() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.STARTER, LandingSound.CLINK);
   }

   public void onEquip() {
         this.counter = 0;
     }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     public void atBattleStart() {
         flash();
         if (AbstractDungeon.player.masterDeck.size()%2==0){
             addToBot((AbstractGameAction)new DrawCardAction(1));
         }else {
             this.counter++;
         }
     }

     public void onVictory() {
       if (this.counter==10){
           AbstractDungeon.player.heal(8);
           AbstractDungeon.effectList.add(new RainingGoldEffect(8));
           AbstractDungeon.player.gainGold(8);
           this.counter=0;
       }
     }

   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Offensive5();
   }
 }


