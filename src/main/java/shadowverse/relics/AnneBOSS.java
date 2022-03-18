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
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.patch.CharacterSelectScreenPatches;


 public class AnneBOSS
   extends CustomRelic
 {
   public static final String ID = "shadowverse:AnneBOSS";
   public static final String IMG = "img/relics/AnneBOSS.png";
   public static final String OUTLINE_IMG = "img/relics/outline/KyoukaBOSS_Outline.png";


   public AnneBOSS() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.MAGICAL);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     @Override
     public void onPlayCard(AbstractCard c, AbstractMonster m) {
         if (c.type== AbstractCard.CardType.SKILL && !c.hasTag(AbstractShadowversePlayer.Enums.MYSTERIA)){
             if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
                 ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
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
         return AbstractDungeon.player.hasRelic(Offensive.ID)
                 && (CharacterSelectScreenPatches.characters[0]).reskinCount == 2;
     }


     public AbstractRelic makeCopy() {
     return (AbstractRelic)new AnneBOSS();
   }
 }

