 package shadowverse.relics;
 
 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
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


     @Override
     public void onPlayCard(AbstractCard c, AbstractMonster m) {
       if (c.type== AbstractCard.CardType.SKILL&&!this.grayscale){
           flash();
           addToBot(new GainEnergyAction(1));
           this.grayscale = true;
       }
     }

     @Override
     public void onVictory(){
         this.grayscale = false;
     }

     public AbstractRelic makeCopy() {
     return (AbstractRelic)new Offensive();
   }
 }

