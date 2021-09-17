 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.cards.Temp.AnnesSummoning;
 import shadowverse.cards.Temp.ExceedBurst;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;


 public class NewAnne
   extends CustomCard {
   public static final String ID = "shadowverse:NewAnne";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NewAnne");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NewAnne.png";

   public NewAnne() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.ENEMY);
     this.cardsToPreview = (AbstractCard)new ExceedBurst();
     this.baseBlock = 5;
     this.baseDamage = 5;
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.cardsToPreview.upgrade();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     ((AbstractShadowversePlayer)abstractPlayer).mysteriaCount++;
     int drawPileAmt = abstractPlayer.drawPile.group.size();
     int masterDeckAmt = AbstractDungeon.player.masterDeck.group.size();
     if (drawPileAmt <= masterDeckAmt/2){
         AbstractCard a = (AbstractCard)new AnnesSummoning();
         if (this.upgraded){
             a.upgrade();
         }
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(a,1));
     }
       if (drawPileAmt>=2){
           int rand = AbstractDungeon.cardRandomRng.random(drawPileAmt-1);
           int rand2 = AbstractDungeon.cardRandomRng.random(drawPileAmt-1);
           while (true){
               if (rand2==rand){
                   rand2 = AbstractDungeon.cardRandomRng.random(drawPileAmt-1);
               }else {
                   break;
               }
           }
           addToBot((AbstractGameAction)new ExhaustSpecificCardAction(abstractPlayer.drawPile.group.get(rand),abstractPlayer.drawPile));
           addToBot((AbstractGameAction)new ExhaustSpecificCardAction(abstractPlayer.drawPile.group.get(rand2),abstractPlayer.drawPile));
       }
     AbstractCard c = this.cardsToPreview;
     addToBot((AbstractGameAction)new SFXAction("NewAnne"));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer,this.block));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NewAnne();
   }
 }


