 package shadowverse.cards.Rare;
 


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import shadowverse.cards.Temp.CelestialShikigami;
import shadowverse.cards.Temp.DemonicShikigami;
import shadowverse.cards.Temp.PaperShikigami;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;

import java.util.ArrayList;


 public class Kuon
   extends CustomCard {
   public static final String ID = "shadowverse:Kuon";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Kuon");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Kuon.png";
   public static final int BASE_COST = 9;
     private float rotationTimer;
     private int previewIndex;

     public static ArrayList<AbstractCard> returnShikigami() {
         ArrayList<AbstractCard> list = new ArrayList<>();
         list.add(new PaperShikigami());
         list.add(new DemonicShikigami());
         list.add(new CelestialShikigami());
         return list;
     }

   public Kuon() {
     super(ID, NAME, IMG_PATH, BASE_COST, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
     this.tags.add(AbstractShadowversePlayer.Enums.SPELL_BOOST);
     this.baseBlock = 10;
   }
 
   
   public void triggerOnOtherCardPlayed(AbstractCard c) {
     if (c.type == CardType.SKILL) {
       flash();
       addToBot((AbstractGameAction)new SFXAction("spell_boost"));
       addToBot((AbstractGameAction)new ReduceCostAction((AbstractCard)this));
     } 
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
                 this.cardsToPreview = (AbstractCard)returnShikigami().get(previewIndex).makeCopy();
                 if (this.previewIndex == returnShikigami().size() - 1) {
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
     addToBot((AbstractGameAction)new SFXAction("Kuon"));
     addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
       AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartBuffEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY)));
     ArrayList<AbstractCard> shikigamiHand = returnShikigami();
     for (AbstractCard c : shikigamiHand){
         if (this.upgraded){
             c.upgrade();
         }
         addToBot((AbstractGameAction)new MakeTempCardInHandAction(c,1));
     }
     this.cost = BASE_COST;
   }
 
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Kuon();
   }
 }

