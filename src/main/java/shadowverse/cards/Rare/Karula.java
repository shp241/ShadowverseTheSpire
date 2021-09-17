 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
 import com.badlogic.gdx.graphics.Color;
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
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
 import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
 import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
 import shadowverse.characters.Nemesis;
 import shadowverse.powers.KarulaPower;


 public class Karula extends CustomCard {
   public static final String ID = "shadowverse:Karula";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Karula");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Karula.png";

   public Karula() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
     this.baseBlock = 9;
     this.baseMagicNumber = 1;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
       upgradeMagicNumber(1);
     } 
   }

   
   public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
       addToBot((AbstractGameAction)new SFXAction("Karula"));
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.ROYAL, true)));
       addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new MiracleEffect(Color.LIGHT_GRAY.cpy(),Color.SKY.cpy(),"HEAL_3")));
       addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new KarulaPower(p,this.magicNumber),this.magicNumber));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Karula();
   }
 }
