 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.actions.utility.UseCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.relics.AbstractRelic;

 
 
 
 public class Offensive2
   extends CustomRelic
 {
   public static final String ID = "shadowverse:Offensive2";
   public static final String IMG = "img/relics/Offensive.png";
   public static final String OUTLINE_IMG = "img/relics/outline/Offensive_Outline.png";
   
   public Offensive2() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.STARTER, LandingSound.CLINK);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     public void atTurnStart() {
         this.counter = 0;
     }

     @Override
     public void onUseCard(AbstractCard card, UseCardAction action) {
       this.counter = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
             if (this.counter % 5 == 0) {
                 flash();
                 addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
                 addToBot((AbstractGameAction)new DamageAllEnemiesAction(null,

                         DamageInfo.createDamageMatrix(2, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
             }
     }

     public void onVictory() {
         this.counter = -1;
     }
 
 
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new Offensive2();
   }
 }


