 package shadowverse.cards.Rare;

 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
 import shadowverse.cards.Temp.WhiteArtifact;
 import shadowverse.characters.Nemesis;


 public class OmenOfDestruction
   extends CustomCard
 {
   public static final String ID = "shadowverse:OmenOfDestruction";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfDestruction");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/OmenOfDestruction.png";

   public OmenOfDestruction() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF);
     this.baseMagicNumber = 2;
     this.magicNumber = this.baseMagicNumber;
     this.cardsToPreview = (AbstractCard)new WhiteArtifact();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.isInnate = true;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractDungeon.effectsQueue.add(new SpotlightPlayerEffect());
     addToBot((AbstractGameAction)new SFXAction("OmenOfDestruction"));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new ArtifactPower((AbstractCreature)abstractPlayer, this.magicNumber), this.magicNumber));
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy()));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new OmenOfDestruction();
   }
 }
