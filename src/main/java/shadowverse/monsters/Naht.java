package shadowverse.monsters;

import basemod.abstracts.CustomMonster;
import basemod.animations.SpineAnimation;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import shadowverse.action.AnimationAction;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.NahtPower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Naht extends CustomMonster {
    public static final String ID = "shadowverse:Naht";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("shadowverse:Naht");
    public static final String NAME = monsterStrings.NAME;

    public static final String[] MOVES = monsterStrings.MOVES;

    public static final String[] DIALOG = monsterStrings.DIALOG;

    private int minionAmt;

    private int heavyDmg;

    private int multiDmg;

    private int backDmg;

    private int strAmount;

    private int debuffAmount;

    private int buffAmount;

    private int next = 1;

    private boolean initialSpawn = true;


    private float spawnX = -100.0F;

    private HashMap<Integer, AbstractMonster> enemySlots = new HashMap<>();

    public Naht() {
        super(NAME, ID, 300, 0.0F, -30F, 600.0F, 400.0F, null, 60.0F, 130.0F);
        this.animation = new SpriterAnimation("img/monsters/Naht/Naht.scml");
        this.dialogX = -100.0F * Settings.scale;
        this.dialogY = 10.0F * Settings.scale;
        this.type = AbstractMonster.EnemyType.BOSS;
        if (AbstractDungeon.ascensionLevel >= 19) {
            this.heavyDmg = 25;
            this.multiDmg = 6;
            this.backDmg = 10;
            this.minionAmt = 4;
            this.strAmount = 4;
            this.debuffAmount = 3;
            this.buffAmount = 5;
        } else if (AbstractDungeon.ascensionLevel >= 4) {
            this.heavyDmg = 25;
            this.multiDmg = 6;
            this.backDmg = 8;
            this.minionAmt = 2;
            this.strAmount = 3;
            this.debuffAmount = 2;
            this.buffAmount = 3;
        } else {
            this.heavyDmg = 20;
            this.multiDmg = 4;
            this.backDmg = 6;
            this.minionAmt = 2;
            this.strAmount = 3;
            this.debuffAmount = 2;
            this.buffAmount = 3;
        }
        this.damage.add(new DamageInfo(this, this.heavyDmg));
        this.damage.add(new DamageInfo(this, this.multiDmg));
        this.damage.add(new DamageInfo(this, this.backDmg));
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BEYOND");
        int baseStrength = 0;
        if (AbstractDungeon.ascensionLevel >= 19) {
            baseStrength = AbstractDungeon.player.currentHealth / 10;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new InvinciblePower(this, 80)));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new CuriosityPower(this, 1)));
        } else {
            baseStrength = AbstractDungeon.player.currentHealth / 12;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new InvinciblePower(this, 100)));
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, baseStrength), baseStrength));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new NahtPower(this), 1));
    }

    @Override
    public void damage(DamageInfo info) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output > 0) {
            int r = MathUtils.random(10);
            if (info.output > 10) {
                r = r % 2 + 4;
            } else {
                r = r % 3 + 1;
            }
            AbstractDungeon.actionManager.addToBottom(new SFXAction("Naht_D" + r));
        }
        super.damage(info);
    }

    @Override
    public void takeTurn() {
        int i;
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom(new ShoutAction(this, DIALOG[0], 1.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom(new SFXAction("Naht_A1"));
                for (i = 0; i < 3; i++) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT, true));
                }
                this.next = (1 + AbstractDungeon.monsterRng.random(3)) % 5 + 1;
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom(new ShoutAction(this, DIALOG[1], 1.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom(new SFXAction("Naht_A2"));
                AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                this.next = (2 + AbstractDungeon.monsterRng.random(3)) % 5 + 1;
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom(new ShoutAction(this, DIALOG[2], 1.0F, 2.0F));
                addToBot(new SFXAction("Naht_A3"));
                AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_COLLECTOR_SUMMON"));
                for (i = 0; i < this.minionAmt; i++) {
                    AbstractMonster m = new Henchman(this.spawnX + -100.0F * (i + enemySlots.size()), MathUtils.random(-5.0F, 25.0F));
                    AbstractDungeon.actionManager.addToBottom(new SpawnMonsterAction(m, true));
                    this.enemySlots.put(i + 1, m);
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this, new MetallicizePower(m, this.buffAmount)));
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this, new StrengthPower(m, this.strAmount), this.strAmount));

                }
                this.next = (3 + AbstractDungeon.monsterRng.random(3)) % 5 + 1;
                break;
            case 4:
                AbstractDungeon.actionManager.addToBottom(new ShoutAction(this, DIALOG[3], 1.0F, 2.0F));
                addToBot(new SFXAction("Naht_A4"));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, this, new VulnerablePower(AbstractDungeon.player, this.debuffAmount, true), this.debuffAmount));
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this, this, new StrengthPower(this, this.strAmount), this.strAmount));
                this.next = (4 + AbstractDungeon.monsterRng.random(3)) % 5 + 1;
                break;
            case 5:
                AbstractDungeon.actionManager.addToBottom(new ShoutAction(this, DIALOG[4], 1.0F, 2.0F));
                addToBot(new SFXAction("Naht_A5"));
                for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (m.equals(this) || m.isDying || m.isDead) {
                        continue;
                    }
                    AbstractDungeon.actionManager.addToTop(new HideHealthBarAction(m));
                    AbstractDungeon.actionManager.addToTop(new SuicideAction(m));
                    AbstractDungeon.actionManager.addToTop(new VFXAction(m, new InflameEffect(m), 0.2F));
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(2), AbstractGameAction.AttackEffect.FIRE, true));
                }
                for (AbstractPower power : this.powers) {
                    if (power instanceof NahtPower && !((NahtPower) power).boxed.isEmpty()) {
                        for (AbstractCard c : ((NahtPower) power).boxed) {
                            AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, this.damage.get(2), AbstractGameAction.AttackEffect.FIRE, true));
                            if (AbstractDungeon.player.hand.size() != 10) {
                                this.addToBot(new MakeTempCardInHandAction(c, false, true));
                            } else {
                                this.addToBot(new MakeTempCardInDiscardAction(c, true));
                            }
                        }
                        ((NahtPower) power).boxed = new ArrayList<>();
                        power.updateDescription();
                        break;
                    }
                }
                this.next = (5 + AbstractDungeon.monsterRng.random(3)) % 5 + 1;
                break;
            default:
                System.out.println("ERROR: Default Take Turn was called on " + this.name);
                break;
        }
        AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
    }

    @Override
    protected void getMove(int i) {
        if (this.initialSpawn) {
            setMove((byte) 1, Intent.ATTACK, (this.damage.get(1)).base, 3, true);
            this.initialSpawn = false;
            return;
        }
        if (this.next == 1) {
            setMove((byte) 1, Intent.ATTACK, (this.damage.get(1)).base, 3, true);
            return;
        }
        if (this.next == 2) {
            setMove((byte) 2, Intent.ATTACK, (this.damage.get(0)).base);
            return;
        }
        if (this.next == 3) {
            setMove((byte) 3, Intent.UNKNOWN);
            return;
        }
        if (this.next == 4) {
            setMove((byte) 4, Intent.DEBUFF);
            return;
        }
        if (this.next == 5) {
            int ms = 0;
            for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                if (m.equals(this) || m.isDying || m.isDead) {
                    continue;
                }
                ms++;
            }
            for (AbstractPower power : this.powers) {
                if (power instanceof NahtPower && !((NahtPower) power).boxed.isEmpty()) {
                    ms += ((NahtPower) power).boxed.size();
                    break;
                }
            }
            if (ms != 0) {
                setMove((byte) 5, AbstractMonster.Intent.ATTACK, (this.damage.get(2)).base, ms, ms > 1);
                this.createIntent();
            } else {
                setMove((byte) 1, Intent.ATTACK, (this.damage.get(1)).base, 3, true);
            }
        }
    }

    @Override
    public void die() {
        addToBot(new SFXAction("Naht_C"));
        super.die();
        useFastShakeAnimation(5.0F);
        CardCrawlGame.screenShake.rumble(4.0F);
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (m.isDying || m.isDead) {
                continue;
            }
            AbstractDungeon.actionManager.addToTop(new HideHealthBarAction(m));
            AbstractDungeon.actionManager.addToTop(new SuicideAction(m));
            AbstractDungeon.actionManager.addToTop(new VFXAction(m, new InflameEffect(m), 0.2F));
        }
        onBossVictoryLogic();
        onFinalBossVictoryLogic();
    }
}
