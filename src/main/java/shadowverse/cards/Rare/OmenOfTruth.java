 package shadowverse.cards.Rare;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.tempCards.Shiv;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.powers.DexterityPower;
 import com.megacrit.cardcrawl.powers.StrengthPower;
 import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;
import shadowverse.action.SpellBoostAction;
 import shadowverse.characters.Witchcraft;
 
 
 public class OmenOfTruth
   extends CustomCard
 {
   public static final String ID = "shadowverse:OmenOfTruth";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfTruth");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/OmenOfTruth.png";
   
   public OmenOfTruth() {
     super("shadowverse:OmenOfTruth", NAME, "img/cards/OmenOfTruth.png", 3, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
     this.baseMagicNumber = 6;
     this.magicNumber = this.baseMagicNumber;
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
             AbstractDungeon.effectsQueue.add(new    SpotlightPlayerEffect());
     addToBot((AbstractGameAction)new SpellBoostAction(abstractPlayer, (AbstractCard)this, AbstractDungeon.player.drawPile.group));
     addToBot((AbstractGameAction)new SFXAction("OmenOfTruth"));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new StrengthPower((AbstractCreature)abstractPlayer, 1), 1));
     addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new DexterityPower((AbstractCreature)abstractPlayer, 1), 1));
     addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Shiv(), 1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new OmenOfTruth();
   }
 }
