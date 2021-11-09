package charbosses.bosses.KMR;

import basemod.animations.SpriterAnimation;
import charbosses.actions.util.CharbossTurnstartDrawAction;
import charbosses.actions.util.DelayedActionAction;
import charbosses.bosses.AbstractBossDeckArchetype;
import charbosses.bosses.AbstractCharBoss;
import charbosses.core.EnemyEnergyManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.ClearCardQueueAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.SetMoveAction;
import com.megacrit.cardcrawl.actions.unique.CanLoseAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.InvinciblePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbInterface;
import com.megacrit.cardcrawl.ui.panels.energyorb.EnergyOrbRed;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import shadowverse.action.AnimationAction;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.LionSanctuaryPower;

import java.util.Iterator;

public class KMR
        extends AbstractCharBoss {
    public static final String ID = "shadowverse:KMR";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("shadowverse:KMR");
    public static final String NAME = monsterStrings.NAME;
    public static final String[] DIALOG = monsterStrings.DIALOG;
//    public static shadowverse.animation.AbstractAnimation bigAnimation = new shadowverse.animation.AbstractAnimation("img/animation/KMR/class_3208.atlas", "img/animation/KMR/class_3208.json", com.megacrit.cardcrawl.core.Settings.M_W / 1600.0F, com.megacrit.cardcrawl.core.Settings.M_W / 2.0F, com.megacrit.cardcrawl.core.Settings.M_H / 2.0F, 0F, 0F);


    public KMR() {
        super(NAME, ID, 1000, -4.0F, -16.0F, 220.0F, 290.0F, null, 0.0F, -20.0F, AbstractPlayer.PlayerClass.IRONCLAD);
        this.energyOrb = (EnergyOrbInterface) new EnergyOrbRed();
        this.energy = new EnemyEnergyManager(5);
        this.animation = new SpriterAnimation("img/monsters/KMR/KMR.scml");
        this.energyString = "[R]";
        this.type = EnemyType.BOSS;
//        bigAnimation.setVisible(false);
    }


    public void generateDeck() {
        AbstractBossDeckArchetype archetype = new ArchetypeKMR();
        archetype.initialize();
        this.chosenArchetype = archetype;
    }

    public void loseBlock(int amount) {
        super.loseBlock(amount);
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.sound.stop("GrandBattle");
        this.energy.recharge();
        addToBot((AbstractGameAction) new DelayedActionAction((AbstractGameAction) new CharbossTurnstartDrawAction()));
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        CardCrawlGame.sound.playAndLoop("GrandBattle");
        (AbstractDungeon.getCurrRoom()).cannotLose = true;
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new SFXAction("KMR1"));
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new TalkAction((AbstractCreature) this, DIALOG[0]));
        if (AbstractDungeon.ascensionLevel >= 19) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) this, (AbstractCreature) this, (AbstractPower) new InvinciblePower((AbstractCreature) this, 300), 300));
        } else if (AbstractDungeon.ascensionLevel >= 4) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) this, (AbstractCreature) this, (AbstractPower) new InvinciblePower((AbstractCreature) this, 405), 405));
        } else {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) this, (AbstractCreature) this, (AbstractPower) new InvinciblePower((AbstractCreature) this, 500), 500));
        }
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction((AbstractCreature) this, (AbstractCreature) this, (AbstractPower) new LionSanctuaryPower((AbstractCreature) this, 3), 3));
        this.chosenArchetype.addedPreBattle();
    }

    public void takeTurn() {
        if (this.nextMove == 20) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new VFXAction((AbstractCreature) this, (AbstractGameEffect) new IntenseZoomEffect(this.hb.cX, this.hb.cY, true), 0.05F, true));
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ChangeStateAction(this, "REBIRTH"));
            setMove((byte) 0, Intent.NONE);
        } else {
            super.takeTurn();
        }
    }

    @Override
    public void changeState(String key) {
        super.changeState(key);
        switch (key) {
            case "REBIRTH":
                if (AbstractDungeon.ascensionLevel >= 19) {
                    this.maxHealth = 2000;
                } else if (AbstractDungeon.ascensionLevel >= 4) {
                    this.maxHealth = 1750;
                } else {
                    this.maxHealth = 1500;
                }
                this.halfDead = false;
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ShoutAction((AbstractCreature) this, DIALOG[1]));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new SFXAction("KMR2"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new HealAction((AbstractCreature) this, (AbstractCreature) this, this.maxHealth));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new CanLoseAction());
                AbstractBossDeckArchetype archetype = new ArchetypeKMR2();
                archetype.initialize();
                this.chosenArchetype = archetype;
                this.chosenArchetype.addedPreBattle();
//                addToBot(new AnimationAction(bigAnimation, "extra", 3.0F, false));
                break;
            default:
                break;
        }
    }

    public void onPlayAttackCardSound() {
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        if (this.currentHealth <= 0 && !this.halfDead) {
            if ((AbstractDungeon.getCurrRoom()).cannotLose == true) {
                this.halfDead = true;
                for (AbstractPower p : this.powers)
                    p.onDeath();
                for (AbstractRelic r : AbstractDungeon.player.relics)
                    r.onMonsterDeath(this);
                addToTop((AbstractGameAction) new ClearCardQueueAction());
                for (Iterator<AbstractPower> s = this.powers.iterator(); s.hasNext(); ) {
                    AbstractPower p = s.next();
                    if (p.type == AbstractPower.PowerType.DEBUFF || p.ID.equals(LionSanctuaryPower.POWER_ID))
                        s.remove();
                }
                setMove((byte) 20, AbstractMonster.Intent.UNKNOWN);
                createIntent();
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new SetMoveAction(this, (byte) 20, AbstractMonster.Intent.UNKNOWN));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new CanLoseAction());
                applyPowers();
            }
        }
    }


    public void die() {
        if (!(AbstractDungeon.getCurrRoom()).cannotLose) {
            super.die();
            onBossVictoryLogic();
            onFinalBossVictoryLogic();
            CardCrawlGame.stopClock = true;
            CardCrawlGame.sound.stop("GrandBattle");
        }
    }


    public void render(SpriteBatch sb) {
        super.render(sb);
    }
}


