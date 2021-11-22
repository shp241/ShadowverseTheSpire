 package shadowverse.cards.Uncommon;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.DrawCardAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import rs.lazymankits.actions.common.DrawExptCardAction;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 import shadowverse.powers.EarthEssence;

 public class ImaginationRealized extends CustomCard {
   public static final String ID = "shadowverse:ImaginationRealized";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ImaginationRealized");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/ImaginationRealized.png";

   public ImaginationRealized() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.NONE);
     this.exhaust = true;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
           addToBot((AbstractGameAction)new DrawExptCardAction((AbstractCreature) abstractPlayer, 3, card -> true, new AbstractGameAction() {
               @Override
               public void update() {
                   this.isDone = true;
                   for (AbstractCard c : DrawCardAction.drawnCards) {
                       if (c.costForTurn > 0) {
                           c.costForTurn = 0;
                           c.isCostModifiedForTurn = true;
                       }
                       if (ImaginationRealized.this.upgraded && c.cost > 0){
                           c.cost = 0;
                           c.isCostModified = true;
                       }
                   }
               }
           }));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new ImaginationRealized();
   }
 }

