 package shadowverse.powers;
 
 import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import shadowverse.characters.AbstractShadowversePlayer;










 
 
 
 
 
 
 
 public class MechabookSorcererPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:MechabookSorcererPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:MechabookSorcererPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public MechabookSorcererPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = "shadowverse:MechabookSorcererPower";
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/MechabookSorcererPower.png");
   }
 
   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0] + '\001' + DESCRIPTIONS[1];
   }
 
   
   public void onUseCard(AbstractCard card, UseCardAction action) {
     if (card.hasTag(AbstractShadowversePlayer.Enums.MACHINE)) {
       flash();
       addToBot((AbstractGameAction)new GainEnergyAction(1));
     } 
   }
 }

