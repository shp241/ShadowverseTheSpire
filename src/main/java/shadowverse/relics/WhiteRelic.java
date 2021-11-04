 package shadowverse.relics;

 import basemod.abstracts.CustomRelic;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.helpers.ImageMaster;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.WeakPower;
 import com.megacrit.cardcrawl.relics.AbstractRelic;

 public class WhiteRelic
   extends CustomRelic {
   public static final String ID = "shadowverse:WhiteRelic";
   public static final String IMG = "img/relics/WhiteRelic.png";
   public static final String OUTLINE_IMG = "img/relics/outline/WhiteRelic_Outline.png";

   public WhiteRelic() {
     super(ID, ImageMaster.loadImage(IMG), RelicTier.UNCOMMON, LandingSound.HEAVY);
   }
   
   public String getUpdatedDescription() {
     return this.DESCRIPTIONS[0];
   }

     @Override
     public void onExhaust(AbstractCard card) {
         if (card.type== AbstractCard.CardType.ATTACK)
             if (!(AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
                 AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                 addToBot((AbstractGameAction)new ApplyPowerAction(randomMonster,AbstractDungeon.player,(AbstractPower)new WeakPower(randomMonster,1,false),1));
             }
     }

     public AbstractRelic makeCopy() {
     return (AbstractRelic)new WhiteRelic();
   }
 }
