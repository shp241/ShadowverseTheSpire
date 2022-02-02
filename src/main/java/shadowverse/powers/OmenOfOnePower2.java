 package shadowverse.powers;

 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
 import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.DexterityPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;


 public class OmenOfOnePower2
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:OmenOfOnePower2";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:OmenOfOnePower2");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

   public OmenOfOnePower2(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/OmenOfOnePower2.png");
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     if (isPlayer) {
         boolean playedUnder2 = false;
         boolean playedUpon2 = false;
         for (AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisTurn){
             if (c.type!= AbstractCard.CardType.SKILL){
                 if (c.cost<=2){
                     playedUnder2 = true;
                 }
                 if (c.cost>=2){
                     playedUpon2 = true;
                 }
             }
         }
         if (playedUnder2){
             addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(this.owner, 10, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
         }
         if (playedUpon2){
             addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature) AbstractDungeon.player, DamageInfo.createDamageMatrix(18, true), DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_HEAVY, true));
         }
     } 
   }
 }


