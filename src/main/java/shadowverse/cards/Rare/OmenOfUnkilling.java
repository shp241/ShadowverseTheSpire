 package shadowverse.cards.Rare;
 


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import shadowverse.characters.Elf;


 public class OmenOfUnkilling
   extends CustomCard
 {
   public static final String ID = "shadowverse:OmenOfUnkilling";
   public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfUnkilling");
   public static final String NAME = cardStrings.NAME;
   public static final String DESCRIPTION = cardStrings.DESCRIPTION;
   public static final String IMG_PATH = "img/cards/OmenOfUnkilling.png";

   public OmenOfUnkilling() {
     super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.POWER, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.ALL_ENEMY);
   }
 
   
   public void upgrade() {
     if (!this.upgraded) {
       upgradeName();
       this.selfRetain = true;
       this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
       initializeDescription();
     }
   }

   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
     boolean canUse = super.canUse(p, m);
     if (!canUse)
       return false;
     if (p.drawPile.size() != 6) {
       this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
       return false;
     }
     return canUse;
   }

   public void triggerOnGlowCheck() {
     boolean glow = true;
     if (AbstractDungeon.player.drawPile.size() != 6) {
       glow = false;
     }
     if (glow) {
       this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
     } else {
       this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
     }
   }



   
   public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
       addToBot((AbstractGameAction)new SFXAction("OmenOfUnkilling"));
     for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
       if (!mo.isDeadOrEscaped()){
         AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new CollectorCurseEffect(mo.hb.cX, mo.hb.cY), 2.0F));
         mo.decreaseMaxHealth((mo.maxHealth-6));;
       }
     }
   }
 
   
   public AbstractCard makeCopy() {
     return (AbstractCard)new OmenOfUnkilling();
   }
 }


