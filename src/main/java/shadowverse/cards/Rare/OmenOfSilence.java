 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import shadowverse.action.NecromanceAction;
 import shadowverse.action.SilenceAction;
 import shadowverse.characters.Necromancer;
import shadowverse.powers.Cemetery;


 public class OmenOfSilence extends CustomCard {
   public static final String ID = "shadowverse:OmenOfSilence";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfSilence");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/OmenOfSilence.png";

   public OmenOfSilence() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.ALL_ENEMY);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(1);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Divinity"));
       addToBot((AbstractGameAction)new SFXAction("OmenOfSilence"));
       int playerNecromance = 0;
       if (abstractPlayer.hasPower(Cemetery.POWER_ID)){
           for (AbstractPower p :abstractPlayer.powers){
               if (p.ID.equals(Cemetery.POWER_ID))
                   playerNecromance = p.amount;
           }
       }
       if (playerNecromance>=3){
           addToBot((AbstractGameAction)new NecromanceAction(3,null,(AbstractGameAction)new SilenceAction()));

       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new OmenOfSilence();
   }
 }

