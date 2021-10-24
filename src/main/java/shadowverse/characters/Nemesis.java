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
import shadowverse.cards.Basic.*;
import shadowverse.cards.Curse.CurseOfPurgation;
import shadowverse.cards.Rare.Modest;
import shadowverse.cards.Rare.OmniscientKaiser;
import shadowverse.cards.Rare.Ralmia;
import shadowverse.cards.Rare.Spine;
import shadowverse.cards.Uncommon.CannonHermitCrab;
import shadowverse.cards.Uncommon.Miriam;

import java.util.ArrayList;

public class Nemesis extends AbstractShadowversePlayer{
    public static class Enums
    {
        @SpireEnum
        public static PlayerClass Nemesis;
        @SpireEnum(name = "NEMESIS_PURPLE_COLOR")
        public static AbstractCard.CardColor COLOR_SKY;
        @SpireEnum(name = "NEMESIS_PURPLE_COLOR")
        public static CardLibrary.LibraryType TYPE_SKY;


    }

    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Nemesis");

    public static final int ENERGY_PER_TURN = 3;
    public static final String Nm_SHOULDER_2 = "img/character/Nemesis/shoulder.png";
    public static final String Nm_SHOULDER_1 = "img/character/Nemesis/shoulder.png";
    public static final String Nm_CORPSE = "img/character/Nemesis/corpse.png";
    private static final int STARTING_HP = 75;
    private static final int MAX_HP = 75;
    private static final int STARTING_GOLD = 99;
    private static final int HAND_SIZE = 5;
    private static final int ORB_SLOTS = 0;
    private static final int ASCENSION_MAX_HP_LOSS = 5;

    public Nemesis(String name) {
        super(name, Enums.Nemesis, null, null, null, (AbstractAnimation)new SpriterAnimation("img/character/Nemesis/images/NewProject.autosave.autosave.scml"));
        initializeClass(null, Nm_SHOULDER_2, Nm_SHOULDER_2, Nm_CORPSE, getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(3));
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> starterDeck = new ArrayList<>(); int i;
        for (i = 0; i < 5; i++) {
            starterDeck.add(Strike_Nm.ID);
        }
        for (i = 0; i < 5; i++) {
            starterDeck.add(Defend_Nm.ID);
        }
        starterDeck.add(DimensionCut.ID);
        starterDeck.add(MagisteelLion.ID);
        starterDeck.add(CurseOfPurgation.ID);
        return starterDeck;
    }

    @Override
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add("shadowverse:Offensive5");
        UnlockTracker.markRelicAsSeen("shadowverse:Offensive5");
        return retVal;
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(charStrings.NAMES[0], charStrings.TEXT[0], 75, 75, 0, 99, 5,
                (AbstractPlayer)this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public String getTitle(PlayerClass playerClass) {
        return charStrings.NAMES[0];
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return Enums.COLOR_SKY;
    }

    @Override
    public Color getCardRenderColor() {
        return CardHelper.getColor(18, 108, 146);
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return (AbstractCard)new MagisteelLion();
    }

    @Override
    public Color getCardTrailColor() {
        return CardHelper.getColor(18, 108, 146);
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
        CardCrawlGame.sound.playA("Nemesis_Selected", 0.0F);
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
        return (AbstractPlayer)new Nemesis(this.name);
    }

    @Override
    public String getSpireHeartText() {
        return SpireHeart.DESCRIPTIONS[8];
    }

    @Override
    public Color getSlashAttackColor() {
        return CardHelper.getColor(18, 108, 146);
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
            CardCrawlGame.sound.playA("Nemesis_Hurt", 0.0F);
        }else if (lastDamageTaken > 0 ){
            String sound = null;
            int roll = AbstractDungeon.cardRandomRng.random(99);
            if (roll < 50) {
                sound = "Nemesis_Hurt2";
            } else if (roll < 80) {
                sound = "Nemesis_Hurt3";
            } else {
                sound = "Nemesis_Hurt4";
            }
            CardCrawlGame.sound.playA(sound, 0.0F);
        }
    }
}
