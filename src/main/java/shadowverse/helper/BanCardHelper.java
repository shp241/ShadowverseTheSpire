package shadowverse.helper;

import com.megacrit.cardcrawl.cards.AbstractCard;
import shadowverse.cards.Common.*;
import shadowverse.cards.Rare.*;
import shadowverse.cards.Uncommon.*;

import java.util.ArrayList;

public class BanCardHelper {
    private static boolean init;
    public static ArrayList<ArrayList<AbstractCard>> royalCardGroupPool = new ArrayList();

    public BanCardHelper() {
    }

    public static void init() {
        if (!init) {
            init = true;
            //基本
            royalCardGroupPool.add(new ArrayList<AbstractCard>() {
                {
                    this.add(new Valse());
                    this.add(new FlyingMessengerSquirrel());
                    this.add(new PompousSummons());
                    this.add(new ShieldPhalanx());
                    this.add(new BladeDance());
                    this.add(new StrikeproneGuardian());
                    this.add(new MirrorImage());
                    this.add(new EleganceInAction());
                    this.add(new Reinhardt());
                    this.add(new MasterDealer());
                    this.add(new Ilmisuna());
                    this.add(new FrenziedCorpsmaster());
                    this.add(new CatAdmiral());
                    this.add(new StrokeOfConviction());
                    this.add(new EmpressOfSerenity());
                    this.add(new DualbladeKnight());
                    this.add(new RadicalGunslinger());
                    this.add(new PrudentGeneral());
                    this.add(new TheChariot());
                    this.add(new Alexander());
                    this.add(new Nahtnaught());
                    this.add(new Spector());
                    this.add(new MusketeersVow());
                    this.add(new Sera());
                    this.add(new Garven());
                    this.add(new WarriorWing());
                    this.add(new Erika());
                    this.add(new Kagemitsu());
                    this.add(new MonochromeEndgame());
                }
            });
            //随从
            royalCardGroupPool.add(new ArrayList<AbstractCard>() {
                {
                    this.add(new RoyalBanner());
                    this.add(new SageCommander());
                    this.add(new GloriousCore());
                    this.add(new EnragedGeneral());
                    this.add(new Leonidas());
                    this.add(new FrontlineInstructor());
                    this.add(new WhitePaladin());
                    this.add(new Latham());
                    this.add(new Arthur());
                    this.add(new Mars());
                    this.add(new CaptainWalfrid());
                }
            });
            //进化
            royalCardGroupPool.add(new ArrayList<AbstractCard>() {
                {
                    this.add(new Charlotta());
                    this.add(new Ernesta());
                    this.add(new Mirin());
                    this.add(new FortressStrategist());
                    this.add(new HonoredFrontguardGeneral());
                    this.add(new LuminousMage());
                    this.add(new Eahta());
                    this.add(new Seofon());
                    this.add(new Alyaska());
                    this.add(new LuxbladeArriet());
                }
            });
            //财宝
            royalCardGroupPool.add(new ArrayList<AbstractCard>() {
                {
                    this.add(new DiscipleOfUsurpation());
                    this.add(new UsurpingSpineblade());
                    this.add(new AdherentOfHollow());
                    this.add(new WeeMerchantsAppraisal());
                    this.add(new ApostleOfUsurpation());
                    this.add(new UltimateHollow());
                    this.add(new GrandAcquisition());
                    this.add(new DanceOfUsurpation());
                    this.add(new Octrice());
                    this.add(new Alwida());
                }
            });
            //雷维翁
            royalCardGroupPool.add(new ArrayList<AbstractCard>() {
                {
                    this.add(new LevinArcher());
                    this.add(new Jeno());
                    this.add(new LevinScholar());
                    this.add(new Lounes());
                    this.add(new MeetTheLevinSisters());
                    this.add(new LevinJustice());
                    this.add(new LevinBeastmaster());
                    this.add(new Albert());
                }
            });
            //机械自然
            royalCardGroupPool.add(new ArrayList<AbstractCard>() {
                {
                    this.add(new Cybercannoneer());
                    this.add(new BrothersUnited());
                    this.add(new TemperedMana());
                    this.add(new StampedingFortress());
                    this.add(new SunnyDayEncounter());
                    this.add(new DramaticRetreat());
                    this.add(new Johann());
                    this.add(new Grayson());
                    this.add(new MistolinaBayleon());
                    this.add(new Patrick());
                }
            });
            //潜伏
            royalCardGroupPool.add(new ArrayList<AbstractCard>() {
                {
                    this.add(new ShinobiTanuki());
                    this.add(new ShieldOfFlame());
                    this.add(new ShadowedMemories());
                    this.add(new Dualblade());
                    this.add(new GracefulManeuver());
                    this.add(new Leod());
                    this.add(new AmbushBuff());
                    this.add(new Tsubaki());
                }
            });
            //金币
            royalCardGroupPool.add(new ArrayList<AbstractCard>() {
                {
                    this.add(new FrontDeskFrog());
                    this.add(new SuaveBandit());
                    this.add(new HonorableThief());
                    this.add(new NightOnTheTown());
                    this.add(new MasterfulMusician());
                    this.add(new NobleShieldmaiden());
                    this.add(new JiemonThiefLord());
                    this.add(new Taketsumi());
                    this.add(new OpulentStrategist());
                }
            });
        }
    }
}
