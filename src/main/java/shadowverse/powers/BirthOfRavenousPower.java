 package shadowverse.powers;

 import com.badlogic.gdx.graphics.Texture;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import shadowverse.action.SpellBoostAction;

 public class BirthOfRavenousPower
   extends AbstractPower {
   public static final String POWER_ID = "shadowverse:BirthOfRavenousPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:BirthOfRavenousPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   public int counter = 0;

   public BirthOfRavenousPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/BirthOfRavenousPower.png");
   }
 
   
   public void onPlayCard(AbstractCard card, AbstractMonster m) {
     this.counter++;
   }
 
   
   public void atStartOfTurnPostDraw() {
     flash();
     if (this.counter > 3) {
         addToBot((AbstractGameAction) new DrawCardAction((AbstractCreature) AbstractDungeon.player, 1));
     }
     this.counter = 0;
   }
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 }

