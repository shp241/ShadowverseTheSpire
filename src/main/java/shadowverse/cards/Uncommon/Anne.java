 package shadowverse.cards.Uncommon;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Temp.AnnesSorcery;
import shadowverse.cards.Temp.AnnesSummoning;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;


 public class Anne
   extends CustomCard {
   public static final String ID = "shadowverse:Anne";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Anne");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Anne.png";
   public static final int BASE_COST = 6;

   private float rotationTimer;
   private int previewIndex;

   public static ArrayList<AbstractCard> returnChoice() {
     ArrayList<AbstractCard> list = new ArrayList<>();
     list.add(new AnnesSummoning());
     list.add(new AnnesSorcery());
     return list;
   }

   public Anne() {
     super(ID, NAME, IMG_PATH, BASE_COST, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 10;
     this.tags.add(AbstractShadowversePlayer.Enums.MYSTERIA);
   }

   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.hasTag(AbstractShadowversePlayer.Enums.MYSTERIA)) {
       flash();
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
       addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
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
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(3);
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     } 
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
     AbstractCard a = (AbstractCard)new AnnesSummoning();
     AbstractCard b = (AbstractCard)new AnnesSorcery();
     ((AbstractShadowversePlayer)abstractPlayer).mysteriaCount++;
     addToBot((AbstractGameAction)new SFXAction("Anne"));
     addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)abstractPlayer,this.block));
     if (this.upgraded){
       a.upgrade();
       b.upgrade();
     }
     addToBot((AbstractGameAction)new ChoiceAction2(new AbstractCard[] { a, b }));
     this.cost = BASE_COST;
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Anne();
   }
 }


