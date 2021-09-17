 package shadowverse.cards.Uncommon;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import shadowverse.Shadowverse;
 import shadowverse.action.GetEPAction;
 import shadowverse.cards.Temp.ArsMagna;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 import shadowverse.powers.EarthEssence;
 
 public class Cagliostro
   extends CustomCard {
   public static final String ID = "shadowverse:Cagliostro";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cagliostro");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Cagliostro.png";
   private boolean doubleCheck = false;
   
   public Cagliostro() {
     super("shadowverse:Cagliostro", NAME, "img/cards/Cagliostro.png", 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
     this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
     this.cardsToPreview = (AbstractCard)new ArsMagna();
     this.baseBlock = 5;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
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
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     boolean powerExists = false;
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:EarthEssence")) {
         powerExists = true;
         break;
       } 
     } 
     if (powerExists) {
       addToBot((AbstractGameAction)new SFXAction("Cagliostro2"));
       ((AbstractShadowversePlayer)abstractPlayer).earthCount++;
       if (EnergyPanel.getCurrentEnergy()-this.costForTurn >= 2)
           c.setCostForTurn(2);
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, -1), -1));
     } else {
       addToBot((AbstractGameAction)new SFXAction("Cagliostro1"));
     } 
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, 1), 1));
     if (this.costForTurn == 2 && Shadowverse.Enhance(2)) {
         addToBot((AbstractGameAction)new GetEPAction(true,1));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Cagliostro();
   }
 }


/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\cards\Uncommon\Cagliostro.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
*/