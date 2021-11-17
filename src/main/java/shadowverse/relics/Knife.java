 package shadowverse.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.cards.colorless.Discovery;
 import com.megacrit.cardcrawl.cards.tempCards.Shiv;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.VulnerablePower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.cards.Rare.Tolerance;
 import shadowverse.cards.Temp.WhiteArtifact;

 public class Knife
   extends CustomRelic {
   public static final String ID = "shadowverse:Knife";
   public static final String IMG = "img/relics/Knife.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Knife_Outline.png";

   public Knife() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.COMMON, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   public void onEquip() {
         this.counter = 0;
     }

     @Override
     public void atBattleStart(){
       if (this.counter>=6){
             addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Tolerance()));
       }else if (this.counter>=3){
             addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Discovery()));
       }else {
             addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Shiv()));
         }
     }

     public void onLoseHp(int damageAmount) {
       this.counter--;
       if (this.counter<0)
           this.counter=0;
     }

     public void onVictory() {
       this.counter++;
     }

     public AbstractRelic makeCopy() {
     return (AbstractRelic)new Knife();
   }
 }
