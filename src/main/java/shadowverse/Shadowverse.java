package shadowverse;/*     */
/*     */

import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.audio.Sfx;
import com.megacrit.cardcrawl.audio.SoundMaster;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shadowverse.cards.Basic.*;
import shadowverse.cards.Common.*;
import shadowverse.cards.Uncommon.LuminousMage;
import shadowverse.cards.Curse.CurseOfPurgation;
import shadowverse.cards.Curse.Geass;
import shadowverse.cards.Neutral.*;
import shadowverse.cards.Rare.*;
import shadowverse.cards.Status.BelphometStatus;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.cards.Temp.Mysteria;
import shadowverse.cards.Temp.*;
import shadowverse.cards.Uncommon.*;
import shadowverse.characters.*;
import shadowverse.events.GemFortune;
import shadowverse.events.LelouchCollaboration;
import shadowverse.events.PinyaEvent;
import shadowverse.monsters.*;
import shadowverse.potions.*;
import shadowverse.relics.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/*     */
/*     */
@SpireInitializer
/*     */ public class Shadowverse implements PostInitializeSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber {
    /*  36 */   public static final Color WITCH_BLUE = CardHelper.getColor(46, 71, 81);
    public static final Color ELF_GREEN = CardHelper.getColor(197, 220, 88);
    public static final Color NECRO_PURPLE = CardHelper.getColor(71, 26, 106);
    public static final Color VAMPIRE_SCARLET = CardHelper.getColor(107, 50, 55);
    public static final Color NEMESIS_SKY = CardHelper.getColor(18, 108, 146);
    public static final Color ROYAL_YELLOW = CardHelper.getColor(152, 156, 1);
    /*     */
    /*  38 */   public static final Logger logger = LogManager.getLogger(Shadowverse.class.getName());

    /*     */
    /*     */
    public static boolean Enhance(int EH) {
        /*  41 */
        boolean res = false;
        /*  42 */
        if (EnergyPanel.getCurrentEnergy() >= EH) {
            /*  43 */
            res = true;
            /*     */
        }
        /*  45 */
        return res;
        /*     */
    }

    /*     */
    /*     */
    public static boolean Accelerate(AbstractCard card) {
        /*  49 */
        boolean res = false;
        /*  50 */
        if (EnergyPanel.getCurrentEnergy() < card.cost) {
            /*  51 */
            res = true;
            /*     */
        }
        /*  53 */
        return res;
        /*     */
    }

    /*     */
    /*     */
    public Shadowverse() {
        /*  57 */
        logger.info("Subscribing");
        /*  58 */
        BaseMod.subscribe((ISubscriber) this);
        /*  59 */
        BaseMod.addColor(Witchcraft.Enums.COLOR_BLUE, WITCH_BLUE, WITCH_BLUE, WITCH_BLUE, WITCH_BLUE, WITCH_BLUE, WITCH_BLUE, WITCH_BLUE, "img/512card/bg_attack_default_gray.png", "img/512card/bg_skill_default_gray.png", "img/512card/bg_power_default_gray.png", "img/512card/card_default_gray_orb.png", "img/1024card/bg_attack_default_gray.png", "img/1024card/bg_skill_default_gray.png", "img/1024card/bg_power_default_gray.png", "img/1024card/card_default_gray_orb.png", "img/512card/card_small_orb.png");
        BaseMod.addColor(Elf.Enums.COLOR_GREEN, ELF_GREEN, ELF_GREEN, ELF_GREEN, ELF_GREEN, ELF_GREEN, ELF_GREEN, ELF_GREEN, "img/512elf/bg_attack_default_gray.png", "img/512elf/bg_skill_default_gray.png", "img/512elf/bg_power_default_gray.png", "img/512elf/card_default_gray_orb.png", "img/1024elf/bg_attack_default_gray.png", "img/1024elf/bg_skill_default_gray.png", "img/1024elf/bg_power_default_gray.png", "img/1024elf/card_default_gray_orb.png", "img/512elf/card_small_orb.png");
        BaseMod.addColor(Necromancer.Enums.COLOR_PURPLE, NECRO_PURPLE, NECRO_PURPLE, NECRO_PURPLE, NECRO_PURPLE, NECRO_PURPLE, NECRO_PURPLE, NECRO_PURPLE, "img/512necro/bg_attack_default_gray.png", "img/512necro/bg_skill_default_gray.png", "img/512necro/bg_power_default_gray.png", "img/512necro/card_default_gray_orb.png", "img/1024necro/bg_attack_default_gray.png", "img/1024necro/bg_skill_default_gray.png", "img/1024necro/bg_power_default_gray.png", "img/1024necro/card_default_gray_orb.png", "img/512necro/card_small_orb.png");
        BaseMod.addColor(Vampire.Enums.COLOR_SCARLET, VAMPIRE_SCARLET, VAMPIRE_SCARLET, VAMPIRE_SCARLET, VAMPIRE_SCARLET, VAMPIRE_SCARLET, VAMPIRE_SCARLET, VAMPIRE_SCARLET, "img/512vamp/bg_attack_default_gray.png", "img/512vamp/bg_skill_default_gray.png", "img/512vamp/bg_power_default_gray.png", "img/512vamp/card_default_gray_orb.png", "img/1024vamp/bg_attack_default_gray.png", "img/1024vamp/bg_skill_default_gray.png", "img/1024vamp/bg_power_default_gray.png", "img/1024vamp/card_default_gray_orb.png", "img/512vamp/card_small_orb.png");
        BaseMod.addColor(Nemesis.Enums.COLOR_SKY, NEMESIS_SKY, NEMESIS_SKY, NEMESIS_SKY, NEMESIS_SKY, NEMESIS_SKY, NEMESIS_SKY, NEMESIS_SKY, "img/512nemesis/bg_attack_default_gray.png", "img/512nemesis/bg_skill_default_gray.png", "img/512nemesis/bg_power_default_gray.png", "img/512nemesis/card_default_gray_orb.png", "img/1024nemesis/bg_attack_default_gray.png", "img/1024nemesis/bg_skill_default_gray.png", "img/1024nemesis/bg_power_default_gray.png", "img/1024nemesis/card_default_gray_orb.png", "img/512nemesis/card_small_orb.png");
        BaseMod.addColor(Royal.Enums.COLOR_YELLOW, ROYAL_YELLOW, ROYAL_YELLOW, ROYAL_YELLOW, ROYAL_YELLOW, ROYAL_YELLOW, ROYAL_YELLOW, ROYAL_YELLOW, "img/512royal/bg_attack_default_gray.png", "img/512royal/bg_skill_default_gray.png", "img/512royal/bg_power_default_gray.png", "img/512royal/card_default_gray_orb.png", "img/1024nemesis/bg_attack_default_gray.png", "img/1024nemesis/bg_skill_default_gray.png", "img/1024nemesis/bg_power_default_gray.png", "img/1024nemesis/card_default_gray_orb.png", "img/512royal/card_small_orb.png");
        /*  60 */
        logger.info("Success subscribe");
        /*     */
    }

    /*     */
    public static void initialize() {
        /*  63 */
        logger.info("Initializing");
        /*  64 */
        Shadowverse shadowverse = new Shadowverse();
        /*  65 */
        logger.info("Initialization success");
        /*     */
    }

    /*     */
    /*     */
    private static String loadJson(String jsonPath) {
        /*  69 */
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
        /*     */
    }

    /*     */
    /*     */
    /*     */
    public void receiveEditKeywords() {
        /*  74 */
        logger.info("Adding Keywords");
        /*  75 */
        String keywordsPath = "localization/keywords-" + Settings.language + ".json";
        /*  76 */
        Gson gson = new Gson();
        /*  77 */
        Keywords keywords = (Keywords) gson.fromJson(loadJson(keywordsPath), Keywords.class);
        /*  78 */
        Keyword[] var4 = keywords.keywords;
        /*  79 */
        int var5 = var4.length;
        /*  80 */
        for (int var6 = 0; var6 < var5; var6++) {
            /*  81 */
            Keyword key = var4[var6];
            /*  82 */
            logger.info("Loading keyword : " + key.NAMES[0]);
            /*  83 */
            BaseMod.addKeyword(key.NAMES, key.DESCRIPTION);
            /*     */
        }
        /*  85 */
        logger.info("Keywords setting finished.");
        /*     */
    }

    /*     */
    /*     */
    private HashMap<String, Sfx> getSoundsMap() {
        /*  89 */
        return (HashMap<String, Sfx>) ReflectionHacks.getPrivate(CardCrawlGame.sound, SoundMaster.class, "map");
        /*     */
    }

    /*     */
    /*     */
    /*     */
    public void receivePostInitialize() {
        BaseMod.addEvent(PinyaEvent.ID, PinyaEvent.class);
        BaseMod.addEvent(GemFortune.ID, GemFortune.class, TheCity.ID);
        BaseMod.addMonster(Belphomet.ID, Belphomet::new);
        BaseMod.addMonster(Iceschillendrig.ID, Iceschillendrig::new);
        BaseMod.addMonster(VincentBOSS.ID, VincentBOSS::new);
        BaseMod.addMonster(Lelouch.ID, () -> new Lelouch());
        BaseMod.addEvent(LelouchCollaboration.ID, LelouchCollaboration.class, TheBeyond.ID);
        BaseMod.addBoss(TheBeyond.ID, Iceschillendrig.ID, "img/monsters/UI/IC.png", "img/monsters/UI/IC_Outline.png");
        BaseMod.addBoss(TheBeyond.ID, Belphomet.ID, "img/monsters/UI/Belphomet.png", "img/monsters/UI/Belphomet_Outline.png");
        BaseMod.addBoss(TheBeyond.ID, VincentBOSS.ID, "img/monsters/UI/VincentBOSS.png", "img/monsters/UI/VincentBOSS_Outline.png");
        BaseMod.addMonster(Megaera.ID, () -> new Megaera());
        BaseMod.addEliteEncounter(TheBeyond.ID, new MonsterInfo(Megaera.ID, 1.5F));
        BaseMod.addMonster(Tisiphone.ID, () -> new Tisiphone());
        BaseMod.addEliteEncounter(TheBeyond.ID, new MonsterInfo(Tisiphone.ID, 1.5F));
        BaseMod.addMonster(Alector.ID, () -> new Alector());
        BaseMod.addEliteEncounter(TheBeyond.ID, new MonsterInfo(Alector.ID, 1.5F));
        BaseMod.addPotion(SatanPotion.class, Color.CYAN, Color.RED, Color.BLACK, SatanPotion.POTION_ID);
        BaseMod.addPotion(ElfPotion.class, Color.GREEN, Color.LIME, Color.GOLD, ElfPotion.POTION_ID, Elf.Enums.Elf);
        BaseMod.addPotion(BoostPotion.class, Color.BLUE, Color.SKY, Color.SKY, BoostPotion.POTION_ID, Witchcraft.Enums.WITCHCRAFT);
        BaseMod.addPotion(EarthPotion.class, Color.MAROON, Color.BROWN, Color.BROWN, EarthPotion.POTION_ID, Witchcraft.Enums.WITCHCRAFT);
        BaseMod.addPotion(KeepPotion.class, Color.PURPLE, Color.VIOLET, Color.VIOLET, KeepPotion.POTION_ID, Witchcraft.Enums.WITCHCRAFT);
        BaseMod.addPotion(CemeteryPotion.class, Color.WHITE, Color.PURPLE, Color.PURPLE, CemeteryPotion.POTION_ID, Necromancer.Enums.Necromancer);
        BaseMod.addPotion(ReanimatePotion.class, Color.DARK_GRAY, Color.BLACK, Color.PURPLE, ReanimatePotion.POTION_ID, Necromancer.Enums.Necromancer);
        BaseMod.addPotion(RosePotion.class, Color.MAGENTA, Color.RED, Color.MAGENTA, RosePotion.POTION_ID, Elf.Enums.Elf);
        BaseMod.addPotion(NaterranPotion.class, Color.FOREST, Color.BROWN, Color.FOREST, NaterranPotion.POTION_ID);
        BaseMod.addPotion(BatPotion.class, Color.SCARLET, Color.BLACK, Color.SCARLET, BatPotion.POTION_ID, Vampire.Enums.Vampire);
        BaseMod.addPotion(EpitaphPotion.class, Color.SCARLET, Color.GOLDENROD, Color.SCARLET, EpitaphPotion.POTION_ID, Vampire.Enums.Vampire);
        /*  94 */
        HashMap<String, Sfx> reflectedMap = getSoundsMap();
        /*  95 */
        reflectedMap.put("spell_boost", new Sfx("sounds/spell_boost2.wav"));
        /*  96 */
        reflectedMap.put("EarthEssence", new Sfx("sounds/EarthEssence.wav"));
        /*  97 */
        reflectedMap.put("witch_selected", new Sfx("sounds/witch_selected.wav"));
        /*  98 */
        reflectedMap.put("OmenOfTruth", new Sfx("sounds/OmenOfTruth.wav"));
        /*  99 */
        reflectedMap.put("DimensionalWitch", new Sfx("sounds/DimensionalWitch.wav"));
        /* 100 */
        reflectedMap.put("DimensionShift", new Sfx("sounds/DimensionShift.wav"));
        /* 101 */
        reflectedMap.put("ZealotOfTruth", new Sfx("sounds/ZealotOfTruth.wav"));
        /* 102 */
        reflectedMap.put("MysticSeeker", new Sfx("sounds/MysticSeeker.wav"));
        /* 103 */
        reflectedMap.put("EdictOfTruth", new Sfx("sounds/EdictOfTruth.wav"));
        /* 104 */
        reflectedMap.put("Lou", new Sfx("sounds/Lou.wav"));
        /* 105 */
        reflectedMap.put("TruthsAdjudication", new Sfx("sounds/TruthsAdjudication.wav"));
        /* 106 */
        reflectedMap.put("MasterMageLevi", new Sfx("sounds/MasterMageLevi.wav"));
        /* 107 */
        reflectedMap.put("PiousInstructor", new Sfx("sounds/PiousInstructor.wav"));
        /* 108 */
        reflectedMap.put("PiousInstructorPower", new Sfx("sounds/PiousInstructorPower.wav"));
        /* 109 */
        reflectedMap.put("Clarke", new Sfx("sounds/Clarke.wav"));
        /* 110 */
        reflectedMap.put("Clarke_Accelerate", new Sfx("sounds/Clarke_Accelerate.wav"));
        /* 111 */
        reflectedMap.put("Faust", new Sfx("sounds/Faust.wav"));
        /* 112 */
        reflectedMap.put("FaustPower", new Sfx("sounds/FaustPower.wav"));
        /* 113 */
        reflectedMap.put("Magisa", new Sfx("sounds/Magisa.wav"));
        /* 114 */
        reflectedMap.put("Chaos", new Sfx("sounds/Chaos.wav"));
        /* 115 */
        reflectedMap.put("Runie", new Sfx("sounds/Runie.wav"));
        /* 116 */
        reflectedMap.put("ForbiddenDarkMage", new Sfx("sounds/ForbiddenDarkMage.wav"));
        /* 117 */
        reflectedMap.put("DarkMagePower", new Sfx("sounds/DarkMagePower.wav"));
        /* 118 */
        reflectedMap.put("JetBroomWitch", new Sfx("sounds/JetBroomWitch.wav"));
        /* 119 */
        reflectedMap.put("TetrasMettle", new Sfx("sounds/TetrasMettle.wav"));
        /* 120 */
        reflectedMap.put("IsabellesConjuration", new Sfx("sounds/IsabellesConjuration.wav"));
        /* 121 */
        reflectedMap.put("SorceryInSolidarity", new Sfx("sounds/SorceryInSolidarity.wav"));
        /* 122 */
        reflectedMap.put("MechabookSorcerer", new Sfx("sounds/MechabookSorcerer.wav"));
        /* 123 */
        reflectedMap.put("SonicFour", new Sfx("sounds/SonicFour.wav"));
        /* 124 */
        reflectedMap.put("Tetra", new Sfx("sounds/Tetra.wav"));
        /* 125 */
        reflectedMap.put("MechastaffSorcerer", new Sfx("sounds/MechastaffSorcerer.wav"));
        /* 126 */
        reflectedMap.put("ArsMagna", new Sfx("sounds/ArsMagna.wav"));
        /* 127 */
        reflectedMap.put("Cagliostro1", new Sfx("sounds/Cagliostro1.wav"));
        /* 128 */
        reflectedMap.put("Cagliostro2", new Sfx("sounds/Cagliostro2.wav"));
        /* 129 */
        reflectedMap.put("Awakened", new Sfx("sounds/Awakened.wav"));
        /* 130 */
        reflectedMap.put("ErasmusPower", new Sfx("sounds/ErasmusPower.wav"));
        /* 131 */
        reflectedMap.put("Erasmus", new Sfx("sounds/Erasmus.wav"));
        /* 132 */
        reflectedMap.put("Oz", new Sfx("sounds/Oz.wav"));
        /* 133 */
        reflectedMap.put("OzPower", new Sfx("sounds/OzPower.wav"));
        /* 134 */
        reflectedMap.put("Geoelementist", new Sfx("sounds/Geoelementist.wav"));
        /* 135 */
        reflectedMap.put("Stormelementalist", new Sfx("sounds/Stormelementalist.wav"));
        /* 136 */
        reflectedMap.put("Pyromancer", new Sfx("sounds/Pyromancer.wav"));
        /* 137 */
        reflectedMap.put("Riley", new Sfx("sounds/Riley.wav"));
        /* 138 */
        reflectedMap.put("DualAngle", new Sfx("sounds/DualAngle.wav"));
        /* 139 */
        reflectedMap.put("Ghios", new Sfx("sounds/Ghios.wav"));
        /* 140 */
        reflectedMap.put("OmenOfOne", new Sfx("sounds/OmenOfOne.wav"));
        /* 141 */
        reflectedMap.put("Mysteria", new Sfx("sounds/Mysteria.wav"));
        /* 142 */
        reflectedMap.put("EarthFall", new Sfx("sounds/EarthFall.wav"));
        /* 143 */
        reflectedMap.put("InfernalSurge", new Sfx("sounds/InfernalSurge.wav"));
        /* 144 */
        reflectedMap.put("InfernalGaze", new Sfx("sounds/InfernalGaze.wav"));
        /* 145 */
        reflectedMap.put("HeavenFall", new Sfx("sounds/HeavenFall.wav"));
        /* 146 */
        reflectedMap.put("Flamelord", new Sfx("sounds/Flamelord.wav"));
        /* 147 */
        reflectedMap.put("ViciousCommander", new Sfx("sounds/ViciousCommander.wav"));
        /* 148 */
        reflectedMap.put("WrathfulIcefiend", new Sfx("sounds/WrathfulIcefiend.wav"));
        /* 149 */
        reflectedMap.put("HellBeast", new Sfx("sounds/HellBeast.wav"));
        /* 150 */
        reflectedMap.put("Satan", new Sfx("sounds/Satan.wav"));
        /* 151 */
        reflectedMap.put("Satan_Accelerate", new Sfx("sounds/Satan_Accelerate.wav"));
        /* 152 */
        reflectedMap.put("EmbodimentOfCocytus", new Sfx("sounds/EmbodimentOfCocytus.wav"));
        /* 153 */
        reflectedMap.put("Eleanor", new Sfx("sounds/Eleanor.wav"));
        reflectedMap.put("JudgmentWord", new Sfx("sounds/JudgmentWord.wav"));
        reflectedMap.put("Vincent", new Sfx("sounds/Vincent.wav"));
        reflectedMap.put("GrimoireSorcerer", new Sfx("sounds/GrimoireSorcerer.wav"));
        reflectedMap.put("Aeroelementalist", new Sfx("sounds/Aeroelementalist.wav"));
        reflectedMap.put("Zeus", new Sfx("sounds/Zeus.wav"));
        reflectedMap.put("StarBright", new Sfx("sounds/StarBright.wav"));
        reflectedMap.put("TheWorld", new Sfx("sounds/TheWorld.wav"));
        reflectedMap.put("TheWorld_I", new Sfx("sounds/TheWorld_I.wav"));
        reflectedMap.put("Grimnir", new Sfx("sounds/Grimnir.wav"));
        reflectedMap.put("SlashOfOne", new Sfx("sounds/SlashOfOne.wav"));
        reflectedMap.put("SilentRider", new Sfx("sounds/SilentRider.wav"));
        reflectedMap.put("Servant", new Sfx("sounds/Servant.wav"));
        reflectedMap.put("OldSatan", new Sfx("sounds/OldSatan.wav"));
        reflectedMap.put("BellAngle", new Sfx("sounds/BellAngle.wav"));
        reflectedMap.put("Ignorant", new Sfx("sounds/Ignorant.wav"));
        reflectedMap.put("Omniscient", new Sfx("sounds/Omniscient.wav"));
        reflectedMap.put("TheFool", new Sfx("sounds/TheFool.wav"));
        reflectedMap.put("Mother", new Sfx("sounds/Mother.wav"));
        reflectedMap.put("Flame", new Sfx("sounds/Flame.wav"));
        reflectedMap.put("Glass", new Sfx("sounds/Glass.wav"));
        reflectedMap.put("FlameNGlass", new Sfx("sounds/FlameNGlass.wav"));
        reflectedMap.put("FlameNGlassPower", new Sfx("sounds/FlameNGlassPower.wav"));
        reflectedMap.put("LegendaryFighter", new Sfx("sounds/LegendaryFighter.wav"));
        reflectedMap.put("LegendaryFighterPower", new Sfx("sounds/LegendaryFighterPower.wav"));
        reflectedMap.put("LegendaryFighterA", new Sfx("sounds/LegendaryFighterA.wav"));
        reflectedMap.put("Gabriel", new Sfx("sounds/Gabriel.wav"));
        reflectedMap.put("Kuon", new Sfx("sounds/Kuon.wav"));
        reflectedMap.put("KuonA", new Sfx("sounds/KuonA.wav"));
        reflectedMap.put("XCW_Hurt", new Sfx("sounds/XCW_Hurt.wav"));
        reflectedMap.put("Owen", new Sfx("sounds/Owen.wav"));
        reflectedMap.put("Grea", new Sfx("sounds/Grea.wav"));
        reflectedMap.put("Ember", new Sfx("sounds/Ember.wav"));
        reflectedMap.put("Inferno", new Sfx("sounds/Inferno.wav"));
        reflectedMap.put("Anne", new Sfx("sounds/Anne.wav"));
        reflectedMap.put("AnnesSorcery", new Sfx("sounds/AnnesSorcery.wav"));
        reflectedMap.put("Miranda", new Sfx("sounds/Miranda.wav"));
        reflectedMap.put("XCW_Hurt2", new Sfx("sounds/XCW_Hurt2.wav"));
        reflectedMap.put("XCW_Hurt3", new Sfx("sounds/XCW_Hurt3.wav"));
        reflectedMap.put("XCW_Hurt4", new Sfx("sounds/XCW_Hurt4.wav"));
        reflectedMap.put("BlackPinya", new Sfx("sounds/BlackPinya.wav"));
        reflectedMap.put("Maiser", new Sfx("sounds/Maiser.wav"));
        reflectedMap.put("RapidFire", new Sfx("sounds/RapidFire.wav"));
        reflectedMap.put("Belphomet2", new Sfx("sounds/Belphomet2.wav"));
        reflectedMap.put("Belphomet3", new Sfx("sounds/Belphomet3.wav"));
        reflectedMap.put("Belphomet4", new Sfx("sounds/Belphomet4.wav"));
        reflectedMap.put("Belphomet5", new Sfx("sounds/Belphomet5.wav"));
        reflectedMap.put("Belphomet6", new Sfx("sounds/Belphomet6.wav"));
        reflectedMap.put("IC1", new Sfx("sounds/IC1.wav"));
        reflectedMap.put("IC2", new Sfx("sounds/IC2.wav"));
        reflectedMap.put("IC3", new Sfx("sounds/IC3.wav"));
        reflectedMap.put("IC4", new Sfx("sounds/IC4.wav"));
        reflectedMap.put("IC5", new Sfx("sounds/IC5.wav"));
        reflectedMap.put("MagiTrain", new Sfx("sounds/MagiTrain.wav"));
        reflectedMap.put("Arisa_Selected", new Sfx("sounds/Arisa_Selected.wav"));
        reflectedMap.put("Arisa_Hurt", new Sfx("sounds/Arisa_Hurt.wav"));
        reflectedMap.put("Arisa_Hurt2", new Sfx("sounds/Arisa_Hurt2.wav"));
        reflectedMap.put("Arisa_Hurt3", new Sfx("sounds/Arisa_Hurt3.wav"));
        reflectedMap.put("Arisa_Hurt4", new Sfx("sounds/Arisa_Hurt4.wav"));
        reflectedMap.put("Fairy", new Sfx("sounds/Fairy.wav"));
        reflectedMap.put("FairyWhisperer", new Sfx("sounds/FairyWhisperer.wav"));
        reflectedMap.put("SylvanJustice", new Sfx("sounds/SylvanJustice.wav"));
        reflectedMap.put("NaturesGuidance", new Sfx("sounds/NaturesGuidance.wav"));
        reflectedMap.put("WhirlwindRhinoceroach", new Sfx("sounds/WhirlwindRhinoceroach.wav"));
        reflectedMap.put("Rhinoceroach", new Sfx("sounds/Rhinoceroach.wav"));
        reflectedMap.put("WardOfUnkilling", new Sfx("sounds/WardOfUnkilling.wav"));
        reflectedMap.put("OmenOfUnkilling", new Sfx("sounds/OmenOfUnkilling.wav"));
        reflectedMap.put("AriasWhirlwind", new Sfx("sounds/AriasWhirlwind.wav"));
        reflectedMap.put("Bayle", new Sfx("sounds/Bayle.wav"));
        reflectedMap.put("Hero", new Sfx("sounds/Hero.wav"));
        reflectedMap.put("WindFall", new Sfx("sounds/WindFall.wav"));
        reflectedMap.put("ElfGuard", new Sfx("sounds/ElfGuard.wav"));
        reflectedMap.put("Aria", new Sfx("sounds/Aria.wav"));
        reflectedMap.put("GuardOfMachinatree", new Sfx("sounds/GuardOfMachinatree.wav"));
        reflectedMap.put("Damian", new Sfx("sounds/Damian.wav"));
        reflectedMap.put("MachineClaw", new Sfx("sounds/MachineClaw.wav"));
        reflectedMap.put("IronglideElf", new Sfx("sounds/IronglideElf.wav"));
        reflectedMap.put("Cassiopeia", new Sfx("sounds/Cassiopeia.wav"));
        reflectedMap.put("Loxis", new Sfx("sounds/Loxis.wav"));
        reflectedMap.put("LoxisPower1", new Sfx("sounds/LoxisPower1.wav"));
        reflectedMap.put("LoxisPower2", new Sfx("sounds/LoxisPower2.wav"));
        reflectedMap.put("Packing", new Sfx("sounds/Packing.wav"));
        reflectedMap.put("Ladica", new Sfx("sounds/Ladica.wav"));
        reflectedMap.put("TreacherousReversal", new Sfx("sounds/TreacherousReversal.wav"));
        reflectedMap.put("TheHanged1", new Sfx("sounds/TheHanged1.wav"));
        reflectedMap.put("TheHanged2", new Sfx("sounds/TheHanged2.wav"));
        reflectedMap.put("Verdant", new Sfx("sounds/Verdant.wav"));
        reflectedMap.put("WindFairy", new Sfx("sounds/WindFairy.wav"));
        reflectedMap.put("ServantOfUnkilling", new Sfx("sounds/ServantOfUnkilling.wav"));
        reflectedMap.put("MarkOfUnkilling", new Sfx("sounds/MarkOfUnkilling.wav"));
        reflectedMap.put("ZealotOfUnkilling", new Sfx("sounds/ZealotOfUnkilling.wav"));
        reflectedMap.put("VarmintHunter", new Sfx("sounds/VarmintHunter.wav"));
        reflectedMap.put("VarmintHunterPower", new Sfx("sounds/VarmintHunterPower.wav"));
        reflectedMap.put("Beast", new Sfx("sounds/Beast.wav"));
        reflectedMap.put("Beauty", new Sfx("sounds/Beauty.wav"));
        reflectedMap.put("Sekka", new Sfx("sounds/Sekka.wav"));
        reflectedMap.put("ResolveOfSekka", new Sfx("sounds/ResolveOfSekka.wav"));
        reflectedMap.put("ForestFairy", new Sfx("sounds/ForestFairy.wav"));
        reflectedMap.put("ForestFairyPower", new Sfx("sounds/ForestFairyPower.wav"));
        reflectedMap.put("Lisa", new Sfx("sounds/Lisa.wav"));
        reflectedMap.put("GreenWoodGuardian", new Sfx("sounds/GreenWoodGuardian.wav"));
        reflectedMap.put("WoodlandCleaver", new Sfx("sounds/WoodlandCleaver.wav"));
        reflectedMap.put("GreenbrierElf", new Sfx("sounds/GreenbrierElf.wav"));
        reflectedMap.put("Lymaga", new Sfx("sounds/Lymaga.wav"));
        reflectedMap.put("Lymaga_Acc", new Sfx("sounds/Lymaga_Acc.wav"));
        reflectedMap.put("WildwoodMatriarch", new Sfx("sounds/WildwoodMatriarch.wav"));
        reflectedMap.put("Aerin", new Sfx("sounds/Aerin.wav"));
        reflectedMap.put("Metera", new Sfx("sounds/Metera.wav"));
        reflectedMap.put("Rino", new Sfx("sounds/Rino.wav"));
        reflectedMap.put("Rino_UB", new Sfx("sounds/Rino_UB.wav"));
        reflectedMap.put("Kokkoro", new Sfx("sounds/Kokkoro.wav"));
        reflectedMap.put("Kokkoro_UB", new Sfx("sounds/Kokkoro_UB.wav"));
        reflectedMap.put("Amataz", new Sfx("sounds/Amataz.wav"));
        reflectedMap.put("DivineSmithing", new Sfx("sounds/DivineSmithing.wav"));
        reflectedMap.put("Cleft", new Sfx("sounds/Cleft.wav"));
        reflectedMap.put("Lycoris", new Sfx("sounds/Lycoris.wav"));
        reflectedMap.put("RoseQueen", new Sfx("sounds/RoseQueen.wav"));
        reflectedMap.put("PameraPower", new Sfx("sounds/PameraPower.wav"));
        reflectedMap.put("Pamera", new Sfx("sounds/Pamera.wav"));
        reflectedMap.put("Korwa", new Sfx("sounds/Korwa.wav"));
        reflectedMap.put("Fil", new Sfx("sounds/Fil.wav"));
        reflectedMap.put("Megaera_PREP", new Sfx("sounds/Megaera_PREP.wav"));
        reflectedMap.put("Megaera_D1", new Sfx("sounds/Megaera_D1.wav"));
        reflectedMap.put("Megaera_D2", new Sfx("sounds/Megaera_D2.wav"));
        reflectedMap.put("Tisiphone_SLASH", new Sfx("sounds/Tisiphone_SLASH.wav"));
        reflectedMap.put("Tisiphone_D1", new Sfx("sounds/Tisiphone_D1.wav"));
        reflectedMap.put("Tisiphone_D2", new Sfx("sounds/Tisiphone_D2.wav"));
        reflectedMap.put("Vincent_A1", new Sfx("sounds/Vincent_A1.wav"));
        reflectedMap.put("Vincent_A2", new Sfx("sounds/Vincent_A2.wav"));
        reflectedMap.put("NewGrea", new Sfx("sounds/NewGrea.wav"));
        reflectedMap.put("NewEmber", new Sfx("sounds/NewEmber.wav"));
        reflectedMap.put("NewAnne", new Sfx("sounds/NewAnne.wav"));
        reflectedMap.put("ExceedBurst", new Sfx("sounds/ExceedBurst.wav"));
        reflectedMap.put("Alector_PREP", new Sfx("sounds/Alector_PREP.wav"));
        reflectedMap.put("Alector_D1", new Sfx("sounds/Alector_D1.wav"));
        reflectedMap.put("Alector_D2", new Sfx("sounds/Alector_D2.wav"));
        reflectedMap.put("Lelouch_GEASS", new Sfx("sounds/Lelouch_GEASS.wav"));
        reflectedMap.put("Lelouch_Checkmate", new Sfx("sounds/Lelouch_Checkmate.wav"));
        reflectedMap.put("FatalOrder", new Sfx("sounds/FatalOrder.wav"));
        reflectedMap.put("Suzaku_SLASH", new Sfx("sounds/Suzaku_SLASH.wav"));
        reflectedMap.put("Suzaku_BEAM", new Sfx("sounds/Suzaku_BEAM.wav"));
        reflectedMap.put("Suzaku_CURSE", new Sfx("sounds/Suzaku_CURSE.wav"));
        reflectedMap.put("Necro_Selected", new Sfx("sounds/Necro_Selected.wav"));
        reflectedMap.put("Necro_Hurt", new Sfx("sounds/Necro_Hurt.wav"));
        reflectedMap.put("Necro_Hurt2", new Sfx("sounds/Necro_Hurt2.wav"));
        reflectedMap.put("Necro_Hurt3", new Sfx("sounds/Necro_Hurt3.wav"));
        reflectedMap.put("Necro_Hurt4", new Sfx("sounds/Necro_Hurt4.wav"));
        reflectedMap.put("UndyingResentment", new Sfx("sounds/UndyingResentment.wav"));
        reflectedMap.put("Mordecai", new Sfx("sounds/Mordecai.wav"));
        reflectedMap.put("Minthe", new Sfx("sounds/Minthe.wav"));
        reflectedMap.put("Fafnir", new Sfx("sounds/Fafnir.wav"));
        reflectedMap.put("ApostleOfSilence", new Sfx("sounds/ApostleOfSilence.wav"));
        reflectedMap.put("DiscipleOfSilence", new Sfx("sounds/DiscipleOfSilence.wav"));
        reflectedMap.put("Litch", new Sfx("sounds/Litch.wav"));
        reflectedMap.put("Ceres", new Sfx("sounds/Ceres.wav"));
        reflectedMap.put("EternalVow", new Sfx("sounds/EternalVow.wav"));
        reflectedMap.put("Hector", new Sfx("sounds/Hector.wav"));
        reflectedMap.put("Lubelle", new Sfx("sounds/Lubelle.wav"));
        reflectedMap.put("LubellePower", new Sfx("sounds/LubellePower.wav"));
        reflectedMap.put("Thoth", new Sfx("sounds/Thoth.wav"));
        reflectedMap.put("GhostRider", new Sfx("sounds/GhostRider.wav"));
        reflectedMap.put("Ceridwen", new Sfx("sounds/Ceridwen.wav"));
        reflectedMap.put("TheLovers", new Sfx("sounds/TheLovers.wav"));
        reflectedMap.put("TheLoversEH", new Sfx("sounds/TheLoversEH.wav"));
        reflectedMap.put("SpiritCurator", new Sfx("sounds/SpiritCurator.wav"));
        reflectedMap.put("ForbiddenArt", new Sfx("sounds/ForbiddenArt.wav"));
        reflectedMap.put("Nicola", new Sfx("sounds/Nicola.wav"));
        reflectedMap.put("Chris", new Sfx("sounds/Chris.wav"));
        reflectedMap.put("SoulStrike", new Sfx("sounds/SoulStrike.wav"));
        reflectedMap.put("Kagero", new Sfx("sounds/Kagero.wav"));
        reflectedMap.put("WightKing", new Sfx("sounds/WightKing.wav"));
        reflectedMap.put("Wight", new Sfx("sounds/Wight.wav"));
        reflectedMap.put("ImmortalThane", new Sfx("sounds/ImmortalThane.wav"));
        reflectedMap.put("NecroAssassin", new Sfx("sounds/NecroAssassin.wav"));
        reflectedMap.put("Nephthys", new Sfx("sounds/Nephthys.wav"));
        reflectedMap.put("CarnivalNecromancer", new Sfx("sounds/CarnivalNecromancer.wav"));
        reflectedMap.put("CarnivalNecromancer_EH", new Sfx("sounds/CarnivalNecromancer_EH.wav"));
        reflectedMap.put("CarnivalNecromancerPower", new Sfx("sounds/CarnivalNecromancerPower.wav"));
        reflectedMap.put("DeadMetalStar", new Sfx("sounds/DeadMetalStar.wav"));
        reflectedMap.put("DeadMetalStar_Acc", new Sfx("sounds/DeadMetalStar_Acc.wav"));
        reflectedMap.put("BoneDominator", new Sfx("sounds/BoneDominator.wav"));
        reflectedMap.put("BoneDominator_Acc", new Sfx("sounds/BoneDominator_Acc.wav"));
        reflectedMap.put("LadyGray", new Sfx("sounds/LadyGray.wav"));
        reflectedMap.put("IrongearCorpsman", new Sfx("sounds/IrongearCorpsman.wav"));
        reflectedMap.put("ForbiddenAndBalance", new Sfx("sounds/ForbiddenAndBalance.wav"));
        reflectedMap.put("Arcus", new Sfx("sounds/Arcus.wav"));
        reflectedMap.put("Manmaru1", new Sfx("sounds/Manmaru1.wav"));
        reflectedMap.put("GenerateNine", new Sfx("sounds/GenerateNine.wav"));
        reflectedMap.put("GenerateNine_EH", new Sfx("sounds/GenerateNine_EH.wav"));
        reflectedMap.put("Aenea", new Sfx("sounds/Aenea.wav"));
        reflectedMap.put("AeneaPower", new Sfx("sounds/AeneaPower.wav"));
        reflectedMap.put("OmenOfSilence", new Sfx("sounds/OmenOfSilence.wav"));
        reflectedMap.put("Gremory", new Sfx("sounds/Gremory.wav"));
        reflectedMap.put("Aisha", new Sfx("sounds/Aisha.wav"));
        reflectedMap.put("Hades", new Sfx("sounds/Hades.wav"));
        reflectedMap.put("Hades_Acc", new Sfx("sounds/Hades_Acc.wav"));
        reflectedMap.put("Mimi", new Sfx("sounds/Mimi.wav"));
        reflectedMap.put("Koko", new Sfx("sounds/Koko.wav"));
        reflectedMap.put("Cerberus", new Sfx("sounds/Cerberus.wav"));
        reflectedMap.put("AttendentOfNight", new Sfx("sounds/AttendentOfNight.wav"));
        reflectedMap.put("Orthrus", new Sfx("sounds/Orthrus.wav"));
        reflectedMap.put("Miyako", new Sfx("sounds/Miyako.wav"));
        reflectedMap.put("Miyako_UB", new Sfx("sounds/Miyako_UB.wav"));
        reflectedMap.put("Pudding", new Sfx("sounds/Pudding.wav"));
        reflectedMap.put("LunaGame", new Sfx("sounds/LunaGame.wav"));
        reflectedMap.put("AeneaFriendship", new Sfx("sounds/AeneaFriendship.wav"));
        reflectedMap.put("FriendsForever", new Sfx("sounds/FriendsForever.wav"));
        reflectedMap.put("SonataOfSilence", new Sfx("sounds/SonataOfSilence.wav"));
        reflectedMap.put("SoulTaker", new Sfx("sounds/SoulTaker.wav"));
        reflectedMap.put("Ginsetsu", new Sfx("sounds/Ginsetsu.wav"));
        reflectedMap.put("Ferry", new Sfx("sounds/Ferry.wav"));
        reflectedMap.put("StormArrow", new Sfx("sounds/StormArrow.wav"));
        reflectedMap.put("GaleArrow", new Sfx("sounds/GaleArrow.wav"));
        reflectedMap.put("ArisaArrow", new Sfx("sounds/ArisaArrow.wav"));
        reflectedMap.put("ArrowPower", new Sfx("sounds/ArrowPower.wav"));
        reflectedMap.put("ArisaPower", new Sfx("sounds/ArisaPower.wav"));
        reflectedMap.put("Arisa", new Sfx("sounds/Arisa.wav"));
        reflectedMap.put("NecroTemptation", new Sfx("sounds/NecroTemptation.wav"));
        reflectedMap.put("LunaDoll", new Sfx("sounds/LunaDoll.wav"));
        reflectedMap.put("Luna", new Sfx("sounds/Luna.wav"));
        reflectedMap.put("UltimateMagic", new Sfx("sounds/UltimateMagic.wav"));
        reflectedMap.put("UnionMagic", new Sfx("sounds/UnionMagic.wav"));
        reflectedMap.put("Isabelle", new Sfx("sounds/Isabelle.wav"));
        reflectedMap.put("Vampire_Selected", new Sfx("sounds/Vampire_Selected.wav"));
        reflectedMap.put("Vampire_Hurt", new Sfx("sounds/Vampire_Hurt.wav"));
        reflectedMap.put("Vampire_Hurt2", new Sfx("sounds/Vampire_Hurt2.wav"));
        reflectedMap.put("Vampire_Hurt3", new Sfx("sounds/Vampire_Hurt3.wav"));
        reflectedMap.put("Vampire_Hurt4", new Sfx("sounds/Vampire_Hurt4.wav"));
        reflectedMap.put("DarkGeneral", new Sfx("sounds/DarkGeneral.wav"));
        reflectedMap.put("SpiderWebImp", new Sfx("sounds/SpiderWebImp.wav"));
        reflectedMap.put("DemonicAssault", new Sfx("sounds/DemonicAssault.wav"));
        reflectedMap.put("HellSpearWarrior", new Sfx("sounds/HellSpearWarrior.wav"));
        reflectedMap.put("MoonriseWerewolf", new Sfx("sounds/MoonriseWerewolf.wav"));
        reflectedMap.put("MoonriseWerewolf_EH", new Sfx("sounds/MoonriseWerewolf_EH.wav"));
        reflectedMap.put("UriasRevelry", new Sfx("sounds/UriasRevelry.wav"));
        reflectedMap.put("MonoResolve", new Sfx("sounds/MonoResolve.wav"));
        reflectedMap.put("GarnetWaltz", new Sfx("sounds/GarnetWaltz.wav"));
        reflectedMap.put("Shemyaza", new Sfx("sounds/Shemyaza.wav"));
        reflectedMap.put("Belphegor", new Sfx("sounds/Belphegor.wav"));
        reflectedMap.put("SexyVampire", new Sfx("sounds/SexyVampire.wav"));
        reflectedMap.put("SadisticDemon", new Sfx("sounds/SadisticDemon.wav"));
        reflectedMap.put("SadisticDemonPower", new Sfx("sounds/SadisticDemonPower.wav"));
        reflectedMap.put("SadisticHeal", new Sfx("sounds/SadisticHeal.wav"));
        reflectedMap.put("SadisticDamage", new Sfx("sounds/SadisticDamage.wav"));
        reflectedMap.put("ApostleOfLust", new Sfx("sounds/ApostleOfLust.wav"));
        reflectedMap.put("Baal", new Sfx("sounds/Baal.wav"));
        reflectedMap.put("BearBerserk", new Sfx("sounds/BearBerserk.wav"));
        reflectedMap.put("BeastEmpress", new Sfx("sounds/BeastEmpress.wav"));
        reflectedMap.put("WingsOfLust", new Sfx("sounds/WingsOfLust.wav"));
        reflectedMap.put("Emeralda", new Sfx("sounds/Emeralda.wav"));
        reflectedMap.put("Azazel", new Sfx("sounds/Azazel.wav"));
        reflectedMap.put("Yurius", new Sfx("sounds/Yurius.wav"));
        reflectedMap.put("YuriusPower", new Sfx("sounds/YuriusPower.wav"));
        reflectedMap.put("MechashotDevil", new Sfx("sounds/MechashotDevil.wav"));
        reflectedMap.put("MechaforgeDevil", new Sfx("sounds/MechaforgeDevil.wav"));
        reflectedMap.put("Mono", new Sfx("sounds/Mono.wav"));
        reflectedMap.put("Mono_Unlock", new Sfx("sounds/Mono_Unlock.wav"));
        reflectedMap.put("FirstOne", new Sfx("sounds/FirstOne.wav"));
        reflectedMap.put("Slayn", new Sfx("sounds/Slayn.wav"));
        reflectedMap.put("Neun", new Sfx("sounds/Neun.wav"));
        reflectedMap.put("NeunPower", new Sfx("sounds/NeunPower.wav"));
        reflectedMap.put("Aluzard", new Sfx("sounds/Aluzard.wav"));
        reflectedMap.put("BloodArts", new Sfx("sounds/BloodArts.wav"));
        reflectedMap.put("RageCommander", new Sfx("sounds/RageCommander.wav"));
        reflectedMap.put("Flauros", new Sfx("sounds/Flauros.wav"));
        reflectedMap.put("DevilOfVengeance", new Sfx("sounds/DevilOfVengeance.wav"));
        reflectedMap.put("OmenOfLust", new Sfx("sounds/OmenOfLust.wav"));
        reflectedMap.put("OmenOfLustPower", new Sfx("sounds/OmenOfLustPower.wav"));
        reflectedMap.put("ServantOfLust", new Sfx("sounds/ServantOfLust.wav"));
        reflectedMap.put("TheBerserker", new Sfx("sounds/TheBerserker.wav"));
        reflectedMap.put("TheBerserker2", new Sfx("sounds/TheBerserker2.wav"));
        reflectedMap.put("Urias", new Sfx("sounds/Urias.wav"));
        reflectedMap.put("TerrorNight", new Sfx("sounds/TerrorNight.wav"));
        reflectedMap.put("NightmareTime", new Sfx("sounds/NightmareTime.wav"));
        reflectedMap.put("BloodyNail", new Sfx("sounds/BloodyNail.wav"));
        reflectedMap.put("DreadAura", new Sfx("sounds/DreadAura.wav"));
        reflectedMap.put("Veight", new Sfx("sounds/Veight.wav"));
        reflectedMap.put("Vanpi", new Sfx("sounds/Vanpi.wav"));
        reflectedMap.put("Vanpi1", new Sfx("sounds/Vanpi1.wav"));
        reflectedMap.put("Vanpi2", new Sfx("sounds/Vanpi2.wav"));
        reflectedMap.put("Volteo", new Sfx("sounds/Volteo.wav"));
        reflectedMap.put("ShowdownDemon", new Sfx("sounds/ShowdownDemon.wav"));
        reflectedMap.put("ShadowDevil", new Sfx("sounds/ShadowDevil.wav"));
        reflectedMap.put("MaskedMayhem", new Sfx("sounds/MaskedMayhem.wav"));
        reflectedMap.put("Ayre", new Sfx("sounds/Ayre.wav"));
        reflectedMap.put("Lucius", new Sfx("sounds/Lucius.wav"));
        reflectedMap.put("Lucius_EH", new Sfx("sounds/Lucius_EH.wav"));
        reflectedMap.put("ArkDaemon", new Sfx("sounds/ArkDaemon.wav"));
        reflectedMap.put("Archdemon", new Sfx("sounds/Archdemon.wav"));
        reflectedMap.put("Archdemon_Acc", new Sfx("sounds/Archdemon_Acc.wav"));
        reflectedMap.put("ArchdemonPower", new Sfx("sounds/ArchdemonPower.wav"));
        reflectedMap.put("Lilim", new Sfx("sounds/Lilim.wav"));
        reflectedMap.put("BelphometCard", new Sfx("sounds/BelphometCard.wav"));
        reflectedMap.put("AlectorCard", new Sfx("sounds/AlectorCard.wav"));
        reflectedMap.put("TisiphoneCard", new Sfx("sounds/TisiphoneCard.wav"));
        reflectedMap.put("MegaeraCard", new Sfx("sounds/MegaeraCard.wav"));
        reflectedMap.put("UltimateCreator", new Sfx("sounds/UltimateCreator.wav"));
        reflectedMap.put("NeoTisiphone", new Sfx("sounds/NeoTisiphone.wav"));
        reflectedMap.put("NeoAlector", new Sfx("sounds/NeoAlector.wav"));
        reflectedMap.put("NeoMegaera", new Sfx("sounds/NeoMegaera.wav"));
        reflectedMap.put("CrimsonWar", new Sfx("sounds/CrimsonWar.wav"));
        reflectedMap.put("DimensionalWitch2", new Sfx("sounds/DimensionalWitch2.wav"));
        reflectedMap.put("Runie2", new Sfx("sounds/Runie2.wav"));
        reflectedMap.put("ProphecyOfBoons", new Sfx("sounds/ProphecyOfBoons.wav"));
        reflectedMap.put("ProphecyOfDoom", new Sfx("sounds/ProphecyOfDoom.wav"));
        reflectedMap.put("LadicaEmbrace", new Sfx("sounds/LadicaEmbrace.wav"));
        reflectedMap.put("Ladica2", new Sfx("sounds/Ladica2.wav"));
        reflectedMap.put("EternalPotion", new Sfx("sounds/EternalPotion.wav"));
        reflectedMap.put("InstantPotion", new Sfx("sounds/InstantPotion.wav"));
        reflectedMap.put("Ceridwen2", new Sfx("sounds/Ceridwen2.wav"));
        reflectedMap.put("ImmoralDesire", new Sfx("sounds/ImmoralDesire.wav"));
        reflectedMap.put("Ceres2", new Sfx("sounds/Ceres2.wav"));
        reflectedMap.put("Kyouka", new Sfx("sounds/Kyouka.wav"));
        reflectedMap.put("Kyouka_UB", new Sfx("sounds/Kyouka_UB.wav"));
        reflectedMap.put("Irya_UB", new Sfx("sounds/Irya_UB.wav"));
        reflectedMap.put("Irya", new Sfx("sounds/Irya.wav"));
        reflectedMap.put("FaustPower2", new Sfx("sounds/FaustPower2.wav"));
        reflectedMap.put("FaustPower3", new Sfx("sounds/FaustPower3.wav"));
        reflectedMap.put("Faust2", new Sfx("sounds/Faust2.wav"));
        reflectedMap.put("Nicola2", new Sfx("sounds/Nicola2.wav"));
        reflectedMap.put("Nicola3", new Sfx("sounds/Nicola3.wav"));
        reflectedMap.put("Mono2", new Sfx("sounds/Mono2.wav"));
        reflectedMap.put("MonoPower", new Sfx("sounds/MonoPower.wav"));
        reflectedMap.put("Tetra3", new Sfx("sounds/Tetra3.wav"));
        reflectedMap.put("Tetra2", new Sfx("sounds/Tetra2.wav"));
        reflectedMap.put("Resonance", new Sfx("sounds/Resonance.wav"));
        reflectedMap.put("Nemesis_Selected", new Sfx("sounds/Nemesis_Selected.wav"));
        reflectedMap.put("Nemesis_Hurt", new Sfx("sounds/Nemesis_Hurt.wav"));
        reflectedMap.put("Nemesis_Hurt2", new Sfx("sounds/Nemesis_Hurt2.wav"));
        reflectedMap.put("Nemesis_Hurt3", new Sfx("sounds/Nemesis_Hurt3.wav"));
        reflectedMap.put("Nemesis_Hurt4", new Sfx("sounds/Nemesis_Hurt4.wav"));
        reflectedMap.put("Icarus", new Sfx("sounds/Icarus.wav"));
        reflectedMap.put("RoboticUser", new Sfx("sounds/RoboticUser.wav"));
        reflectedMap.put("DeusExMachina", new Sfx("sounds/DeusExMachina.wav"));
        reflectedMap.put("NewOrder", new Sfx("sounds/NewOrder.wav"));
        reflectedMap.put("Tolerance", new Sfx("sounds/Tolerance.wav"));
        reflectedMap.put("Enforcer", new Sfx("sounds/Enforcer.wav"));
        reflectedMap.put("MegaEnforcer", new Sfx("sounds/MegaEnforcer.wav"));
        reflectedMap.put("MagnaGiant", new Sfx("sounds/MagnaGiant.wav"));
        reflectedMap.put("MagnaGiant_Acc", new Sfx("sounds/MagnaGiant_Acc.wav"));
        reflectedMap.put("MagnaZero", new Sfx("sounds/MagnaZero.wav"));
        reflectedMap.put("Yuwan", new Sfx("sounds/Yuwan.wav"));
        reflectedMap.put("YuwanPower", new Sfx("sounds/YuwanPower.wav"));
        reflectedMap.put("Paracelsus", new Sfx("sounds/Paracelsus.wav"));
        reflectedMap.put("InfinityPuppeteer", new Sfx("sounds/InfinityPuppeteer.wav"));
        reflectedMap.put("OmenOfDestruction", new Sfx("sounds/OmenOfDestruction.wav"));
        reflectedMap.put("WhiteArtifact", new Sfx("sounds/WhiteArtifact.wav"));
        reflectedMap.put("BlackArtifact", new Sfx("sounds/BlackArtifact.wav"));
        reflectedMap.put("Licht", new Sfx("sounds/Licht.wav"));
        reflectedMap.put("LichtPower", new Sfx("sounds/LichtPower.wav"));
        reflectedMap.put("IronStaffMechanic", new Sfx("sounds/IronStaffMechanic.wav"));
        reflectedMap.put("YuwanFury", new Sfx("sounds/YuwanFury.wav"));
        reflectedMap.put("RebelAgainstFate", new Sfx("sounds/RebelAgainstFate.wav"));
        reflectedMap.put("PurgationBlade", new Sfx("sounds/PurgationBlade.wav"));
        reflectedMap.put("Maisha", new Sfx("sounds/Maisha.wav"));
        reflectedMap.put("DeviceTuner", new Sfx("sounds/DeviceTuner.wav"));
        reflectedMap.put("ApostleOfDestruction", new Sfx("sounds/ApostleOfDestruction.wav"));
        reflectedMap.put("DiscipleOfDestruction", new Sfx("sounds/DiscipleOfDestruction.wav"));
        reflectedMap.put("StringMaster", new Sfx("sounds/StringMaster.wav"));
        reflectedMap.put("StringMasterPower", new Sfx("sounds/StringMasterPower.wav"));
        reflectedMap.put("KnowerOfHistory", new Sfx("sounds/KnowerOfHistory.wav"));
        reflectedMap.put("GadgetUser", new Sfx("sounds/GadgetUser.wav"));
        reflectedMap.put("Karula", new Sfx("sounds/Karula.wav"));
        reflectedMap.put("KarulaPower", new Sfx("sounds/KarulaPower.wav"));
        reflectedMap.put("Orchid1", new Sfx("sounds/Orchid1.wav"));
        reflectedMap.put("Orchid1Effect", new Sfx("sounds/Orchid1Effect.wav"));
        reflectedMap.put("Orchid2", new Sfx("sounds/Orchid2.wav"));
        reflectedMap.put("Orchid", new Sfx("sounds/Orchid.wav"));
        reflectedMap.put("OrchidNeo", new Sfx("sounds/OrchidNeo.wav"));
        reflectedMap.put("UnnamedDetermination", new Sfx("sounds/UnnamedDetermination.wav"));
        reflectedMap.put("Noa", new Sfx("sounds/Noa.wav"));
        reflectedMap.put("MindDivider", new Sfx("sounds/MindDivider.wav"));
        reflectedMap.put("Ines", new Sfx("sounds/Ines.wav"));
        reflectedMap.put("InesPower", new Sfx("sounds/InesPower.wav"));
        reflectedMap.put("DollsOwner", new Sfx("sounds/DollsOwner.wav"));
        reflectedMap.put("DestructionRefrain", new Sfx("sounds/DestructionRefrain.wav"));
        reflectedMap.put("Modest", new Sfx("sounds/Modest.wav"));
        reflectedMap.put("Ralmia", new Sfx("sounds/Ralmia.wav"));
        reflectedMap.put("OmniscientKaiser", new Sfx("sounds/OmniscientKaiser.wav"));
        reflectedMap.put("OmniscientKaiser_Acc", new Sfx("sounds/OmniscientKaiser_Acc.wav"));
        reflectedMap.put("Spine", new Sfx("sounds/Spine.wav"));
        reflectedMap.put("Spine2", new Sfx("sounds/Spine2.wav"));
        reflectedMap.put("SpinePower", new Sfx("sounds/SpinePower.wav"));
        reflectedMap.put("Spine2_Acc", new Sfx("sounds/Spine2_Acc.wav"));
        reflectedMap.put("Miriam", new Sfx("sounds/Miriam.wav"));
        reflectedMap.put("MiriamPower", new Sfx("sounds/MiriamPower.wav"));
        reflectedMap.put("Miriam_Acc", new Sfx("sounds/Miriam_Acc.wav"));
        reflectedMap.put("CannonHermitCrab", new Sfx("sounds/CannonHermitCrab.wav"));
        reflectedMap.put("ServantOfDarkness", new Sfx("sounds/ServantOfDarkness.wav"));
        reflectedMap.put("Royal_Hurt", new Sfx("sounds/Royal_Hurt.wav"));
        reflectedMap.put("Royal_Hurt1", new Sfx("sounds/Royal_Hurt1.wav"));
        reflectedMap.put("Royal_Hurt2", new Sfx("sounds/Royal_Hurt2.wav"));
        reflectedMap.put("Royal_Hurt3", new Sfx("sounds/Royal_Hurt3.wav"));
        reflectedMap.put("Royal_Hurt4", new Sfx("sounds/Royal_Hurt4.wav"));
        reflectedMap.put("Royal_Selected", new Sfx("sounds/Royal_Selected.wav"));
        reflectedMap.put("CannonHermitCrabOrb", new Sfx("sounds/CannonHermitCrabOrb.wav"));
        reflectedMap.put("CannonHermitCrabOrb_Atk", new Sfx("sounds/CannonHermitCrabOrb_Atk.wav"));
        reflectedMap.put("FrontguardGeneral", new Sfx("sounds/FrontguardGeneral.wav"));
        reflectedMap.put("FrontguardGeneral_Atk", new Sfx("sounds/FrontguardGeneral_Atk.wav"));
        reflectedMap.put("HeavyKnight", new Sfx("sounds/HeavyKnight.wav"));
        reflectedMap.put("HeavyKnight_Atk", new Sfx("sounds/HeavyKnight_Atk.wav"));
        reflectedMap.put("Knight", new Sfx("sounds/Knight.wav"));
        reflectedMap.put("Knight_Atk", new Sfx("sounds/Knight_Atk.wav"));
        reflectedMap.put("QueenHemera", new Sfx("sounds/QueenHemera.wav"));
        reflectedMap.put("QueenHemera_Atk", new Sfx("sounds/QueenHemera_Atk.wav"));
        reflectedMap.put("QueenMagnus", new Sfx("sounds/QueenMagnus.wav"));
        reflectedMap.put("QueenMagnus_Atk", new Sfx("sounds/QueenMagnus_Atk.wav"));
        reflectedMap.put("Quickblader", new Sfx("sounds/Quickblader.wav"));
        reflectedMap.put("Quickblader_Atk", new Sfx("sounds/Quickblader_Atk.wav"));
        reflectedMap.put("ShieldGuardian", new Sfx("sounds/ShieldGuardian.wav"));
        reflectedMap.put("ShieldGuardian_Atk", new Sfx("sounds/ShieldGuardian_Atk.wav"));
        reflectedMap.put("SteelcladKnight", new Sfx("sounds/SteelcladKnight.wav"));
        reflectedMap.put("SteelcladKnight_Atk", new Sfx("sounds/SteelcladKnight_Atk.wav"));
        reflectedMap.put("Alyaska", new Sfx("sounds/Alyaska.wav"));
        reflectedMap.put("Alyaska_Eff", new Sfx("sounds/Alyaska_Eff.wav"));
        reflectedMap.put("Alyaska_Ev", new Sfx("sounds/Alyaska_Ev.wav"));
        reflectedMap.put("ApostleOfUsurpation", new Sfx("sounds/ApostleOfUsurpation.wav"));
        reflectedMap.put("ApostleOfUsurpation_Pow", new Sfx("sounds/ApostleOfUsurpation_Pow.wav"));
        reflectedMap.put("BayleonsCommand", new Sfx("sounds/BayleonsCommand.wav"));
        reflectedMap.put("BrothersUnited", new Sfx("sounds/BrothersUnited.wav"));
        reflectedMap.put("CatAdmiral", new Sfx("sounds/CatAdmiral.wav"));
        reflectedMap.put("CatAdmiral_Ev", new Sfx("sounds/CatAdmiral_Ev.wav"));
        reflectedMap.put("Charlotta", new Sfx("sounds/Charlotta.wav"));
        reflectedMap.put("Charlotta_Ev", new Sfx("sounds/Charlotta_Ev.wav"));
        reflectedMap.put("DanceOfUsurpation", new Sfx("sounds/DanceOfUsurpation.wav"));
        reflectedMap.put("DiscipleOfUsurpation", new Sfx("sounds/DiscipleOfUsurpation.wav"));
        reflectedMap.put("DiscipleOfUsurpation_Pow", new Sfx("sounds/DiscipleOfUsurpation_Pow.wav"));
        reflectedMap.put("DualbladeKnight", new Sfx("sounds/DualbladeKnight.wav"));
        reflectedMap.put("DualbladeKnight_Ev", new Sfx("sounds/DualbladeKnight_Ev.wav"));
        reflectedMap.put("Eahta", new Sfx("sounds/Eahta.wav"));
        reflectedMap.put("Eahta_SA", new Sfx("sounds/Eahta_SA.wav"));
        reflectedMap.put("Eahta_SSA", new Sfx("sounds/Eahta_SSA.wav"));
        reflectedMap.put("EmpressOfSerenity", new Sfx("sounds/EmpressOfSerenity.wav"));
        reflectedMap.put("EmpressOfSerenity_Eh", new Sfx("sounds/EmpressOfSerenity_Eh.wav"));
        reflectedMap.put("EnragedGeneral", new Sfx("sounds/EnragedGeneral.wav"));
        reflectedMap.put("ErikasSleight", new Sfx("sounds/ErikasSleight.wav"));
        reflectedMap.put("ExterminusWeapon", new Sfx("sounds/ExterminusWeapon.wav"));
        reflectedMap.put("FloralFencer", new Sfx("sounds/FloralFencer.wav"));
        reflectedMap.put("FloralFencer_Ev", new Sfx("sounds/FloralFencer_Ev.wav"));
        reflectedMap.put("FlyingMessengerSquirrel", new Sfx("sounds/FlyingMessengerSquirrel.wav"));
        reflectedMap.put("FortressStrategist", new Sfx("sounds/FortressStrategist.wav"));
        reflectedMap.put("FortressStrategist_Pow", new Sfx("sounds/FortressStrategist_Pow.wav"));
        reflectedMap.put("FrenziedCorpsmaster", new Sfx("sounds/FrenziedCorpsmaster.wav"));
        reflectedMap.put("FrenziedCorpsmaster_Acc", new Sfx("sounds/FrenziedCorpsmaster_Acc.wav"));
        reflectedMap.put("FrontlineInstructor", new Sfx("sounds/FrontlineInstructor.wav"));
        reflectedMap.put("HonorableThief", new Sfx("sounds/HonorableThief.wav"));
        reflectedMap.put("LuminousMage", new Sfx("sounds/LuminousMage.wav"));
        reflectedMap.put("LuxbladeArriet", new Sfx("sounds/LuxbladeArriet.wav"));
        reflectedMap.put("Mars", new Sfx("sounds/Mars.wav"));
        reflectedMap.put("Mars_Pow", new Sfx("sounds/Mars_Pow.wav"));
        reflectedMap.put("Mirin", new Sfx("sounds/Mirin.wav"));
        reflectedMap.put("Mirin_SA", new Sfx("sounds/Mirin_SA.wav"));
        reflectedMap.put("Mirin_SSA", new Sfx("sounds/Mirin_SSA.wav"));
        reflectedMap.put("MistolinaBayleon", new Sfx("sounds/MistolinaBayleon.wav"));
        reflectedMap.put("MistolinasSwordplay", new Sfx("sounds/MistolinasSwordplay.wav"));
        reflectedMap.put("OathlessKnight", new Sfx("sounds/OathlessKnight.wav"));
        reflectedMap.put("Octrice", new Sfx("sounds/Octrice.wav"));
        reflectedMap.put("Octrice_Ev", new Sfx("sounds/Octrice_Ev.wav"));
        reflectedMap.put("PrudentGeneral", new Sfx("sounds/PrudentGeneral.wav"));
        reflectedMap.put("PrudentGeneral_Ev", new Sfx("sounds/PrudentGeneral_Ev.wav"));
        reflectedMap.put("RadicalGunslinger", new Sfx("sounds/RadicalGunslinger.wav"));
        reflectedMap.put("RadicalGunslinger_Ev", new Sfx("sounds/RadicalGunslinger_Ev.wav"));
        reflectedMap.put("SageCommander", new Sfx("sounds/SageCommander.wav"));
        reflectedMap.put("Seofon", new Sfx("sounds/Seofon.wav"));
        reflectedMap.put("Seofon_SA", new Sfx("sounds/Seofon_SA.wav"));
        reflectedMap.put("Seofon_SSA", new Sfx("sounds/Seofon_SSA.wav"));
        reflectedMap.put("Sera", new Sfx("sounds/Sera.wav"));
        reflectedMap.put("Sera_Ev", new Sfx("sounds/Sera_Ev.wav"));
        reflectedMap.put("StrikeproneGuardian", new Sfx("sounds/StrikeproneGuardian.wav"));
        reflectedMap.put("StrokeOfConviction", new Sfx("sounds/StrokeOfConviction.wav"));
        reflectedMap.put("SunnyDayEncounter", new Sfx("sounds/SunnyDayEncounter.wav"));
        reflectedMap.put("UsurpingSpineblade", new Sfx("sounds/UsurpingSpineblade.wav"));
        reflectedMap.put("WarriorWing", new Sfx("sounds/WarriorWing.wav"));
        reflectedMap.put("WarriorWing_Ev", new Sfx("sounds/WarriorWing_Ev.wav"));
        reflectedMap.put("WhitePaladin", new Sfx("sounds/WhitePaladin.wav"));

        /*     */
    }

    /*     */
    /*     */
    /*     */
    public void receiveEditRelics() {
        /* 158 */
        BaseMod.addRelicToCustomPool((AbstractRelic) new FriendOfTruth(), Witchcraft.Enums.COLOR_BLUE);
        /* 159 */
        BaseMod.addRelicToCustomPool((AbstractRelic) new Eleanor(), Witchcraft.Enums.COLOR_BLUE);
        /* 160 */
        BaseMod.addRelicToCustomPool((AbstractRelic) new EnchantedLibrary(), Witchcraft.Enums.COLOR_BLUE);
        /* 161 */
        BaseMod.addRelicToCustomPool((AbstractRelic) new EarthEssenceRelic(), Witchcraft.Enums.COLOR_BLUE);
        /* 162 */
        BaseMod.addRelicToCustomPool((AbstractRelic) new shadowverse.relics.Mysteria(), Witchcraft.Enums.COLOR_BLUE);
        /* 164 */
        BaseMod.addRelicToCustomPool((AbstractRelic) new Offensive(), Witchcraft.Enums.COLOR_BLUE);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Genius(), Witchcraft.Enums.COLOR_BLUE);
        BaseMod.addRelic((AbstractRelic) new BlackPinya(), RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic) new GoldPinya(), RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic) new KMR1(), RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic) new KMR2(), RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic) new MissLethal(), RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic) new GeassRelic(), RelicType.SHARED);
        BaseMod.addRelic((AbstractRelic) new NeutralBook(), RelicType.SHARED);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Offensive2(), Elf.Enums.COLOR_GREEN);
        BaseMod.addRelicToCustomPool((AbstractRelic) new SixMark(), Elf.Enums.COLOR_GREEN);
        BaseMod.addRelicToCustomPool((AbstractRelic) new ArisaBOSS(), Elf.Enums.COLOR_GREEN);
        BaseMod.addRelicToCustomPool((AbstractRelic) new NiouKyuu(), Elf.Enums.COLOR_GREEN);
        BaseMod.addRelicToCustomPool((AbstractRelic) new LymagaWeapon(), Elf.Enums.COLOR_GREEN);
        BaseMod.addRelicToCustomPool((AbstractRelic) new GlassOfIC(), Elf.Enums.COLOR_GREEN);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Mimori(), Elf.Enums.COLOR_GREEN);
        BaseMod.addRelicToCustomPool((AbstractRelic) new KyoukaBOSS(), Witchcraft.Enums.COLOR_BLUE);
        BaseMod.addRelicToCustomPool((AbstractRelic) new KuonWeapon(), Witchcraft.Enums.COLOR_BLUE);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Offensive3(), Necromancer.Enums.COLOR_PURPLE);
        BaseMod.addRelicToCustomPool((AbstractRelic) new BurialGround(), Necromancer.Enums.COLOR_PURPLE);
        BaseMod.addRelicToCustomPool((AbstractRelic) new LunaBOSS(), Necromancer.Enums.COLOR_PURPLE);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Todoroki(), Necromancer.Enums.COLOR_PURPLE);
        BaseMod.addRelicToCustomPool((AbstractRelic) new TailRelic(), Necromancer.Enums.COLOR_PURPLE);
        BaseMod.addRelicToCustomPool((AbstractRelic) new InfernalCrown(), Necromancer.Enums.COLOR_PURPLE);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Alice(), Necromancer.Enums.COLOR_PURPLE);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Offensive4(), Vampire.Enums.COLOR_SCARLET);
        BaseMod.addRelicToCustomPool((AbstractRelic) new IriaBOSS(), Vampire.Enums.COLOR_SCARLET);
        BaseMod.addRelicToCustomPool((AbstractRelic) new LustRelic(), Vampire.Enums.COLOR_SCARLET);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Pendant(), Vampire.Enums.COLOR_SCARLET);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Lusia(), Vampire.Enums.COLOR_SCARLET);
        BaseMod.addRelicToCustomPool((AbstractRelic) new BerserkRelic(), Vampire.Enums.COLOR_SCARLET);
        BaseMod.addRelicToCustomPool((AbstractRelic) new LonePromise(), Vampire.Enums.COLOR_SCARLET);
        BaseMod.addRelicToCustomPool((AbstractRelic) new RevelrySeed(), Vampire.Enums.COLOR_SCARLET);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Offensive5(), Nemesis.Enums.COLOR_SKY);
        BaseMod.addRelicToCustomPool((AbstractRelic) new NemesisBOSS(), Nemesis.Enums.COLOR_SKY);
        BaseMod.addRelicToCustomPool((AbstractRelic) new Offensive6(), Royal.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool((AbstractRelic) new OliviasBlessing(), Royal.Enums.COLOR_YELLOW);
        BaseMod.addRelicToCustomPool((AbstractRelic) new WindGodsBlessing(), Royal.Enums.COLOR_YELLOW);
        /*     */
    }

    /*     */
    /*     */   class Keywords
            /*     */ {
        /*     */ Keyword[] keywords;
        /*     */
    }

    /*     */
    /*     */
    public void receiveEditStrings() {
        /* 173 */
        logger.info("Adding Strings");
        /*     */
        /* 175 */
        String cardsPath = "localization/cards-" + Settings.language + ".json";
        /* 176 */
        String characterPath = "localization/character-" + Settings.language + ".json";
        /* 177 */
        String powerPath = "localization/powers-" + Settings.language + ".json";
        /* 178 */
        String relicsPath = "localization/relics-" + Settings.language + ".json";
        String uiPath = "localization/UIStrings-" + Settings.language + ".json";
        String eventPath = "localization/events-" + Settings.language + ".json";
        String monsterPath = "localization/monsters-" + Settings.language + ".json";
        String potionPath = "localization/potions-" + Settings.language + ".json";
        String orbPath = "localization/orbs-" + Settings.language + ".json";
        /* 179 */
        BaseMod.loadCustomStringsFile(CardStrings.class, cardsPath);
        /* 180 */
        BaseMod.loadCustomStringsFile(CharacterStrings.class, characterPath);
        /* 181 */
        BaseMod.loadCustomStringsFile(PowerStrings.class, powerPath);
        /* 182 */
        BaseMod.loadCustomStringsFile(RelicStrings.class, relicsPath);
        BaseMod.loadCustomStringsFile(UIStrings.class, uiPath);
        BaseMod.loadCustomStringsFile(EventStrings.class, eventPath);
        BaseMod.loadCustomStringsFile(MonsterStrings.class, monsterPath);
        BaseMod.loadCustomStringsFile(PotionStrings.class, potionPath);
        BaseMod.loadCustomStringsFile(OrbStrings.class, orbPath);
        /* 183 */
        logger.info("Success");
        /*     */
    }

    /*     */
    /*     */
    /*     */
    public void receiveEditCards() {
        /* 188 */
        logger.info("Adding cards");
        /* 189 */
        BaseMod.addCard((AbstractCard) new Strike_W());
        /* 190 */
        BaseMod.addCard((AbstractCard) new Defend_W());
        /* 191 */
        BaseMod.addCard((AbstractCard) new Insight());
        /* 192 */
        BaseMod.addCard((AbstractCard) new FieryEmbrace());
        /* 193 */
        BaseMod.addCard((AbstractCard) new FatesHand());
        /* 194 */
        BaseMod.addCard((AbstractCard) new Lou());
        /* 195 */
        BaseMod.addCard((AbstractCard) new Ogler());
        /* 196 */
        BaseMod.addCard((AbstractCard) new ZealotOfTruth());
        /* 197 */
        BaseMod.addCard((AbstractCard) new MagicMissile());
        /* 198 */
        BaseMod.addCard((AbstractCard) new DimensionShift());
        /* 199 */
        BaseMod.addCard((AbstractCard) new MagicOwl());
        /* 200 */
        BaseMod.addCard((AbstractCard) new FireChain());
        /* 201 */
        BaseMod.addCard((AbstractCard) new GigantChimera());
        /* 202 */
        BaseMod.addCard((AbstractCard) new OmenOfTruth());
        /* 203 */
        BaseMod.addCard((AbstractCard) new DimensionalWitch());
        /* 204 */
        BaseMod.addCard((AbstractCard) new MysticSeeker());
        /* 205 */
        BaseMod.addCard((AbstractCard) new EdictOfTruth());
        /* 206 */
        BaseMod.addCard((AbstractCard) new TruthsAdjudication());
        /* 207 */
        BaseMod.addCard((AbstractCard) new MysterianKnowledge());
        /* 208 */
        BaseMod.addCard((AbstractCard) new MysterianCircle());
        /* 209 */
        BaseMod.addCard((AbstractCard) new MysterianMissile());
        /* 210 */
        BaseMod.addCard((AbstractCard) new CommenceExperiment());
        /* 211 */
        BaseMod.addCard((AbstractCard) new MasterMageLevi());
        /* 212 */
        BaseMod.addCard((AbstractCard) new Concentration());
        /* 213 */
        BaseMod.addCard((AbstractCard) new PiousInstructor());
        /* 214 */
        BaseMod.addCard((AbstractCard) new ConjureGuardian());
        /* 215 */
        BaseMod.addCard((AbstractCard) new GolemAssault());
        /* 216 */
        BaseMod.addCard((AbstractCard) new Clarke());
        /* 217 */
        BaseMod.addCard((AbstractCard) new Faust());
        /* 218 */
        BaseMod.addCard((AbstractCard) new OrichalcumGolem());
        /* 219 */
        BaseMod.addCard((AbstractCard) new DwarfAlchemist());
        /* 220 */
        BaseMod.addCard((AbstractCard) new WitchsCauldron());
        /* 221 */
        BaseMod.addCard((AbstractCard) new Magisa());
        /* 222 */
        BaseMod.addCard((AbstractCard) new StaffOfWhirlwinds());
        /* 223 */
        BaseMod.addCard((AbstractCard) new ConjureTwosome());
        /* 224 */
        BaseMod.addCard((AbstractCard) new Petrification());
        /* 225 */
        BaseMod.addCard((AbstractCard) new AbsoluteZeroBlade());
        /* 226 */
        BaseMod.addCard((AbstractCard) new Chaos());
        /* 227 */
        BaseMod.addCard((AbstractCard) new ChainLightning());
        /* 228 */
        BaseMod.addCard((AbstractCard) new Runie());
        /* 229 */
        BaseMod.addCard((AbstractCard) new Telescope());
        /* 230 */
        BaseMod.addCard((AbstractCard) new ForbiddenDarkMage());
        /* 231 */
        BaseMod.addCard((AbstractCard) new ProductMachine());
        /* 232 */
        BaseMod.addCard((AbstractCard) new RepairMode());
        /* 233 */
        BaseMod.addCard((AbstractCard) new JetBroomWitch());
        /* 234 */
        BaseMod.addCard((AbstractCard) new TetrasMettle());
        /* 235 */
        BaseMod.addCard((AbstractCard) new IsabellesConjuration());
        /* 236 */
        BaseMod.addCard((AbstractCard) new SorceryInSolidarity());
        /* 237 */
        BaseMod.addCard((AbstractCard) new MechanizedLifeform());
        /* 238 */
        BaseMod.addCard((AbstractCard) new MagitechGolem());
        /* 239 */
        BaseMod.addCard((AbstractCard) new MechabookSorcerer());
        /* 240 */
        BaseMod.addCard((AbstractCard) new SonicFour());
        /* 241 */
        BaseMod.addCard((AbstractCard) new Tetra());
        /* 242 */
        BaseMod.addCard((AbstractCard) new MechastaffSorcerer());
        /* 243 */
        BaseMod.addCard((AbstractCard) new ArsMagna());
        /* 244 */
        BaseMod.addCard((AbstractCard) new Cagliostro());
        /* 245 */
        BaseMod.addCard((AbstractCard) new MadcapConjuration());
        /* 246 */
        BaseMod.addCard((AbstractCard) new Awakened());
        /* 247 */
        BaseMod.addCard((AbstractCard) new Erasmus());
        /* 248 */
        BaseMod.addCard((AbstractCard) new Oz());
        /* 249 */
        BaseMod.addCard((AbstractCard) new GrandSummoning());
        /* 250 */
        BaseMod.addCard((AbstractCard) new Shop());
        /* 251 */
        BaseMod.addCard((AbstractCard) new NaterranGreatTree());
        /* 252 */
        BaseMod.addCard((AbstractCard) new Geoelementist());
        /* 253 */
        BaseMod.addCard((AbstractCard) new Stormelementalist());
        /* 254 */
        BaseMod.addCard((AbstractCard) new Pyromancer());
        /* 255 */
        BaseMod.addCard((AbstractCard) new Elementalmana());
        /* 256 */
        BaseMod.addCard((AbstractCard) new Riley());
        /* 257 */
        BaseMod.addCard((AbstractCard) new ApexElemental());
        /* 258 */
        BaseMod.addCard((AbstractCard) new DualAngle());
        /* 259 */
        BaseMod.addCard((AbstractCard) new Ghios());
        /* 260 */
        BaseMod.addCard((AbstractCard) new OmenOfOne());
        /* 261 */
        BaseMod.addCard((AbstractCard) new Mysteria());
        /* 262 */
        BaseMod.addCard((AbstractCard) new EarthFall());
        /* 263 */
        BaseMod.addCard((AbstractCard) new InfernalSurge());
        /* 264 */
        BaseMod.addCard((AbstractCard) new InfernalGaze());
        /* 265 */
        BaseMod.addCard((AbstractCard) new HeavenFall());
        /* 266 */
        BaseMod.addCard((AbstractCard) new Astaroth());
        /* 267 */
        BaseMod.addCard((AbstractCard) new Scorpion());
        /* 268 */
        BaseMod.addCard((AbstractCard) new Flamelord());
        /* 269 */
        BaseMod.addCard((AbstractCard) new ViciousCommander());
        /* 270 */
        BaseMod.addCard((AbstractCard) new WrathfulIcefiend());
        /* 271 */
        BaseMod.addCard((AbstractCard) new Behemoth());
        /* 272 */
        BaseMod.addCard((AbstractCard) new DemonOfPurgatory());
        /* 273 */
        BaseMod.addCard((AbstractCard) new Desire());
        /* 274 */
        BaseMod.addCard((AbstractCard) new Satan());
        /* 275 */
        BaseMod.addCard((AbstractCard) new EmbodimentOfCocytus());
        BaseMod.addCard((AbstractCard) new JudgmentWord());
        BaseMod.addCard((AbstractCard) new Vincent());
        BaseMod.addCard((AbstractCard) new Machinus());
        BaseMod.addCard((AbstractCard) new GrimoireSorcerer());
        BaseMod.addCard((AbstractCard) new WitchSnap());
        BaseMod.addCard((AbstractCard) new Aeroelementalist());
        BaseMod.addCard((AbstractCard) new Zeus(0));
        BaseMod.addCard((AbstractCard) new StarBright());
        BaseMod.addCard((AbstractCard) new TheWorld());
        BaseMod.addCard((AbstractCard) new TheWorld_I());
        BaseMod.addCard((AbstractCard) new Grimnir());
        BaseMod.addCard((AbstractCard) new SlashOfOne());
        BaseMod.addCard((AbstractCard) new Dis());
        BaseMod.addCard((AbstractCard) new Servant());
        BaseMod.addCard((AbstractCard) new SilentRider());
        BaseMod.addCard((AbstractCard) new OldSatan());
        BaseMod.addCard((AbstractCard) new BellAngle());
        BaseMod.addCard((AbstractCard) new Omniscient());
        BaseMod.addCard((AbstractCard) new Ignorant());
        BaseMod.addCard((AbstractCard) new TheFool());
        BaseMod.addCard((AbstractCard) new Mother());
        BaseMod.addCard((AbstractCard) new Glass());
        BaseMod.addCard((AbstractCard) new FlameNGlass());
        BaseMod.addCard((AbstractCard) new HeavensDoor());
        BaseMod.addCard((AbstractCard) new Technolord());
        BaseMod.addCard((AbstractCard) new LegendaryFighterA());

        BaseMod.addCard((AbstractCard) new Gabriel());
        BaseMod.addCard((AbstractCard) new Kuon());
        BaseMod.addCard((AbstractCard) new PaperShikigami());
        BaseMod.addCard((AbstractCard) new DemonicShikigami());
        BaseMod.addCard((AbstractCard) new CelestialShikigami());
        BaseMod.addCard((AbstractCard) new Anne());
        BaseMod.addCard((AbstractCard) new AnnesSorcery());
        BaseMod.addCard((AbstractCard) new AnnesSummoning());
        BaseMod.addCard((AbstractCard) new Grea());
        BaseMod.addCard((AbstractCard) new Inferno());
        BaseMod.addCard((AbstractCard) new Ember());
        BaseMod.addCard((AbstractCard) new Owen());
        BaseMod.addCard((AbstractCard) new Miranda());
        BaseMod.addCard((AbstractCard) new MysterianRite());
        BaseMod.addCard((AbstractCard) new Horse());
        BaseMod.addCard((AbstractCard) new Jeep());
        BaseMod.addCard((AbstractCard) new Motorbike());
        BaseMod.addCard((AbstractCard) new RapidFire());
        BaseMod.addCard((AbstractCard) new Maiser());
        BaseMod.addCard((AbstractCard) new CrystalBright());
        BaseMod.addCard((AbstractCard) new GemLight());
        BaseMod.addCard((AbstractCard) new BelphometStatus());
        BaseMod.addCard((AbstractCard) new ManaForce());
        BaseMod.addCard((AbstractCard) new Strike_E());
        BaseMod.addCard((AbstractCard) new Defend_E());
        BaseMod.addCard((AbstractCard) new Fairy());
        BaseMod.addCard((AbstractCard) new FairyWhisperer());
        BaseMod.addCard((AbstractCard) new SylvanJustice());
        BaseMod.addCard((AbstractCard) new NaturesGuidance());
        BaseMod.addCard((AbstractCard) new AirboundBarrage());
        BaseMod.addCard((AbstractCard) new WhirlwindRhinoceroach());
        BaseMod.addCard((AbstractCard) new Rhinoceroach());
        BaseMod.addCard((AbstractCard) new ElfSong());
        BaseMod.addCard((AbstractCard) new FairyCircle());
        BaseMod.addCard((AbstractCard) new WoodOfBrambles());
        BaseMod.addCard((AbstractCard) new WardOfUnkilling());
        BaseMod.addCard((AbstractCard) new OmenOfUnkilling());
        BaseMod.addCard((AbstractCard) new AriasWhirlwind());
        BaseMod.addCard((AbstractCard) new Bayle());
        BaseMod.addCard((AbstractCard) new Hero());
        BaseMod.addCard((AbstractCard) new WindFall());
        BaseMod.addCard((AbstractCard) new ElfGuard());
        BaseMod.addCard((AbstractCard) new Aria());
        BaseMod.addCard((AbstractCard) new Flame());
        BaseMod.addCard((AbstractCard) new LegendaryFighter());
        BaseMod.addCard((AbstractCard) new NaterranFuture());
        BaseMod.addCard((AbstractCard) new GuardOfMachinatree());
        BaseMod.addCard((AbstractCard) new RoboticBagworm());
        BaseMod.addCard((AbstractCard) new Damian());
        BaseMod.addCard((AbstractCard) new MachineClaw());
        BaseMod.addCard((AbstractCard) new IrongliderElf());
        BaseMod.addCard((AbstractCard) new Cassiopeia());
        BaseMod.addCard((AbstractCard) new Loxis());
        BaseMod.addCard((AbstractCard) new NaturalMana());
        BaseMod.addCard((AbstractCard) new ScaryTrend());
        BaseMod.addCard((AbstractCard) new Ladica());
        BaseMod.addCard((AbstractCard) new Packing());
        BaseMod.addCard((AbstractCard) new VictoryCard());
        BaseMod.addCard((AbstractCard) new TreacherousReversal());
        BaseMod.addCard((AbstractCard) new TheHanged());
        BaseMod.addCard((AbstractCard) new Verdant());
        BaseMod.addCard((AbstractCard) new WindFairy());
        BaseMod.addCard((AbstractCard) new ServantOfUnkilling());
        BaseMod.addCard((AbstractCard) new MarkOfUnkilling());
        BaseMod.addCard((AbstractCard) new ZealotOfUnkilling());
        BaseMod.addCard((AbstractCard) new VarmintHunter());
        BaseMod.addCard((AbstractCard) new Beast());
        BaseMod.addCard((AbstractCard) new Beauty());
        BaseMod.addCard((AbstractCard) new MiracleOfLove());
        BaseMod.addCard((AbstractCard) new Sekka());
        BaseMod.addCard((AbstractCard) new ResolveOfSekka());
        BaseMod.addCard((AbstractCard) new ForestFairy());
        BaseMod.addCard((AbstractCard) new Lisa());
        BaseMod.addCard((AbstractCard) new NatureCorroded());
        BaseMod.addCard((AbstractCard) new Genshin());
        BaseMod.addCard((AbstractCard) new GreenWoodGuardian());
        BaseMod.addCard((AbstractCard) new CrossCombination());
        BaseMod.addCard((AbstractCard) new GreenbrierElf());
        BaseMod.addCard((AbstractCard) new WoodlandCleaver());
        BaseMod.addCard((AbstractCard) new Lymaga());
        BaseMod.addCard((AbstractCard) new WildwoodMatriarch());
        BaseMod.addCard((AbstractCard) new Aerin());
        BaseMod.addCard((AbstractCard) new Metera());
        BaseMod.addCard((AbstractCard) new BeetleWarrior());
        BaseMod.addCard((AbstractCard) new BlossomTreant());
        BaseMod.addCard((AbstractCard) new Rino(0));
        BaseMod.addCard((AbstractCard) new Kokkoro(0));
        BaseMod.addCard((AbstractCard) new Amataz());
        BaseMod.addCard((AbstractCard) new DivineSmithing());
        BaseMod.addCard((AbstractCard) new Cleft());
        BaseMod.addCard((AbstractCard) new TwinAssault());
        BaseMod.addCard((AbstractCard) new Homecoming());
        BaseMod.addCard((AbstractCard) new PixieParadise());
        BaseMod.addCard((AbstractCard) new Lycoris());
        BaseMod.addCard((AbstractCard) new AvatarOfFruition());
        BaseMod.addCard((AbstractCard) new ThronBurst());
        BaseMod.addCard((AbstractCard) new RoseQueen());
        BaseMod.addCard((AbstractCard) new Pamera());
        BaseMod.addCard((AbstractCard) new SkyDevouring());
        BaseMod.addCard((AbstractCard) new Korwa());
        BaseMod.addCard((AbstractCard) new Fil());
        BaseMod.addCard((AbstractCard) new PrimalGigant());
        BaseMod.addCard((AbstractCard) new NewGrea());
        BaseMod.addCard((AbstractCard) new NewEmber());
        BaseMod.addCard((AbstractCard) new NewAnne());
        BaseMod.addCard((AbstractCard) new ExceedBurst());
        BaseMod.addCard((AbstractCard) new ShikigamiSummoning());
        BaseMod.addCard((AbstractCard) new Geass());
        BaseMod.addCard((AbstractCard) new Strike_N());
        BaseMod.addCard((AbstractCard) new Defend_N());
        BaseMod.addCard((AbstractCard) new UndyingResentment());
        BaseMod.addCard((AbstractCard) new SpartoiSergent());
        BaseMod.addCard((AbstractCard) new Path());
        BaseMod.addCard((AbstractCard) new SoulConversion());
        BaseMod.addCard((AbstractCard) new Mordecai());
        BaseMod.addCard((AbstractCard) new LurchingCorpse());
        BaseMod.addCard((AbstractCard) new DeathTyrant());
        BaseMod.addCard((AbstractCard) new Minthe());
        BaseMod.addCard((AbstractCard) new Fafnir());
        BaseMod.addCard((AbstractCard) new ApostleOfSilence());
        BaseMod.addCard((AbstractCard) new DiscipleOfSilence());
        BaseMod.addCard((AbstractCard) new DeathParty());
        BaseMod.addCard((AbstractCard) new FoulTempest());
        BaseMod.addCard((AbstractCard) new Zombie());
        BaseMod.addCard((AbstractCard) new Litch());
        BaseMod.addCard((AbstractCard) new ImpiousResurrection());
        BaseMod.addCard((AbstractCard) new EternalVow());
        BaseMod.addCard((AbstractCard) new Ceres());
        BaseMod.addCard((AbstractCard) new Hector());
        BaseMod.addCard((AbstractCard) new RevenantRam());
        BaseMod.addCard((AbstractCard) new ReviveMana());
        BaseMod.addCard((AbstractCard) new ZombieDog());
        BaseMod.addCard((AbstractCard) new NecroAnimals());
        BaseMod.addCard((AbstractCard) new Lubelle());
        BaseMod.addCard((AbstractCard) new Thoth());
        BaseMod.addCard((AbstractCard) new TrothCurse());
        BaseMod.addCard((AbstractCard) new GhostRider());
        BaseMod.addCard((AbstractCard) new GloamingTombs());
        BaseMod.addCard((AbstractCard) new DemonicProcession());
        BaseMod.addCard((AbstractCard) new Ceridwen());
        BaseMod.addCard((AbstractCard) new DeathBreath());
        BaseMod.addCard((AbstractCard) new TheLovers());
        BaseMod.addCard((AbstractCard) new SpiritCurator());
        BaseMod.addCard((AbstractCard) new Nicola());
        BaseMod.addCard((AbstractCard) new ForbiddenArt());
        BaseMod.addCard((AbstractCard) new SpartoiSoldier());
        BaseMod.addCard((AbstractCard) new Chris());
        BaseMod.addCard((AbstractCard) new SoulStrike());
        BaseMod.addCard((AbstractCard) new Kagero());
        BaseMod.addCard((AbstractCard) new WightKing());
        BaseMod.addCard((AbstractCard) new Wight());
        BaseMod.addCard((AbstractCard) new ImmortalThane());
        BaseMod.addCard((AbstractCard) new NecroAssassin());
        BaseMod.addCard((AbstractCard) new Nephthys());
        BaseMod.addCard((AbstractCard) new CarnivalNecromancer());
        BaseMod.addCard((AbstractCard) new EverdarkStrix());
        BaseMod.addCard((AbstractCard) new HungrySlash());
        BaseMod.addCard((AbstractCard) new DeadMetalStar());
        BaseMod.addCard((AbstractCard) new Skeleton());
        BaseMod.addCard((AbstractCard) new HinterlandGhoul());
        BaseMod.addCard((AbstractCard) new BoneDominator());
        BaseMod.addCard((AbstractCard) new NecroImpulse());
        BaseMod.addCard((AbstractCard) new BoneChimera());
        BaseMod.addCard((AbstractCard) new LadyGray());
        BaseMod.addCard((AbstractCard) new SowDeathReapLife());
        BaseMod.addCard((AbstractCard) new BoneDrone());
        BaseMod.addCard((AbstractCard) new GigantSkull());
        BaseMod.addCard((AbstractCard) new IrongearCorpsman());
        BaseMod.addCard((AbstractCard) new ForbiddenAndBalance());
        BaseMod.addCard((AbstractCard) new Arcus());
        BaseMod.addCard((AbstractCard) new Manmaru1());
        BaseMod.addCard((AbstractCard) new GenerateNine());
        BaseMod.addCard((AbstractCard) new Aenea());
        BaseMod.addCard((AbstractCard) new Andrealphus());
        BaseMod.addCard((AbstractCard) new OmenOfSilence());
        BaseMod.addCard((AbstractCard) new Gremory());
        BaseMod.addCard((AbstractCard) new Aisha());
        BaseMod.addCard((AbstractCard) new Hades());
        BaseMod.addCard((AbstractCard) new Mimi());
        BaseMod.addCard((AbstractCard) new Koko());
        BaseMod.addCard((AbstractCard) new Cerberus());
        BaseMod.addCard((AbstractCard) new AttendentOfNight());
        BaseMod.addCard((AbstractCard) new Orthrus());
        BaseMod.addCard((AbstractCard) new Pudding());
        BaseMod.addCard((AbstractCard) new Miyako(0));
        BaseMod.addCard((AbstractCard) new LunaGame());
        BaseMod.addCard((AbstractCard) new AeneaFriendship());
        BaseMod.addCard((AbstractCard) new FriendsForever());
        BaseMod.addCard((AbstractCard) new SonataOfSilence());
        BaseMod.addCard((AbstractCard) new SoulTaker());
        BaseMod.addCard((AbstractCard) new OneTailFox());
        BaseMod.addCard((AbstractCard) new Ginsetsu());
        BaseMod.addCard((AbstractCard) new Ferry());
        BaseMod.addCard((AbstractCard) new StormArrow());
        BaseMod.addCard((AbstractCard) new GaleArrow());
        BaseMod.addCard((AbstractCard) new ArisaArrow());
        BaseMod.addCard((AbstractCard) new Arisa());
        BaseMod.addCard((AbstractCard) new NecroTemptation());
        BaseMod.addCard((AbstractCard) new LunaDoll());
        BaseMod.addCard((AbstractCard) new Luna());
        BaseMod.addCard((AbstractCard) new UnionMagic());
        BaseMod.addCard((AbstractCard) new UltimateMagic());
        BaseMod.addCard((AbstractCard) new FireBall());
        BaseMod.addCard((AbstractCard) new Isabelle());
        BaseMod.addCard((AbstractCard) new Strike_V());
        BaseMod.addCard((AbstractCard) new Defend_V());
        BaseMod.addCard((AbstractCard) new DarkGeneral());
        BaseMod.addCard((AbstractCard) new RazoryClaw());
        BaseMod.addCard((AbstractCard) new ForestBat());
        BaseMod.addCard((AbstractCard) new BloodPact());
        BaseMod.addCard((AbstractCard) new BloodGarden());
        BaseMod.addCard((AbstractCard) new BloodWolf());
        BaseMod.addCard((AbstractCard) new SpiderWebImp());
        BaseMod.addCard((AbstractCard) new AntelopeWarrior());
        BaseMod.addCard((AbstractCard) new HellSpearWarrior());
        BaseMod.addCard((AbstractCard) new MoonriseWerewolf());
        BaseMod.addCard((AbstractCard) new BloodtrothEpitaph());
        BaseMod.addCard((AbstractCard) new Revelation());
        BaseMod.addCard((AbstractCard) new UriasRevelry());
        BaseMod.addCard((AbstractCard) new MonoResolve());
        BaseMod.addCard((AbstractCard) new GarnetWaltz());
        BaseMod.addCard((AbstractCard) new Shemyaza());
        BaseMod.addCard((AbstractCard) new DarkfeastBat());
        BaseMod.addCard((AbstractCard) new Belphegor());
        BaseMod.addCard((AbstractCard) new SexyVampire());
        BaseMod.addCard((AbstractCard) new ApostleOfLust());
        BaseMod.addCard((AbstractCard) new DiabolicDrain());
        BaseMod.addCard((AbstractCard) new Baal());
        BaseMod.addCard((AbstractCard) new Ravening());
        BaseMod.addCard((AbstractCard) new LunaticMana());
        BaseMod.addCard((AbstractCard) new SadisticDemon());
        BaseMod.addCard((AbstractCard) new BearBerserk());
        BaseMod.addCard((AbstractCard) new FloodBehemoth());
        BaseMod.addCard((AbstractCard) new BeastEmpress());
        BaseMod.addCard((AbstractCard) new CorruptedBat());
        BaseMod.addCard((AbstractCard) new WingsOfLust());
        BaseMod.addCard((AbstractCard) new DemonicAssault());
        BaseMod.addCard((AbstractCard) new Emeralda());
        BaseMod.addCard((AbstractCard) new Azazel());
        BaseMod.addCard((AbstractCard) new Yurius());
        BaseMod.addCard((AbstractCard) new UnleashTheNightmare());
        BaseMod.addCard((AbstractCard) new MechashotDevil());
        BaseMod.addCard((AbstractCard) new ArmoredBat());
        BaseMod.addCard((AbstractCard) new MechaforgeDevil());
        BaseMod.addCard((AbstractCard) new FirstOne());
        BaseMod.addCard((AbstractCard) new Mono());
        BaseMod.addCard((AbstractCard) new Mono_Unlock());
        BaseMod.addCard((AbstractCard) new Slayn());
        BaseMod.addCard((AbstractCard) new Neun());
        BaseMod.addCard((AbstractCard) new CreepingMadness());
        BaseMod.addCard((AbstractCard) new Cradle());
        BaseMod.addCard((AbstractCard) new RuinwebSpider());
        BaseMod.addCard((AbstractCard) new BloodArts());
        BaseMod.addCard((AbstractCard) new Aluzard());
        BaseMod.addCard((AbstractCard) new SummoningBloodkin());
        BaseMod.addCard((AbstractCard) new RageCommander());
        BaseMod.addCard((AbstractCard) new Relinquish());
        BaseMod.addCard((AbstractCard) new Flauros());
        BaseMod.addCard((AbstractCard) new DevilOfVengeance());
        BaseMod.addCard((AbstractCard) new OmenOfLust());
        BaseMod.addCard((AbstractCard) new ServantOfLust());
        BaseMod.addCard((AbstractCard) new DevilSheep());
        BaseMod.addCard((AbstractCard) new TheBerserker());
        BaseMod.addCard((AbstractCard) new PrisonOfPain());
        BaseMod.addCard((AbstractCard) new Lilim());
        BaseMod.addCard((AbstractCard) new ChaosShip());
        BaseMod.addCard((AbstractCard) new NightFall());
        BaseMod.addCard((AbstractCard) new MadnessRevealed());
        BaseMod.addCard((AbstractCard) new RestlessParish());
        BaseMod.addCard((AbstractCard) new TerrorNight());
        BaseMod.addCard((AbstractCard) new NightmareTime());
        BaseMod.addCard((AbstractCard) new BloodyNail());
        BaseMod.addCard((AbstractCard) new DreadAura());
        BaseMod.addCard((AbstractCard) new Urias());
        BaseMod.addCard((AbstractCard) new Jormungand());
        BaseMod.addCard((AbstractCard) new OldBloodKing());
        BaseMod.addCard((AbstractCard) new NightHorde());
        BaseMod.addCard((AbstractCard) new Veight());
        BaseMod.addCard((AbstractCard) new Vanpi());
        BaseMod.addCard((AbstractCard) new Vanpi1());
        BaseMod.addCard((AbstractCard) new Vanpi2());
        BaseMod.addCard((AbstractCard) new Volteo());
        BaseMod.addCard((AbstractCard) new ShowdownDemon());
        BaseMod.addCard((AbstractCard) new BatNoise());
        BaseMod.addCard((AbstractCard) new ShadowDevil());
        BaseMod.addCard((AbstractCard) new MaskedMayhem());
        BaseMod.addCard((AbstractCard) new Ayre());
        BaseMod.addCard((AbstractCard) new Lucius());
        BaseMod.addCard((AbstractCard) new SilverboltHail());
        BaseMod.addCard((AbstractCard) new ArkDaemon());
        BaseMod.addCard((AbstractCard) new Archdemon());
        BaseMod.addCard((AbstractCard) new Executioner());
        BaseMod.addCard((AbstractCard) new BelphometCard());
        BaseMod.addCard((AbstractCard) new ArmoredTentacle());
        BaseMod.addCard((AbstractCard) new AssaultTentacle());
        BaseMod.addCard((AbstractCard) new TisiphoneCard());
        BaseMod.addCard((AbstractCard) new MegaeraCard());
        BaseMod.addCard((AbstractCard) new AlectorCard());
        BaseMod.addCard((AbstractCard) new NeoTisiphone());
        BaseMod.addCard((AbstractCard) new NeoMegaera());
        BaseMod.addCard((AbstractCard) new NeoAlector());
        BaseMod.addCard((AbstractCard) new ProphecyOfDoom());
        BaseMod.addCard((AbstractCard) new ProphecyOfBoons());
        BaseMod.addCard((AbstractCard) new LadicaEmbrace());
        BaseMod.addCard((AbstractCard) new EternalPotion());
        BaseMod.addCard((AbstractCard) new InstantPotion());
        BaseMod.addCard((AbstractCard) new ImmoralDesire());
        BaseMod.addCard((AbstractCard) new Kyouka(0));
        BaseMod.addCard((AbstractCard) new Irya(0));
        BaseMod.addCard((AbstractCard) new Strike_Nm());
        BaseMod.addCard((AbstractCard) new Defend_Nm());
        BaseMod.addCard((AbstractCard) new DimensionCut());
        BaseMod.addCard((AbstractCard) new AnalyzeArtifact());
        BaseMod.addCard((AbstractCard) new AncientArtifact());
        BaseMod.addCard((AbstractCard) new MysticArtifact());
        BaseMod.addCard((AbstractCard) new RadiantArtifact());
        BaseMod.addCard((AbstractCard) new PrimeArtifact());
        BaseMod.addCard((AbstractCard) new ProtectArtifact());
        BaseMod.addCard((AbstractCard) new EdgeArtifact());
        BaseMod.addCard((AbstractCard) new BlitzArtifact());
        BaseMod.addCard((AbstractCard) new TraceArtifact());
        BaseMod.addCard((AbstractCard) new SpineArtifact());
        BaseMod.addCard((AbstractCard) new StrikeFormGolem());
        BaseMod.addCard((AbstractCard) new GuardFormGolem());
        BaseMod.addCard((AbstractCard) new Puppet());
        BaseMod.addCard((AbstractCard) new MagisteelLion());
        BaseMod.addCard((AbstractCard) new Acceleratium());
        BaseMod.addCard((AbstractCard) new AugmentationBestowal());
        BaseMod.addCard((AbstractCard) new ParadigmShift());
        BaseMod.addCard((AbstractCard) new Automation());
        BaseMod.addCard((AbstractCard) new Synchronization());
        BaseMod.addCard((AbstractCard) new ArtifactScan());
        BaseMod.addCard((AbstractCard) new Biofabrication());
        BaseMod.addCard((AbstractCard) new Icarus());
        BaseMod.addCard((AbstractCard) new RoboticUser());
        BaseMod.addCard((AbstractCard) new ArtifactCall());
        BaseMod.addCard((AbstractCard) new NilpotentEntity());
        BaseMod.addCard((AbstractCard) new DeusExMachina());
        BaseMod.addCard((AbstractCard) new NewOrder());
        BaseMod.addCard((AbstractCard) new Tolerance());
        BaseMod.addCard((AbstractCard) new Enforcer());
        BaseMod.addCard((AbstractCard) new MegaEnforcer());
        BaseMod.addCard((AbstractCard) new Invasion());
        BaseMod.addCard((AbstractCard) new Armadillo());
        BaseMod.addCard((AbstractCard) new AerialCraft());
        BaseMod.addCard((AbstractCard) new LasershellTortoise());
        BaseMod.addCard((AbstractCard) new Bearminator());
        BaseMod.addCard((AbstractCard) new Prototype());
        BaseMod.addCard((AbstractCard) new MagnaGiant());
        BaseMod.addCard((AbstractCard) new MagnaZero());
        BaseMod.addCard((AbstractCard) new Yuwan());
        BaseMod.addCard((AbstractCard) new Paracelsus());
        BaseMod.addCard((AbstractCard) new Windup());
        BaseMod.addCard((AbstractCard) new PuppeteerStrings());
        BaseMod.addCard((AbstractCard) new InfinityPuppeteer());
        BaseMod.addCard((AbstractCard) new WhiteArtifact());
        BaseMod.addCard((AbstractCard) new BlackArtifact());
        BaseMod.addCard((AbstractCard) new OmenOfDestruction());
        BaseMod.addCard((AbstractCard) new Licht());
        BaseMod.addCard((AbstractCard) new PuppetRoom());
        BaseMod.addCard((AbstractCard) new Roid());
        BaseMod.addCard((AbstractCard) new Victoria());
        BaseMod.addCard((AbstractCard) new HeartlessBattle());
        BaseMod.addCard((AbstractCard) new IronStaffMechanic());
        BaseMod.addCard((AbstractCard) new YuwanFury());
        BaseMod.addCard((AbstractCard) new BelphometCrackdown());
        BaseMod.addCard((AbstractCard) new RebelAgainstFate());
        BaseMod.addCard((AbstractCard) new PurgationBlade());
        BaseMod.addCard((AbstractCard) new Maisha());
        BaseMod.addCard((AbstractCard) new Metaproduction());
        BaseMod.addCard((AbstractCard) new DeviceTuner());
        BaseMod.addCard((AbstractCard) new ApostleOfDestruction());
        BaseMod.addCard((AbstractCard) new DiscipleOfDestruction());
        BaseMod.addCard((AbstractCard) new StringMaster());
        BaseMod.addCard((AbstractCard) new DominateFortress());
        BaseMod.addCard((AbstractCard) new KnowerOfHistory());
        BaseMod.addCard((AbstractCard) new GadgetUser());
        BaseMod.addCard((AbstractCard) new Karula());
        BaseMod.addCard((AbstractCard) new CurseOfPurgation());
        BaseMod.addCard((AbstractCard) new Uno());
        BaseMod.addCard((AbstractCard) new Due());
        BaseMod.addCard((AbstractCard) new Tre());
        BaseMod.addCard((AbstractCard) new Orchid1());
        BaseMod.addCard((AbstractCard) new Orchid2());
        BaseMod.addCard((AbstractCard) new Orchid());
        BaseMod.addCard((AbstractCard) new MagiTrainCard());
        BaseMod.addCard((AbstractCard) new ICCard());
        BaseMod.addCard((AbstractCard) new DeathPenalty());
        BaseMod.addCard((AbstractCard) new UnnamedDetermination());
        BaseMod.addCard((AbstractCard) new Noa());
        BaseMod.addCard((AbstractCard) new MindDivider());
        BaseMod.addCard((AbstractCard) new Ines());
        BaseMod.addCard((AbstractCard) new DollsOwner());
        BaseMod.addCard((AbstractCard) new DestructionRefrain());
        BaseMod.addCard((AbstractCard) new Modest());
        BaseMod.addCard((AbstractCard) new Ralmia());
        BaseMod.addCard((AbstractCard) new Concentrate());
        BaseMod.addCard((AbstractCard) new TechnologyMana());
        BaseMod.addCard((AbstractCard) new OmniscientKaiser());
        BaseMod.addCard((AbstractCard) new Spine());
        BaseMod.addCard((AbstractCard) new Miriam());
        BaseMod.addCard((AbstractCard) new CannonHermitCrab());
        BaseMod.addCard((AbstractCard) new MultiarmedArtifact());
        BaseMod.addCard((AbstractCard) new ArtifactImpulse());
        BaseMod.addCard((AbstractCard) new ServantOfDarkness());
        BaseMod.addCard((AbstractCard) new Defend_R());
        BaseMod.addCard((AbstractCard) new Strike_R());
        BaseMod.addCard((AbstractCard) new OathlessKnight());
        BaseMod.addCard((AbstractCard) new GildedBlade());
        BaseMod.addCard((AbstractCard) new GildedGoblet());
        BaseMod.addCard((AbstractCard) new GildedNecklace());
        BaseMod.addCard((AbstractCard) new GildedBoots());
        BaseMod.addCard((AbstractCard) new GrandAcquisition());
        BaseMod.addCard((AbstractCard) new SageCommander());
        BaseMod.addCard((AbstractCard) new DanceOfUsurpation());
        BaseMod.addCard((AbstractCard) new UsurpingSpineblade());
        BaseMod.addCard((AbstractCard) new WeeMerchantsAppraisal());
        BaseMod.addCard((AbstractCard) new DiscipleOfUsurpation());
        BaseMod.addCard((AbstractCard) new ApostleOfUsurpation());
        BaseMod.addCard((AbstractCard) new Octrice());
        BaseMod.addCard((AbstractCard) new DramaticRetreat());
        BaseMod.addCard((AbstractCard) new ShieldPhalanx());
        BaseMod.addCard((AbstractCard) new WhitePaladin());
        BaseMod.addCard((AbstractCard) new TemperedMana());
        BaseMod.addCard((AbstractCard) new Mars());
        BaseMod.addCard((AbstractCard) new MistolinaBayleon());
        BaseMod.addCard((AbstractCard) new Sera());
        BaseMod.addCard((AbstractCard) new FrenziedCorpsmaster());
        BaseMod.addCard((AbstractCard) new CatAdmiral());
        BaseMod.addCard((AbstractCard) new FortressStrategist());
        BaseMod.addCard((AbstractCard) new EvolutionPoint());
        BaseMod.addCard((AbstractCard) new EnragedGeneral());
        BaseMod.addCard((AbstractCard) new LuminousMage());
        BaseMod.addCard((AbstractCard) new PrudentGeneral());
        BaseMod.addCard((AbstractCard) new DualbladeKnight());
        BaseMod.addCard((AbstractCard) new StrikeproneGuardian());
        BaseMod.addCard((AbstractCard) new MonochromeEndgame());
        BaseMod.addCard((AbstractCard) new QueenHemera_Card());
        BaseMod.addCard((AbstractCard) new QueenMagnus_Card());
        BaseMod.addCard((AbstractCard) new FlyingMessengerSquirrel());
        BaseMod.addCard((AbstractCard) new PompousSummons());
        BaseMod.addCard((AbstractCard) new BrothersUnited());
        BaseMod.addCard((AbstractCard) new HonorableThief());
        BaseMod.addCard((AbstractCard) new FloralFencer());
        BaseMod.addCard((AbstractCard) new SunnyDayEncounter());
        BaseMod.addCard((AbstractCard) new WarriorWing());
        BaseMod.addCard((AbstractCard) new Alyaska());
        BaseMod.addCard((AbstractCard) new ExterminusWeapon());
        BaseMod.addCard((AbstractCard) new RadicalGunslinger());
        BaseMod.addCard((AbstractCard) new Mirin());
        BaseMod.addCard((AbstractCard) new Seofon());
        BaseMod.addCard((AbstractCard) new Eahta());
        BaseMod.addCard((AbstractCard) new StrokeOfConviction());
        BaseMod.addCard((AbstractCard) new ErikasSleight());
        BaseMod.addCard((AbstractCard) new MistolinasSwordplay());
        BaseMod.addCard((AbstractCard) new BayleonsCommand());
        BaseMod.addCard((AbstractCard) new FrontlineInstructor());
        BaseMod.addCard((AbstractCard) new RoyalBanner());
        BaseMod.addCard((AbstractCard) new LuxbladeArriet());
        BaseMod.addCard((AbstractCard) new EmpressOfSerenity());
        BaseMod.addCard((AbstractCard) new Charlotta());

        /* 276 */
        logger.info("Success");
        /*     */
    }

    /*     */
    /*     */
    /*     */
    public void receiveEditCharacters() {
        /* 281 */
        logger.info("Adding char");
        /* 282 */
        BaseMod.addCharacter((AbstractPlayer) new Witchcraft("Witchcraft"), "img/character/Witchcraft/button1.png", "img/character/Witchcraft/background.png", Witchcraft.Enums.WITCHCRAFT);
        BaseMod.addCharacter((AbstractPlayer) new Elf(("Elf")), "img/character/Elf/button.png", "img/character/Elf/background.png", Elf.Enums.Elf);
        BaseMod.addCharacter((AbstractPlayer) new Necromancer(("Necromancer")), "img/character/Necromancer/button.png", "img/character/Necromancer/background.png", Necromancer.Enums.Necromancer);
        BaseMod.addCharacter((AbstractPlayer) new Vampire(("Vamp")), "img/character/Vampire/button.png", "img/character/Vampire/background.png", Vampire.Enums.Vampire);
        BaseMod.addCharacter((AbstractPlayer) new Nemesis(("Nemesis")), "img/character/Nemesis/button.png", "img/character/Nemesis/background.png", Nemesis.Enums.Nemesis);
        BaseMod.addCharacter((AbstractPlayer) new Royal(("Royal")), "img/character/Royal/button.png", "img/character/Royal/background.png", Royal.Enums.Royal);
        /*     */
    }
    /*     */
}



/* Location:              F:\Steam\steamapps\common\SlayTheSpire\mods\shadowverseMod.jar!\shadowverse\shadowverse.Shadowverse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */