 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.powers.FairyCirclePower;


 public class Mimori
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Mimori";
   public static final String IMG = "img/relics/Mimori.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Mimori_Outline.png";

   public Mimori() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public void atBattleStart() {
     flash();
     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player,(AbstractCreature)AbstractDungeon.player,(AbstractPower)new FairyCirclePower((AbstractCreature)AbstractDungeon.player,1),1));
   }
 
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Mimori();
   }
 }

