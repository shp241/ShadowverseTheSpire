 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;


 public class RevelrySeed
   extends CustomRelic
 {
   public static final String ID = "shadowverse:RevelrySeed";
   public static final String IMG = "img/relics/RevelrySeed.png";
   public static final String OUTLINE_IMG = "img/relics/outline/RevelrySeed.png";

   public RevelrySeed() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.SHOP, LandingSound.MAGICAL);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

   public void atBattleStart() {
       flash();
       addToBot((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player,(AbstractCreature)AbstractDungeon.player,(AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player,3),3));
       for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
           addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)m, this));
           m.addPower((AbstractPower)new StrengthPower((AbstractCreature)m, 3));
       }
       AbstractDungeon.onModifyPower();
   }

     public void onSpawnMonster(AbstractMonster monster) {
         monster.addPower((AbstractPower)new StrengthPower((AbstractCreature)monster, 3));
         AbstractDungeon.onModifyPower();
     }

   public AbstractRelic makeCopy() {
     return (AbstractRelic)new RevelrySeed();
   }
 }


