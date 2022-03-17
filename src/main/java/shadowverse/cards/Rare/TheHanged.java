 package shadowverse.cards.Rare;

 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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
 import com.megacrit.cardcrawl.powers.BlurPower;
 import com.megacrit.cardcrawl.rooms.AbstractRoom;
 import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
 import shadowverse.Shadowverse;
 import shadowverse.cards.Temp.TreacherousReversal;
 import shadowverse.characters.Elf;

 public class TheHanged
   extends CustomCard {
   public static final String ID = "shadowverse:TheHanged";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheHanged");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/TheHanged.png";

   public TheHanged() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
     this.cardsToPreview = (AbstractCard)new TreacherousReversal();
     this.baseBlock = 8;
     this.baseMagicNumber = 4;
     this.magicNumber = this.baseMagicNumber;
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
       upgradeMagicNumber(2);
     } 
   }

     @Override
     public void update() {
         if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                 Shadowverse.Enhance(3)) {
             setCostForTurn(3);
         } else {
             if(this.costForTurn!=0){
                 setCostForTurn(1);
             }
         }
         super.update();
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
     if (this.costForTurn == 3 && Shadowverse.Enhance(3)) {
         addToBot((AbstractGameAction)new SFXAction("TheHanged2"));
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
     }else {
         addToBot((AbstractGameAction)new SFXAction("TheHanged1"));
         addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
         addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new BlurPower((AbstractCreature)abstractPlayer, 1), 1));
         int count = 0;
         for (AbstractCard ca : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
             count++;
         }
         if (count >= 3){
             addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.magicNumber));
         }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new TheHanged();
   }
 }

