 package shadowverse.cards.Uncommon;


 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.*;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.cards.DamageInfo;
 import com.megacrit.cardcrawl.cards.status.Burn;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.cards.Temp.NewEmber;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;


 public class NewGrea
   extends CustomCard {
   public static final String ID = "shadowverse:NewGrea";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:NewGrea");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/NewGrea.png";

   public NewGrea() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.ENEMY);
     this.cardsToPreview = (AbstractCard)new NewEmber();
     this.baseBlock = 7;
     this.baseDamage = 9;
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
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
       this.damage *=2;
       this.block *=2;
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
     addToBot((AbstractGameAction)new SFXAction("NewGrea"));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer,this.block));
     addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
     addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,1));
     addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Burn(), 2));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new NewGrea();
   }
 }


