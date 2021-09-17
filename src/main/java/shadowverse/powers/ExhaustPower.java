 package shadowverse.powers;
 


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;


 public class ExhaustPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:ExhaustPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ExhaustPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   public ArrayList<AbstractCard> cardGroup;
   
   public ExhaustPower(AbstractCreature owner, ArrayList<AbstractCard> cardGroup) {
     this.name = NAME;
     this.ID = POWER_ID;
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.DEBUFF;
     this.cardGroup = cardGroup;
     updateDescription();
     loadRegion("confusion");
   }

   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     if (isPlayer) {
         for (AbstractCard c:cardGroup){
             if (((AbstractPlayer)this.owner).hand.group.contains(c)){
                 addToTop((AbstractGameAction)new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
             }
         }
         addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "shadowverse:ExhaustPower"));
     }
   }
 }


