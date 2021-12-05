 package shadowverse.relics;


 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.helpers.PowerTip;
 import com.megacrit.cardcrawl.helpers.TipHelper;
 import com.megacrit.cardcrawl.localization.PowerStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;
 import shadowverse.powers.IminaPower;
 import shadowverse.powers.TweyenPower;


 public class IminaRelic
   extends CustomRelic
 {
   public static final String ID = "shadowverse:IminaRelic";
   public static final String IMG = "img/relics/IminaRelic.png";
   public static final String OUTLINE_IMG = "img/relics/outline/EarthEssenceRelic_Outline.png";
     public static final PowerStrings powStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:IminaPower");

   public IminaRelic() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.SHOP, LandingSound.SOLID);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }
   
   public void atBattleStart() {
       flash();
       AbstractMonster mo = AbstractDungeon.getRandomMonster();
       if (mo!=null&&!mo.isDeadOrEscaped()){
           addToBot((AbstractGameAction) new RelicAboveCreatureAction((AbstractCreature) mo, this));
           addToBot((AbstractGameAction)new ApplyPowerAction(mo,AbstractDungeon.player,(AbstractPower)new IminaPower(mo)));
       }
   }

   @Override
   protected void initializeTips(){
       super.initializeTips();
       this.tips.add(new PowerTip(
               TipHelper.capitalize(powStrings.NAME), powStrings.DESCRIPTIONS[0]));
   }
   
   public AbstractRelic makeCopy() {
     return (AbstractRelic)new IminaRelic();
   }
 }

