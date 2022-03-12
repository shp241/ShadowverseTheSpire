 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.badlogic.gdx.graphics.Texture;
 import com.badlogic.gdx.math.MathUtils;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.actions.utility.UseCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.powers.DexterityPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import shadowverse.patch.CharacterSelectScreenPatches;


 public class KokkoroBOSS
   extends CustomRelic
 {
   public static final String ID = "shadowverse:KokkoroBOSS";
   public static final String IMG = "img/relics/KokkoroBOSS.png";
   public static final String OUTLINE_IMG = "img/relics/outline/ArisaBOSS_Outline.png";
   private boolean triggeredThisTurn = false;

   public KokkoroBOSS() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     public void atTurnStart() {
         this.triggeredThisTurn = false;
         this.counter = 0;
         this.outlineImg = new Texture(OUTLINE_IMG);
     }

     @Override
     public void onUseCard(AbstractCard card, UseCardAction action) {
         this.counter = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
         if (this.counter%6 == 0){
             if (!this.triggeredThisTurn) {
                 this.triggeredThisTurn = true;
                 flash();
                 addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature) AbstractDungeon.player, this));
                 addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new StrengthPower(AbstractDungeon.player,1)));
                 addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new DexterityPower(AbstractDungeon.player,1)));
             }
         }
     }

     public int onPlayerHeal(int healAmount) {
         if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
             flash();
             return MathUtils.round(healAmount * 1.5F);
         }
         return healAmount;
     }

     @Override
     public void obtain() {
         if (AbstractDungeon.player.hasRelic(Offensive2.ID)) {
             instantObtain(AbstractDungeon.player, 0, false);
         } else {
             super.obtain();
         }
     }

     public void onVictory() {
         this.counter = -1;
     }

     @Override
     public boolean canSpawn(){
         return AbstractDungeon.player.hasRelic(Offensive2.ID)
                 &&(CharacterSelectScreenPatches.characters[1]).reskinCount == 1;
     }

 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new KokkoroBOSS();
   }
 }


