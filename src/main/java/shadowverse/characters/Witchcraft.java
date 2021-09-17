 package shadowverse.characters;

 import basemod.animations.AbstractAnimation;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.beyond.SpireHeart;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import shadowverse.cards.Basic.Insight;

import java.util.ArrayList;


 public class Witchcraft
   extends AbstractShadowversePlayer
 {


     public static class Enums
   {
     @SpireEnum
     public static PlayerClass WITCHCRAFT;
     @SpireEnum(name = "WITCH_BLUE_COLOR")
     public static AbstractCard.CardColor COLOR_BLUE;
     @SpireEnum(name = "WITCH_BLUE_COLOR")
     public static CardLibrary.LibraryType TYPE_BLUE;


   }
   public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Witchcraft");
   
   public static final int ENERGY_PER_TURN = 3;
   public static final String WITCHCRAFT_SHOULDER_2 = "img/character/Witchcraft/shoulder.png";
   public static final String WITCHCRAFT_SHOULDER_1 = "img/character/Witchcraft/shoulder.png";
   public static final String WITCHCRAFT_CORPSE = "img/character/Witchcraft/corpse.png";
   private static final int STARTING_HP = 70;
   private static final int MAX_HP = 70;
   private static final int STARTING_GOLD = 99;
   private static final int HAND_SIZE = 5;
   private static final int ORB_SLOTS = 0;
   private static final int ASCENSION_MAX_HP_LOSS = 5;
   
   public Witchcraft(String name) {
     super(name, Enums.WITCHCRAFT, null, null, null, (AbstractAnimation)new SpriterAnimation("img/character/Witchcraft/sprite/Witchcraft.scml"));
     initializeClass(null, "img/character/Witchcraft/shoulder.png", "img/character/Witchcraft/shoulder.png", "img/character/Witchcraft/corpse.png", getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(3));
   }
 
   
   public ArrayList<String> getStartingDeck() {
     ArrayList<String> starterDeck = new ArrayList<>(); int i;
     for (i = 0; i < 4; i++) {
       starterDeck.add("shadowverse:Strike_W");
     }
     for (i = 0; i < 4; i++) {
       starterDeck.add("shadowverse:Defend_W");
     }
     starterDeck.add("shadowverse:Insight");
     starterDeck.add("shadowverse:FieryEmbrace");
     return starterDeck;
   }
 
   
   public ArrayList<String> getStartingRelics() {
     ArrayList<String> retVal = new ArrayList<>();
     retVal.add("shadowverse:Offensive");
     UnlockTracker.markRelicAsSeen("shadowverse:Offensive");
     return retVal;
   }
 
   
   public CharSelectInfo getLoadout() {
     return new CharSelectInfo(charStrings.NAMES[0], charStrings.TEXT[0], 70, 70, 0, 99, 5, (AbstractPlayer)this, 
 
 
 
 
 
 
 
         
         getStartingRelics(), 
         getStartingDeck(), false);
   }
 
 
   
   public String getTitle(PlayerClass playerClass) {
     return charStrings.NAMES[0];
   }
 
   
   public AbstractCard.CardColor getCardColor() {
     return Enums.COLOR_BLUE;
   }
 
   
   public Color getCardRenderColor() {
     return CardHelper.getColor(46, 71, 81);
   }
 
   
   public AbstractCard getStartCardForEvent() {
     return (AbstractCard)new Insight();
   }
 
   
   public Color getCardTrailColor() {
     return CardHelper.getColor(46, 71, 81);
   }
 
   
   public int getAscensionMaxHPLoss() {
     return 5;
   }
 
   
   public BitmapFont getEnergyNumFont() {
     return FontHelper.energyNumFontGreen;
   }
 
   
   public void doCharSelectScreenSelectEffect() {
     CardCrawlGame.sound.playA("witch_selected", 0.0F);
     CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
   }
 
   
   public String getCustomModeCharacterButtonSoundKey() {
     return "AUTOMATON_ORB_SPAWN";
   }
 
   
   public String getLocalizedCharacterName() {
     return charStrings.NAMES[0];
   }
 
   
   public AbstractPlayer newInstance() {
     return (AbstractPlayer)new Witchcraft(this.name);
   }
 
   
   public String getSpireHeartText() {
     return SpireHeart.DESCRIPTIONS[8];
   }
 
   
   public Color getSlashAttackColor() {
     return CardHelper.getColor(46, 71, 81);
   }
 
   
   public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
     return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY };
   }
 
   
   public String getVampireText() {
     return Vampires.DESCRIPTIONS[0];
   }
 


     public void damage(DamageInfo info) {
       super.damage(info);
       if (lastDamageTaken >= 20){
           CardCrawlGame.sound.playA("XCW_Hurt", 0.0F);
       }else if (lastDamageTaken > 0 ){
           String sound = null;
           int roll = AbstractDungeon.cardRandomRng.random(99);
           if (roll < 50) {
               sound = "XCW_Hurt2";
           } else if (roll < 80) {
               sound = "XCW_Hurt3";
           } else {
               sound = "XCW_Hurt4";
           }
           CardCrawlGame.sound.playA(sound, 0.0F);
       }
     }
 }

