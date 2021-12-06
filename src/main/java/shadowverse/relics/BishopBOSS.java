 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
 import com.megacrit.cardcrawl.actions.utility.UseCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.BerserkPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;


 public class BishopBOSS
   extends CustomRelic
 {
   public static final String ID = "shadowverse:BishopBOSS";
   public static final String IMG = "img/relics/BishopBOSS.png";
   public static final String OUTLINE_IMG = "img/relics/outline/ArisaBOSS_Outline.png";
   private boolean isActive = false;

   public BishopBOSS() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     @Override
     public void onEquip() {
         AbstractDungeon.player.masterMaxOrbs=6;
     }

     public void atBattleStart() {
         this.isActive = false;
         addToBot(new AbstractGameAction() {
             public void update() {
                 if (!BishopBOSS.this.isActive && AbstractDungeon.player.isBloodied) {
                     BishopBOSS.this.flash();
                     BishopBOSS.this.pulse = true;
                     AbstractDungeon.player.addPower((AbstractPower)new BerserkPower((AbstractCreature)AbstractDungeon.player, 1));
                     addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, BishopBOSS.this));
                     BishopBOSS.this.isActive = true;
                     AbstractDungeon.onModifyPower();
                 }
                 this.isDone = true;
             }
         });
     }

     public void onBloodied() {
         flash();
         this.pulse = true;
         if (!this.isActive && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
             AbstractPlayer p = AbstractDungeon.player;
             addToTop((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new BerserkPower((AbstractCreature)p, 1), 1));
             addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
             this.isActive = true;
             AbstractDungeon.player.hand.applyPowers();
         }
     }

     public void onNotBloodied() {
         if (this.isActive && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
             AbstractPlayer p = AbstractDungeon.player;
             addToTop((AbstractGameAction) new RemoveSpecificPowerAction(p,p,BerserkPower.POWER_ID));
         }
         stopPulse();
         this.isActive = false;
         AbstractDungeon.player.hand.applyPowers();
     }

     public void onVictory() {
         this.pulse = false;
         this.isActive = false;
     }
     @Override
     public void obtain() {
         if (AbstractDungeon.player.hasRelic(Offensive7.ID)) {
             instantObtain(AbstractDungeon.player, 0, false);
         } else {
             super.obtain();
         }
     }

     @Override
     public boolean canSpawn(){
         return AbstractDungeon.player.hasRelic(Offensive7.ID);
     }
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new BishopBOSS();
   }
 }


