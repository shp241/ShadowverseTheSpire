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
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import shadowverse.powers.EarthEssence;
 
 
 
 public class EarthEssenceRelic
   extends CustomRelic
 {
   public static final String ID = "shadowverse:EarthEssenceRelic";
   public static final String IMG = "img/relics/EarthEssenceRelic.png";
   public static final String OUTLINE_IMG = "img/relics/outline/EarthEssenceRelic_Outline.png";
   
   public EarthEssenceRelic() {
     super("shadowverse:EarthEssenceRelic", ImageMaster.loadImage("img/relics/EarthEssenceRelic.png"), RelicTier.COMMON, LandingSound.FLAT);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public void atBattleStart() {
     flash();
     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new EarthEssence((AbstractCreature)AbstractDungeon.player, 1), 1));
     this.grayscale = true;
   }
   
   public void justEnteredRoom(AbstractRoom room) {
     this.grayscale = false;
   }
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new EarthEssenceRelic();
   }
 }

