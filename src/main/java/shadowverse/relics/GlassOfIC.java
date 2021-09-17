 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;


 public class GlassOfIC
   extends CustomRelic
 {
   public static final String ID = "shadowverse:GlassOfIC";
   public static final String IMG = "img/relics/GlassOfIC.png";
   public static final String OUTLINE_IMG = "img/relics/outline/GlassOfIC_Outline.png";

   public GlassOfIC() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.UNCOMMON, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     public void wasHPLost(int damageAmount) {
         if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                 damageAmount > 0) {
             flash();
             addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)AbstractDungeon.player, DamageInfo.createDamageMatrix(damageAmount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.POISON, true));
         }
     }
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new GlassOfIC();
   }
 }

