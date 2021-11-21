package shadowverse.monsters;

import basemod.abstracts.CustomMonster;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
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
import com.megacrit.cardcrawl.vfx.combat.BloodShotEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;
import shadowverse.powers.ICPower;

import java.util.HashMap;

public class Iceschillendrig extends CustomMonster {
    public static final String ID = "shadowverse:Iceschillendrig";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("shadowverse:Iceschillendrig");
    public static final String NAME = monsterStrings.NAME;

    public static final String[] MOVES = monsterStrings.MOVES;

    public static final String[] DIALOG = monsterStrings.DIALOG;

    public static final int HP = 570;

    public static final int A_2_HP = 600;

    private static final byte SPAWN = 1;

    private static final byte ATTACK = 3;

    private static final byte BLOOD_SHOT = 2;

    private static final byte DEBUFF = 4;

    private static final byte BUFF = 5;

    private static final byte REVIVE = 6;

    private int Dmg;

    private int bloodHitCount;

    private int debuffAmount;

    private int turnsTaken = 0;

    private boolean usedHaste = false;

    private boolean usedPenalty = false;


    private float spawnX = -100.0F;

    private static final String DEATH_PENALTY = MOVES[0];

    private HashMap<Integer, AbstractMonster> enemySlots = new HashMap<>();

    public Iceschillendrig() {
        super(NAME, ID, 570, 0.0F, -30F, 230.0F, 400.0F, null, 80.0F, -30.0F);
        this.animation = new SpriterAnimation("img/monsters/Iceschillendrig/Iceschillendrig.scml");
        this.dialogX = -100.0F * Settings.scale;
        this.dialogY = 10.0F * Settings.scale;
        this.type = EnemyType.BOSS;
        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(600);
        } else {
            setHp(570);
        }
        if (AbstractDungeon.ascensionLevel >= 19) {
            this.debuffAmount = 4;
            this.bloodHitCount = 7;
            this.Dmg = 40;
        } else if (AbstractDungeon.ascensionLevel >= 4) {
            this.bloodHitCount = 7;
            this.debuffAmount = 3;
            this.Dmg = 37;
        } else {
            this.bloodHitCount = 5;
            this.debuffAmount = 3;
            this.Dmg = 35;
        }
        this.damage.add(new DamageInfo((AbstractCreature)this, this.Dmg));
        this.damage.add(new DamageInfo((AbstractCreature)this, 2));
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BEYOND");
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new ICPower((AbstractCreature)AbstractDungeon.player,(AbstractCreature)this)));
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new WeakPower((AbstractCreature)this,99,true)));
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[0]));
        addToBot((AbstractGameAction)new SFXAction("IC1"));
        AbstractMonster m = new MagiTrain(this.spawnX + -185.0F * 1, MathUtils.random(-10.0F, 20.0F));
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SpawnMonsterAction(m, true));
        this.enemySlots.put(Integer.valueOf(1), m);
        if (AbstractDungeon.ascensionLevel >= 19) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)m, 3)));
        } else {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)m, 2)));
        }
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)m, (AbstractCreature)this, (AbstractPower)new InvinciblePower((AbstractCreature)m, 120), 120));

    }


    @Override
    public void takeTurn() {
        int i;
        switch (this.nextMove) {
            case 2:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[1]));
                addToBot((AbstractGameAction)new SFXAction("IC2"));
                if (Settings.FAST_MODE) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BloodShotEffect(this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.bloodHitCount), 0.25F));
                } else {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BloodShotEffect(this.hb.cX, this.hb.cY, AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, this.bloodHitCount), 0.6F));
                }
                for (i = 0; i < this.bloodHitCount; i++)
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
                            .get(1), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[2]));
                addToBot((AbstractGameAction)new SFXAction("IC3"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViceCrushEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.5F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
                        .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                break;
            case 4:
                int cardCount = 1;
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[3]));
                addToBot((AbstractGameAction)new SFXAction("IC4"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractCreature)this, (AbstractGameEffect)new ShockWaveEffect(this.hb.cX, this.hb.cY, Settings.GREEN_TEXT_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 1.5F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FrailPower((AbstractCreature)AbstractDungeon.player, this.debuffAmount, true), this.debuffAmount));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)AbstractDungeon.player, -4), -4));
                for (AbstractCard ca:AbstractDungeon.player.drawPile.group){
                    if (ca.rarity == AbstractCard.CardRarity.RARE){
                        cardCount--;
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ExhaustSpecificCardAction(ca,AbstractDungeon.player.drawPile));
                        break;
                    }
                }
                if (cardCount>0){
                    for (AbstractCard ca:AbstractDungeon.player.discardPile.group){
                        if (ca.rarity == AbstractCard.CardRarity.RARE){
                            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ExhaustSpecificCardAction(ca,AbstractDungeon.player.discardPile));
                            break;
                        }
                    }
                }
                break;
            case 5:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[4]));
                addToBot((AbstractGameAction)new SFXAction("IC5"));
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (!mo.isDead && !mo.isDying && !mo.isEscaping){
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)mo, (AbstractCreature)this, 20));
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)mo, 1), 1));
                    }
                }
                break;
            case 6:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DIALOG[5], 0.5F, 2.0F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveDebuffsAction((AbstractCreature)this));
                this.increaseMaxHp(AbstractDungeon.player.maxHealth*10-this.maxHealth,true);
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new HealAction((AbstractCreature)this, (AbstractCreature)this, AbstractDungeon.player.maxHealth*10));
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (!mo.isDead && !mo.isDying && !mo.isEscaping){
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)mo, (AbstractCreature)this, 20));
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)mo, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)mo, 1), 1));
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
        if (this.currentHealth < this.maxHealth / 2 && !this.usedHaste) {
            this.usedHaste = true;
            setMove((byte)6, AbstractMonster.Intent.BUFF);
            return;
        }
        if (!usedPenalty && this.turnsTaken >= 2 && !lastMove((byte)4)) {
            setMove(DEATH_PENALTY,(byte)4, AbstractMonster.Intent.STRONG_DEBUFF);
            usedPenalty = true;
            return;
        }
        if (i <= 70) {
            if (!lastMove((byte)2)){
                setMove((byte)2, Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base, this.bloodHitCount, true);
                return;
            }
            setMove((byte)5, AbstractMonster.Intent.DEFEND_BUFF);
            return;
        }
        if (!lastMove((byte)3)) {
            setMove((byte)3, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
        }
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
