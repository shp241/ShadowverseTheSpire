 package shadowverse.cards.Common;

 import basemod.abstracts.AbstractCardModifier;
 import basemod.abstracts.CustomCard;
 import basemod.helpers.CardModifierManager;
 import com.badlogic.gdx.Gdx;
 import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
 import com.megacrit.cardcrawl.actions.AbstractGameAction;
 import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
 import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
 import com.megacrit.cardcrawl.cards.AbstractCard;
 import com.megacrit.cardcrawl.characters.AbstractPlayer;
 import com.megacrit.cardcrawl.core.CardCrawlGame;
 import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
 import com.megacrit.cardcrawl.localization.CardStrings;
 import com.megacrit.cardcrawl.monsters.AbstractMonster;
 import com.megacrit.cardcrawl.random.Random;
 import shadowverse.cardmods.ImmoralDesireMod1;
 import shadowverse.cards.Temp.MysterianCircle;
 import shadowverse.cards.Temp.MysterianMissile;
 import shadowverse.characters.AbstractShadowversePlayer;
 import shadowverse.characters.Witchcraft;

 import java.util.ArrayList;

 public class MysterianWisdom
   extends CustomCard {
   public static final String ID = "shadowverse:MysterianWisdom";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MysterianWisdom");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
     public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("ExhaustAction")).TEXT;
   private float rotationTimer;
   private int previewIndex;
   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new MysterianCircle());
     list.add(new MysterianMissile());
     return list;
   }
   public static final String IMG_PATH = "img/cards/MysterianWisdom.png";

   public MysterianWisdom() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.COMMON, CardTarget.NONE);
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
   public void update() {
     super.update();
     if (this.hb.hovered)
       if (this.rotationTimer <= 0.0F) {
         this.rotationTimer = 2.0F;
         this.cardsToPreview = (AbstractCard)returnChoice().get(previewIndex).makeCopy();
         if (this.previewIndex == returnChoice().size() - 1) {
           this.previewIndex = 0;
         } else {
           this.previewIndex++;
         }
         if (this.upgraded)
           this.cardsToPreview.upgrade();
       } else {
         this.rotationTimer -= Gdx.graphics.getDeltaTime();
       }
   }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
           ((AbstractShadowversePlayer) AbstractDungeon.player).mysteriaCount++;
       }
       addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],false,false,card -> {
           return true;
       }, abstractCards ->{
           for (AbstractCard c:abstractCards){
               addToBot((AbstractGameAction)new ExhaustSpecificCardAction(c,abstractPlayer.hand));
               addToBot((AbstractGameAction)new MakeTempCardInHandAction(new MysterianMissile(), 1));
               addToBot((AbstractGameAction)new MakeTempCardInHandAction(new MysterianCircle(), 1));
               if (this.upgraded){
                   addToBot((AbstractGameAction)new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(),1,true,true,false));
               }
           }
       } ));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new MysterianWisdom();
   }
 }

