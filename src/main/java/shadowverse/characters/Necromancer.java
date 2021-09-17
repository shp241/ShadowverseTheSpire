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
import shadowverse.cards.Basic.Defend_N;
import shadowverse.cards.Basic.SpartoiSergent;
import shadowverse.cards.Basic.Strike_N;
import shadowverse.cards.Basic.UndyingResentment;

import java.util.ArrayList;

public class Necromancer extends AbstractShadowversePlayer{
    public static class Enums
    {
        @SpireEnum
        public static PlayerClass Necromancer;
        @SpireEnum(name = "NECRO_PURPLE_COLOR")
        public static AbstractCard.CardColor COLOR_PURPLE;
        @SpireEnum(name = "NECRO_PURPLE_COLOR")
        public static CardLibrary.LibraryType TYPE_PURPLE;


    }

    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Necromancer");

    public static final int ENERGY_PER_TURN = 3;
    public static final String NEC_SHOULDER_2 = "img/character/Necromancer/shoulder.png";
    public static final String NEC_SHOULDER_1 = "img/character/Necromancer/shoulder.png";
    public static final String NEC_CORPSE = "img/character/Necromancer/corpse.png";
    private static final int STARTING_HP = 65;
    private static final int MAX_HP = 65;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 5;
    private static final int ORB_SLOTS = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;

    public Necromancer(String name) {
        super(name, Enums.Necromancer, null, null, null, (AbstractAnimation)new SpriterAnimation("img/character/Necromancer/images/necro.scml"));
        initializeClass(null, NEC_SHOULDER_2, NEC_SHOULDER_2, NEC_CORPSE, getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(3));
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> starterDeck = new ArrayList<>(); int i;
        for (i = 0; i < 4; i++) {
            starterDeck.add(Strike_N.ID);
        }
        for (i = 0; i < 4; i++) {
            starterDeck.add(Defend_N.ID);
        }
        starterDeck.add(UndyingResentment.ID);
        starterDeck.add(SpartoiSergent.ID);
        return starterDeck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("shadowverse:Offensive3");
        UnlockTracker.markRelicAsSeen("shadowverse:Offensive3");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(charStrings.NAMES[0], charStrings.TEXT[0], 65, 65, 0, 99, 5,
                (AbstractPlayer)this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return charStrings.NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.COLOR_PURPLE;
    }

    @Override
    public Color getCardRenderColor() {
        return CardHelper.getColor(71, 26, 106);
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return (AbstractCard)new UndyingResentment();
    }

    @Override
    public Color getCardTrailColor() {
        return CardHelper.getColor(71, 26, 106);
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 5;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontGreen;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("Necro_Selected", 0.0F);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "AUTOMATON_ORB_SPAWN";
    }

    @Override
    public String getLocalizedCharacterName() {
        return charStrings.NAMES[0];
    }

    @Override
    public AbstractPlayer newInstance() {
        return (AbstractPlayer)new Necromancer(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[8];
    }

    @Override
    public Color getSlashAttackColor() {
        return CardHelper.getColor(71, 26, 106);
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] { AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY, AbstractGameAction.AttackEffect.SLASH_HEAVY, AbstractGameAction.AttackEffect.FIRE, AbstractGameAction.AttackEffect.BLUNT_HEAVY };
    }

    @Override
    public String getVampireText() {
        return Vampires.DESCRIPTIONS[0];
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        if (lastDamageTaken >= 20){
            CardCrawlGame.sound.playA("Necro_Hurt", 0.0F);
        }else if (lastDamageTaken > 0 ){
            String sound = null;
            int roll = AbstractDungeon.cardRandomRng.random(99);
            if (roll < 50) {
                sound = "Necro_Hurt2";
            } else if (roll < 80) {
                sound = "Necro_Hurt3";
            } else {
                sound = "Necro_Hurt4";
            }
            CardCrawlGame.sound.playA(sound, 0.0F);
        }
    }
}
