 package shadowverse.relics;
 
 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.BurstPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 
 
 
 public class Offensive
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Offensive";
   public static final String IMG = "img/relics/Offensive.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Offensive_Outline.png";
   
   public Offensive() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.STARTER, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public void atBattleStart() {
     flash();
     addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, (AbstractRelic)this));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new BurstPower((AbstractCreature)AbstractDungeon.player, 1), 1));
   }
 
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Offensive();
   }
 }

