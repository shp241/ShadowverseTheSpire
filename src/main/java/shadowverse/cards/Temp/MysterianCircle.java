 package shadowverse.cards.Temp;
 
 import basemod.abstracts.CustomCard;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.AbstractCreature;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;
 
 public class MysterianCircle extends CustomCard {
   public static final String ID = "shadowverse:MysterianCircle";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MysterianCircle");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/MysterianCircle.png";
   
   public MysterianCircle() {
     super("shadowverse:MysterianCircle", NAME, "img/cards/MysterianCircle.png", 0, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.SPECIAL, CardTarget.SELF);
     this.baseBlock = 4;
     this.exhaust = true;
     this.isEthereal = true;
       this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       ((AbstractShadowversePlayer)abstractPlayer).mysteriaCount++;
     AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, this.block));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MysterianCircle();
   }
 }

