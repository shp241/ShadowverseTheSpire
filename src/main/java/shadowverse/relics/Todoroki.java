 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

 
 
 
 public class Todoroki
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Todoroki";
   public static final String IMG = "img/relics/Todoroki.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Todoroki_Outline.png";
   
   public Todoroki() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.RARE, LandingSound.HEAVY);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public void atBattleStart() {
       flash();
       for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
           addToBot((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature) mo, this));
           if (mo.hasPower("Artifact")) {
               addToBot((AbstractGameAction) new RemoveSpecificPowerAction(mo, AbstractDungeon.player, "Artifact"));
           } else {
               for (AbstractPower pow : mo.powers) {
                   if (pow.type == AbstractPower.PowerType.BUFF && pow.ID != "Invincible" && pow.ID != "Mode Shift" && pow.ID != "Split" && pow.ID != "Unawakened" && pow.ID != "Life Link" && pow.ID != "Fading" && pow.ID != "Stasis" && pow.ID != "Minion" && pow.ID != "Shifting" && pow.ID != "shadowverse:chushouHealPower") {
                       addToBot((AbstractGameAction) new RemoveSpecificPowerAction(pow.owner, AbstractDungeon.player, pow.ID));
                       break;
                   }
               }
           }
       }
       this.grayscale = true;
   }
   
   public void justEnteredRoom(AbstractRoom room) {
     this.grayscale = false;
   }

   @Override
   public void onVictory(){
       this.grayscale = false;
   }
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Todoroki();
   }
 }

