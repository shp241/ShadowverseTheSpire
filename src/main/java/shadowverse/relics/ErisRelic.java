 package shadowverse.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.ArtifactPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;


 public class ErisRelic
   extends CustomRelic
 {
   public static final String ID = "shadowverse:ErisRelic";
   public static final String IMG = "img/relics/ErisRelic.png";
   public static final String OUTLINE_IMG = "img/relics/outline/ErisRelic_Outline.png";

   public ErisRelic() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.UNCOMMON, LandingSound.SOLID);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   public AbstractRelic makeCopy() {
     return (AbstractRelic)new ErisRelic();
   }
 }

