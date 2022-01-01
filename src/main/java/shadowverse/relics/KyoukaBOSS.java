 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.BurstPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Basic.Insight;


 public class KyoukaBOSS
   extends CustomRelic
 {
   public static final String ID = "shadowverse:KyoukaBOSS";
   public static final String IMG = "img/relics/KyoukaBOSS.png";
   public static final String OUTLINE_IMG = "img/relics/outline/KyoukaBOSS_Outline.png";


   public KyoukaBOSS() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.MAGICAL);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     @Override
     public void onPlayCard(AbstractCard c, AbstractMonster m) {
         if (c.type== AbstractCard.CardType.SKILL){
             this.counter++;
             if (this.counter == 7) {
                 this.counter = 0;
                 flash();
                 this.pulse = false;
             } else if (this.counter == 6) {
                 beginPulse();
                 this.pulse = true;
                 AbstractDungeon.player.hand.refreshHandLayout();
                 addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
                 addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (AbstractPower)new BurstPower((AbstractCreature)AbstractDungeon.player, 1), 1));
             }
         }
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
         return AbstractDungeon.player.hasRelic(Offensive.ID);
     }


     public AbstractRelic makeCopy() {
     return (AbstractRelic)new KyoukaBOSS();
   }
 }

