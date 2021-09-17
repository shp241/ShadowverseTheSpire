 package shadowverse.cards.Uncommon;


 import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
 import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
 import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
 import shadowverse.action.ChoiceAction2;
 import shadowverse.action.ReanimateAction;
import shadowverse.cards.Temp.EternalPotion;
import shadowverse.cards.Temp.InstantPotion;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;
import java.util.List;


 public class Ceridwen extends CustomCard implements BranchableUpgradeCard {
   public static final String ID = "shadowverse:Ceridwen";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ceridwen");
     public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Ceridwen2");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Ceridwen.png";
     public static final String IMG_PATH2 = "img/cards/Ceridwen2.png";
     private boolean branch2 = false;
     private float rotationTimer;
     private int previewIndex;
     public static ArrayList<AbstractCard> returnProphecy(){
         ArrayList<AbstractCard> list = new ArrayList<>();
         list.add(new EternalPotion());
         list.add(new InstantPotion());
         return list;
     }

   public Ceridwen() {
     super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.UNCOMMON, CardTarget.SELF);
     this.baseBlock = 6;
   }

     public void update(){
         super.update();
         if (branch2){
             if (this.hb.hovered)
                 if (this.rotationTimer <= 0.0F) {
                     this.rotationTimer = 2.0F;
                     this.cardsToPreview = (AbstractCard)returnProphecy().get(previewIndex).makeCopy();
                     if (this.previewIndex == returnProphecy().size() - 1) {
                         this.previewIndex = 0;
                     } else {
                         this.previewIndex++;
                     }
                 } else {
                     this.rotationTimer -= Gdx.graphics.getDeltaTime();
                 }
         }
     }
   
   public void upgrade() {
       ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
   }
 
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
         switch (chosenBranch()){
             case 0:
                 addToBot((AbstractGameAction)new SFXAction("Ceridwen"));
                 addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
                 if (this.costForTurn>0){
                     addToBot((AbstractGameAction)new ReanimateAction(3));
                 }
                 break;
             case 1:
                 addToBot((AbstractGameAction)new SFXAction("Ceridwen2"));
                 addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
                 AbstractCard eternal = (AbstractCard)new EternalPotion();
                 AbstractCard instant = (AbstractCard)new InstantPotion();
                 addToBot((AbstractGameAction)new GainEnergyAction(1));
                 addToBot((AbstractGameAction)new ChoiceAction2(eternal,instant));
         }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Ceridwen();
   }

     @Override
     public List<UpgradeBranch> possibleBranches() {
         ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
         list.add(new UpgradeBranch() {
             @Override
             public void upgrade() {
                 ++Ceridwen.this.timesUpgraded;
                 Ceridwen.this.upgraded = true;
                 Ceridwen.this.name = NAME + "+";
                 Ceridwen.this.initializeTitle();
                 Ceridwen.this.baseBlock = 12;
                 Ceridwen.this.upgradedBlock = true;
             }
         });
         list.add(new UpgradeBranch() {
             @Override
             public void upgrade() {
                 ++Ceridwen.this.timesUpgraded;
                 Ceridwen.this.upgraded = true;
                 Ceridwen.this.textureImg = IMG_PATH2;
                 Ceridwen.this.loadCardImage(IMG_PATH2);
                 Ceridwen.this.name = cardStrings2.NAME;
                 Ceridwen.this.initializeTitle();
                 Ceridwen.this.rawDescription = cardStrings2.DESCRIPTION;
                 Ceridwen.this.initializeDescription();
                 Ceridwen.this.branch2 = true;
                 Ceridwen.this.rarity = CardRarity.RARE;
                 Ceridwen.this.setDisplayRarity(Ceridwen.this.rarity);
             }
         });
         return list;
     }
 }

