 package shadowverse.cards.Uncommon;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.powers.AbstractPower;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

 import shadowverse.Shadowverse;
import shadowverse.cards.Temp.ConjureGuardian;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 import shadowverse.powers.EarthEssence;
 
 
 
 public class GolemAssault
   extends CustomCard
 {
   public static final String ID = "shadowverse:GolemAssault";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GolemAssault");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/GolemAssault.png";

   
   public GolemAssault() {
     super("shadowverse:GolemAssault", NAME, "img/cards/GolemAssault.png", 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
     this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
     this.cardsToPreview = (AbstractCard)new ConjureGuardian();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.cardsToPreview.upgrade();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }

     @Override
     public void update() {
         if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                 Shadowverse.Enhance(2)){
             setCostForTurn(2);
         }else {
             setCostForTurn(0);
         }
         super.update();
     }

     public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     boolean powerExists = false;
     for (AbstractPower pow : abstractPlayer.powers) {
       if (pow.ID.equals("shadowverse:EarthEssence")) {
         powerExists = true;
         break;
       } 
     } 
     if (powerExists) {
         if (abstractPlayer instanceof  AbstractShadowversePlayer){
             ((AbstractShadowversePlayer)abstractPlayer).earthCount++;
         }
       c.setCostForTurn(0);
       addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EarthEssence((AbstractCreature)abstractPlayer, -1), -1));
     } 
     if (this.upgraded) {
       c.upgrade();
     }
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
     if (this.costForTurn == 2 && Shadowverse.Enhance(2)) {
       addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 2));
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new GolemAssault();
   }
 }

