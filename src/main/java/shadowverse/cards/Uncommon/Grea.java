 package shadowverse.cards.Uncommon;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Temp.Ember;
import shadowverse.cards.Temp.Inferno;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;


 public class Grea
   extends CustomCard {
   public static final String ID = "shadowverse:Grea";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Grea");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Grea.png";

   private float rotationTimer;
   private int previewIndex;

   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new Ember());
     list.add(new Inferno());
     return list;
   }

   public Grea() {
     super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 12;
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
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
     ((AbstractShadowversePlayer)abstractPlayer).mysteriaCount++;
     AbstractCard a = (AbstractCard)new Ember();
     AbstractCard b = (AbstractCard)new Inferno();
     addToBot((AbstractGameAction)new SFXAction("Grea"));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer,this.block));
     if (this.upgraded){
       a.upgrade();
       b.upgrade();
     }
     addToBot((AbstractGameAction)new ChoiceAction2(new AbstractCard[] { a, b }));
     addToBot((AbstractGameAction)new MakeTempCardInDiscardAction((AbstractCard)new Burn(), 1));
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Grea();
   }
 }


