 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.powers.AvaricePower;
 import shadowverse.powers.EpitaphPower;
 import shadowverse.powers.WrathPower;
 import shadowverse.stance.Vengeance;


 public class Pendant
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Pendant";
   public static final String IMG = "img/relics/Pendant.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Pendant_Outline.png";
   private AbstractPlayer p = AbstractDungeon.player;

   public Pendant() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.FLAT);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     public void atTurnStartPostDraw() {
       if (p.stance.ID.equals(Vengeance.STANCE_ID)||p.hasPower(EpitaphPower.POWER_ID)){
           addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new StrengthPower(p,1),1));
       }
       if (p.hasPower(EpitaphPower.POWER_ID)||p.hasPower(WrathPower.POWER_ID)){
           addToBot((AbstractGameAction)new GainBlockAction(p,7));
       }
       if (p.hasPower(EpitaphPower.POWER_ID)||p.hasPower(AvaricePower.POWER_ID)){
           addToBot((AbstractGameAction)new DrawCardAction(1));
       }
     }

   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Pendant();
   }
 }


