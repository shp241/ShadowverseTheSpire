 package shadowverse.cards.Rare;
 
 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.cards.Temp.Beast;
import shadowverse.cards.Temp.Beauty;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Elf;

import java.util.ArrayList;


















 
 
 
 public class MiracleOfLove
   extends CustomCard
 {
   public static final String ID = "shadowverse:MiracleOfLove";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MiracleOfLove");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MiracleOfLove.png";
   private  boolean doubleCheck = false;
   
   public MiracleOfLove() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.NONE);
       this.baseBlock = 7;
       this.baseDamage = 9;
       this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
         upgradeBlock(3);
         upgradeDamage(3);
         this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
         initializeDescription();
     } 
   }


     public void triggerWhenDrawn() {
         if (Shadowverse.Enhance(2)) {
             super.triggerWhenDrawn();
             setCostForTurn(2);
             applyPowers();
         }
     }

     @Override
     public void applyPowers(){
         if (Shadowverse.Enhance(2))
             setCostForTurn(2);
         else
             resetAttributes();
         super.applyPowers();
     }

     @Override
     public void atTurnStart() {
         if (AbstractDungeon.player.hand.group.contains(this)){
                 setCostForTurn(2);
                 applyPowers();
         }
     }

     public void triggerOnOtherCardPlayed(AbstractCard c) {
         if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
             doubleCheck = true;
             if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                 resetAttributes();
                 applyPowers();
             }
         }else {
             if (doubleCheck) {
                 doubleCheck = false;
             }else {
                 if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                     resetAttributes();
                     applyPowers();
                 }
             }
         }
     }

     public void triggerOnGainEnergy(int e, boolean dueToCard) {
         if (EnergyPanel.getCurrentEnergy() >= 2) {
             setCostForTurn(2);
         }  else {
             resetAttributes();
         }
         applyPowers();
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       if (Shadowverse.Enhance(2) && this.costForTurn == 2 ){
           int count = 0;
           for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
               count++;
           }
           count--;
           if (count >= 2){
               addToBot((AbstractGameAction)new SFXAction("Beast"));
               addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage*2, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
               addToBot((AbstractGameAction)new WaitAction(1.0F));
               addToBot((AbstractGameAction)new SFXAction("Beauty"));
               addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,this.block*2));
           }else {
               addToBot((AbstractGameAction)new SFXAction("Beast"));
               addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
               addToBot((AbstractGameAction)new WaitAction(1.0F));
               addToBot((AbstractGameAction)new SFXAction("Beauty"));
               addToBot((AbstractGameAction)new GainBlockAction(AbstractDungeon.player,this.block));
           }
       }else {
           ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
           stanceChoices.add(new Beast());
           stanceChoices.add(new Beauty());
           addToBot((AbstractGameAction)new ChooseOneAction(stanceChoices));
       }
   }
 

 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MiracleOfLove();
   }
 }

