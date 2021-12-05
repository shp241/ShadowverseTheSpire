 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Color;
 import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.BlurPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
 import shadowverse.action.NecromanceAction;
 import shadowverse.action.PrimalGigantAction;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Bishop;
 import shadowverse.characters.Necromancer;
 import shadowverse.powers.ImperialSaintPower;


 public class ImperialSaint extends CustomCard {
   public static final String ID = "shadowverse:ImperialSaint";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ImperialSaint");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ImperialSaint.png";

   public ImperialSaint() {
     super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.POWER, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 50;
     this.tags.add(AbstractShadowversePlayer.Enums.LEGEND);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBaseCost(3);
     } 
   }


   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("ImperialSaint"));
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.GOLD, true),1.0f));
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
       addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new BlurPower(abstractPlayer,1),1));
       addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractMonster,new ImperialSaintPower(abstractPlayer)));
   }


   public AbstractCard makeCopy() {
     return (AbstractCard)new ImperialSaint();
   }
 }

