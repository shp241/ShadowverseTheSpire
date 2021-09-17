package shadowverse.monsters;

import basemod.abstracts.CustomMonster;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.LaserBeamEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import shadowverse.cards.Status.BelphometStatus;
import shadowverse.powers.ManaBarrier;
import shadowverse.powers.chushouExplosionPower;
import shadowverse.powers.chushouHealPower;

import java.util.HashMap;
import java.util.Map;

public class Belphomet extends CustomMonster {
    public static final String ID = "shadowverse:Belphomet";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("shadowverse:Belphomet");
    public static final String NAME = monsterStrings.NAME;

    public static final String[] MOVES = monsterStrings.MOVES;

    public static final String[] DIALOG = monsterStrings.DIALOG;

    public static final int HP = 500;

    public static final int A_2_HP = 420;

    private static final byte SPAWN = 1;

    private static final byte BEAM = 2;

    private static final byte BUFF = 3;

    private static final byte DEBUFF = 4;

    private static final byte HYPER_BEAM = 5;

    private static final byte REVIVE = 6;

    private int blockAmt;

    private int strAmt;

    private int beamDmg;

    private int hyperBeamDmg;

    private int debuffAmount;

    private boolean initialSpawn = true;

    private int turnsTaken = 0;

    private float spawnX = -100.0F;

    private HashMap<Integer, AbstractMonster> enemySlots = new HashMap<>();

    public Belphomet() {
        super(NAME, ID, 500, 0.0F, -30F, 600.0F, 400.0F, null, 60.0F, 130.0F);
        this.animation = new SpriterAnimation("img/monsters/Belphomet1/Belphomet.scml");
        this.dialogX = -100.0F * Settings.scale;
        this.dialogY = 10.0F * Settings.scale;
        this.type = AbstractMonster.EnemyType.BOSS;
        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(520);
            this.blockAmt = 25;
        } else {
            setHp(500);
            this.blockAmt = 23;
        }
        if (AbstractDungeon.ascensionLevel >= 19) {
            this.hyperBeamDmg = 40;
            this.beamDmg = 7;
            this.strAmt = 6;
            this.debuffAmount = 4;
        } else if (AbstractDungeon.ascensionLevel >= 4) {
            this.hyperBeamDmg = 38;
            this.beamDmg = 6;
            this.strAmt = 5;
            this.debuffAmount = 3;
        } else {
            this.hyperBeamDmg = 35;
            this.beamDmg = 5;
            this.strAmt = 4;
            this.debuffAmount = 3;
        }
        this.damage.add(new DamageInfo((AbstractCreature)this, this.beamDmg));
        this.damage.add(new DamageInfo((AbstractCreature)this, this.hyperBeamDmg));
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BEYOND");
        if (AbstractDungeon.ascensionLevel >= 19) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RegenerateMonsterPower(this, 15)));
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 2)));
        } else {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new RegenerateMonsterPower(this, 10)));
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 1)));
        }
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new BarricadePower((AbstractCreature)this)));
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, 25));
    }

    @Override
    public void takeTurn() {
        int i;
        AbstractCard c = (AbstractCard)new BelphometStatus();
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[2]));
                addToBot((AbstractGameAction)new SFXAction("Belphomet3"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ManaBarrier((AbstractCreature) this)));
                AbstractMonster m1 = new chushou1(this.spawnX + -185.0F * 1, MathUtils.random(-5.0F, 25.0F));
                AbstractMonster m2 = new chushou2(this.spawnX + -185.0F * 2, MathUtils.random(-5.0F, 25.0F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("MONSTER_COLLECTOR_SUMMON"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(m1, true));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(m2, true));
                this.enemySlots.put(Integer.valueOf(1), m1);
                this.enemySlots.put(Integer.valueOf(2), m2);
                if (AbstractDungeon.ascensionLevel >= 19) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m1, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)m1, 3)));
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m2, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)m2, 3)));
                } else {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m1, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)m1, 2)));
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m2, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)m2, 2)));
                }
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m1, (AbstractCreature)this, (AbstractPower)new chushouExplosionPower((AbstractCreature)m1, 12)));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m2, (AbstractCreature)this, (AbstractPower)new chushouHealPower((AbstractCreature)m2, 80)));
                this.initialSpawn = false;
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[5]));
                addToBot((AbstractGameAction)new SFXAction("Belphomet6"));
                for (i = 0;i<3;i++){
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
                            .get(0), AbstractGameAction.AttackEffect.NONE, true));
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.SKY)));
                    if (Settings.FAST_MODE) {
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX, this.hb.cY), 0.1F));
                    } else {
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.hb.cX, this.hb.cY), 0.3F));
                    }
                }
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[3]));
                addToBot((AbstractGameAction)new SFXAction("Belphomet4"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.blockAmt));
                for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (!m.isDead && !m.isDying && !m.isEscaping)
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)m, this.strAmt), this.strAmt));
                }
                if (!this.hasPower("shadowverse:ManaBarrier")){
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ManaBarrier((AbstractCreature) this)));
                }
                break;
            case 4:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[0]));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)AbstractDungeon.player, this.debuffAmount, true), this.debuffAmount));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player, this.debuffAmount, true), this.debuffAmount));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, this.debuffAmount, true), this.debuffAmount));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new MakeTempCardInHandAction(c, 3));
                break;
            case 5:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[4]));
                addToBot((AbstractGameAction)new SFXAction("Belphomet5"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new LaserBeamEffect(this.hb.cX, this.hb.cY + 60.0F * Settings.scale), 1.5F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
                        .get(1), AbstractGameAction.AttackEffect.NONE));
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[1]));
                addToBot((AbstractGameAction)new SFXAction("Belphomet2"));
                for (Map.Entry<Integer, AbstractMonster> m : this.enemySlots.entrySet()) {
                    if (((AbstractMonster)m.getValue()).isDying) {
                        int rand = AbstractDungeon.aiRng.random(0, 99);
                        if (rand < 50){
                            AbstractMonster newMonster = new chushou2(this.spawnX + -185.0F * ((Integer)m.getKey()).intValue(), MathUtils.random(-5.0F, 25.0F));
                            int key = ((Integer)m.getKey()).intValue();
                            this.enemySlots.put(Integer.valueOf(key), newMonster);
                            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(newMonster, true));
                            if (AbstractDungeon.ascensionLevel >= 19) {
                                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)newMonster, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)newMonster, 3)));
                            } else {
                                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)newMonster, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)newMonster, 2)));
                            }
                            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)newMonster, (AbstractCreature)this, (AbstractPower)new chushouHealPower((AbstractCreature)newMonster, 80)));
                        }else {
                            AbstractMonster newMonster = new chushou1(this.spawnX + -185.0F * ((Integer)m.getKey()).intValue(), MathUtils.random(-5.0F, 25.0F));
                            int key = ((Integer)m.getKey()).intValue();
                            this.enemySlots.put(Integer.valueOf(key), newMonster);
                            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(newMonster, true));
                            if (AbstractDungeon.ascensionLevel >= 19) {
                                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)newMonster, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)newMonster, 3)));
                            } else {
                                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)newMonster, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)newMonster, 2)));
                            }
                            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)newMonster, (AbstractCreature)this, (AbstractPower)new chushouExplosionPower((AbstractCreature)newMonster, 12)));
                        }
                    }
                }
                break;
            default:
                System.out.println("ERROR: Default Take Turn was called on " + this.name);
                break;
        }
        this.turnsTaken++;
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
    }

    @Override
    protected void getMove(int i) {
        if (this.initialSpawn) {
            setMove((byte)1, AbstractMonster.Intent.UNKNOWN);
            return;
        }
        if (i<50&&this.turnsTaken >= 3 && !lastMove((byte)4)) {
            setMove((byte)4, AbstractMonster.Intent.STRONG_DEBUFF);
            return;
        }
        if (i > 65 && isMinionDead() && !lastMove((byte)6)) {
            setMove((byte)6, AbstractMonster.Intent.UNKNOWN);
            return;
        }
        if (i <= 65 && !lastTwoMoves((byte)2)) {
            setMove((byte)2, Intent.ATTACK_DEBUFF, ((DamageInfo)this.damage.get(0)).base, 3, true);
            return;
        }
        if (!lastMove((byte)3)) {
            setMove((byte)3, AbstractMonster.Intent.DEFEND_BUFF);
        } else {
            setMove((byte)5, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
        }
    }

    private boolean isMinionDead() {
        for (Map.Entry<Integer, AbstractMonster> m : this.enemySlots.entrySet()) {
            if (((AbstractMonster)m.getValue()).isDying)
                return true;
        }
        return false;
    }

    public void die() {
        super.die();
        useFastShakeAnimation(5.0F);
        CardCrawlGame.screenShake.rumble(4.0F);
        for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
            if (m.isDying || m.isDead)
                continue;
            AbstractDungeon.actionManager.addToTop((AbstractGameAction)new HideHealthBarAction((AbstractCreature)m));
            AbstractDungeon.actionManager.addToTop((AbstractGameAction)new SuicideAction(m));
            AbstractDungeon.actionManager.addToTop((AbstractGameAction)new VFXAction((AbstractCreature)m, (AbstractGameEffect)new InflameEffect((AbstractCreature)m), 0.2F));
        }
        onBossVictoryLogic();
        onFinalBossVictoryLogic();
    }
}
