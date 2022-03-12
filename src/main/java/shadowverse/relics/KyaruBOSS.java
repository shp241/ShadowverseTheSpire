 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.BurstPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.patch.CharacterSelectScreenPatches;


 public class KyaruBOSS
   extends CustomRelic
 {
   public static final String ID = "shadowverse:KyaruBOSS";
   public static final String IMG = "img/relics/KyaruBOSS.png";
   public static final String OUTLINE_IMG = "img/relics/outline/KyoukaBOSS_Outline.png";
   private static final float MODIFIER_AMT = 0.18F;


   public KyaruBOSS() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.MAGICAL);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     public void atBattleStart() {
             flash();
             for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                     m.decreaseMaxHealth((int)(m.maxHealth *  MODIFIER_AMT));  ;
                     m.healthBarUpdatedEvent();
             }
             addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
     }

     @Override
     public void obtain() {
         if (AbstractDungeon.player.hasRelic(Offensive.ID)) {
             instantObtain(AbstractDungeon.player, 0, false);
         } else {
             super.obtain();
         }
     }

     @Override
     public boolean canSpawn(){
         return AbstractDungeon.player.hasRelic(Offensive.ID)
                 && (CharacterSelectScreenPatches.characters[0]).reskinCount == 1;
     }


     public AbstractRelic makeCopy() {
     return (AbstractRelic)new KyaruBOSS();
   }
 }

