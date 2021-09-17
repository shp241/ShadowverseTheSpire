 package shadowverse.powers;
 


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import shadowverse.characters.Witchcraft;

 
 public class TheWorldPower
   extends AbstractPower
 {
   public static final String POWER_ID = "shadowverse:TheWorldPower";
   private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:TheWorldPower");
   public static final String NAME = powerStrings.NAME;
   public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
   
   public TheWorldPower(AbstractCreature owner) {
     this.name = NAME;
     this.ID = "shadowverse:TheWorldPower";
     this.owner = owner;
     this.amount = -1;
     this.type = PowerType.BUFF;
     updateDescription();
     this.img = new Texture("img/powers/TheWorldPower.png");
   }

   
   public void updateDescription() {
     this.description = DESCRIPTIONS[0];
   }
 
   
   public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
     if (isPlayer) {
       addToBot((AbstractGameAction)new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(20, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
       addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Burn(), 2));
     } 
   }
 }

