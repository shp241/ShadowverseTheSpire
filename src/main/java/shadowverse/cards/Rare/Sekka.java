 package shadowverse.cards.Rare;


 import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FireballEffect;
import shadowverse.cards.Temp.ResolveOfSekka;
import shadowverse.characters.Elf;


 public class Sekka extends CustomCard {
   public static final String ID = "shadowverse:Sekka";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Sekka");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/Sekka.png";

   public Sekka() {
     super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ENEMY);
     this.baseDamage = 1;
     this.baseBlock = 3;
     this.cardsToPreview = (AbstractCard)new ResolveOfSekka();
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       upgradeBlock(2);
       this.cardsToPreview.upgrade();
         this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
         initializeDescription();
     } 
   }

     public void applyPowers() {
         super.applyPowers();
         int count = 0;
         for(AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
             if (c.type == CardType.ATTACK&&!(c.hasTag(CardTags.STRIKE))){
                 count++;
             }
         }
         this.rawDescription = cardStrings.DESCRIPTION;
         this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
         this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
         initializeDescription();
     }
   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("Sekka"));
       addToBot((AbstractGameAction)new GainBlockAction(abstractPlayer,this.block));
       int count = 0;
       for(AbstractCard c: AbstractDungeon.actionManager.cardsPlayedThisCombat){
           if (c.type == CardType.ATTACK&&!(c.hasTag(CardTags.STRIKE))){
               count++;
           }
       }
       count--;
       if (count>=9){
           addToBot((AbstractGameAction)new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),1));
           count = 9;
       }
       for (int i=0;i<count;i++){
           if (!abstractMonster.isDeadOrEscaped()){
               AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new FireballEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, abstractMonster.hb.cX, abstractMonster.hb.cY), 0.2F));
               addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
           }
       }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new Sekka();
   }
 }
