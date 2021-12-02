 package shadowverse.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.ArtifactPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.Shadowverse;
 import shadowverse.action.PlaceAmulet;


 public class HeresyAvatar
   extends CustomRelic
 {
   public static final String ID = "shadowverse:HeresyAvatar";
   public static final String IMG = "img/relics/HeresyAvatar.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Mysteria_Outline.png";

   public HeresyAvatar() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.MAGICAL);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     @Override
     public void onCardDraw(AbstractCard drawnCard) {
         if (drawnCard.type == AbstractCard.CardType.CURSE){
             flash();
             addToBot((AbstractGameAction) new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,(AbstractPower)new ArtifactPower(AbstractDungeon.player,1),1));
             if (drawnCard.costForTurn>0){
                 drawnCard.setCostForTurn(0);
             }
         }
     }

     public AbstractRelic makeCopy() {
     return (AbstractRelic)new HeresyAvatar();
   }
 }

