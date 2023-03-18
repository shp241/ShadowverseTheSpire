 package shadowverse.cards.Common;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Color;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.animations.VFXAction;
 import com.megacrit.cardcrawl.actions.common.DamageAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.LoseHPAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
 import shadowverse.characters.Vampire;


 public class DancingMiniSoulDevil
   extends CustomCard {
   public static final String ID = "shadowverse:DancingMiniSoulDevil";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:DancingMiniSoulDevil");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/DancingMiniSoulDevil.png";

   public DancingMiniSoulDevil() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
     this.baseBlock = 9;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new SFXAction("DancingMiniSoulDevil"));
       addToBot(new GainBlockAction(p,this.block));
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new DancingMiniSoulDevil();
   }
 }

