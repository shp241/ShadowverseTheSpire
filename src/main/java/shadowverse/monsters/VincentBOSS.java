package shadowverse.monsters;

import basemod.abstracts.CustomMonster;
import basemod.animations.SpriterAnimation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.ShoutAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;
import shadowverse.powers.*;

public class VincentBOSS extends CustomMonster {
    public static final String ID = "shadowverse:VincentBOSS";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("shadowverse:VincentBOSS");
    public static final String NAME = monsterStrings.NAME;

    public static final String[] MOVES = monsterStrings.MOVES;

    public static final String[] DIALOG = monsterStrings.DIALOG;

    private int aDmg;

    private int bDmg;

    private int strAmt;

    private int debuffAmt;

    private int blockAmt;

    private int rapidFireAmt;

    private int fireDmg;

    private int fireAmt;

    private boolean firstTurn = true;



    public VincentBOSS() {
        super(NAME, ID, 450, 0.0F, -30F, 230.0F, 450.0F, null, 80.0F, -30.0F);
        this.animation = new SpriterAnimation("img/monsters/VincentBOSS/VincentBOSS.scml");
        this.type = EnemyType.BOSS;
        if (AbstractDungeon.ascensionLevel >= 8) {
            setHp(500, 500);
        } else {
            setHp(450, 450);
        }
        if (AbstractDungeon.ascensionLevel >= 19) {
            this.debuffAmt = 4;
            this.blockAmt = 30;
            this.aDmg = 28;
            this.bDmg = 15;
            this.strAmt = 2;
            this.fireDmg = 2;
            this.fireAmt = 4;
            this.rapidFireAmt = 6;
        } else if (AbstractDungeon.ascensionLevel >= 4) {
            this.debuffAmt = 3;
            this.blockAmt = 28;
            this.aDmg = 26;
            this.bDmg = 13;
            this.fireDmg = 1;
            this.fireAmt = 4;
            this.strAmt = 1;
            this.rapidFireAmt = 5;
        } else {
            this.debuffAmt = 3;
            this.blockAmt = 26;
            this.aDmg = 24;
            this.bDmg = 11;
            this.fireDmg = 1;
            this.fireAmt = 3;
            this.strAmt = 1;
            this.rapidFireAmt = 4;
        }
        this.damage.add(new DamageInfo((AbstractCreature)this, aDmg));
        this.damage.add(new DamageInfo((AbstractCreature)this, bDmg));
        this.damage.add(new DamageInfo((AbstractCreature)this,fireDmg));
    }

    @Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BEYOND");
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new TimeWarpPower((AbstractCreature)this)));
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[0]));
        addToBot((AbstractGameAction)new SFXAction("Vincent"));
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new JudgmentWordPower((AbstractCreature)AbstractDungeon.player)));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player,(AbstractCreature)this,(AbstractPower)new NoDamage((AbstractCreature)AbstractDungeon.player)));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player,(AbstractCreature)this,(AbstractPower)new ManaPower((AbstractCreature)AbstractDungeon.player,0)));
    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, DIALOG[4], 1.0F, 2.0F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new SFXAction("Vincent_A4"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
                        .get(1), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FreezePower((AbstractCreature)AbstractDungeon.player)));
                setMove((byte)2, Intent.ATTACK,((DamageInfo)this.damage.get(0)).base);
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[2]));
                addToBot((AbstractGameAction)new SFXAction("Vincent_A2"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new ViceCrushEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.5F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
                        .get(0), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                setMove((byte)3, Intent.DEFEND_BUFF);
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.blockAmt));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.strAmt), this.strAmt));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new RapidFirePower((AbstractCreature)AbstractDungeon.player,this.rapidFireAmt,this)));
                setMove( (byte)4, Intent.STRONG_DEBUFF);
                break;
            case 4:
                addToBot((AbstractGameAction)new SFXAction("JudgmentWord"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[3]));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RemoveDebuffsAction((AbstractCreature)this));
                for (AbstractPower p : AbstractDungeon.player.powers) {
                    if (p instanceof ManaPower){
                        if (p.amount>0){
                            addToBot((AbstractGameAction)new ReducePowerAction(AbstractDungeon.player,AbstractDungeon.player,p,1));
                            break;
                        }
                    }
                    if (p.type == AbstractPower.PowerType.BUFF&&p.ID!=StrengthPower.POWER_ID&&p.ID!=DexterityPower.POWER_ID&&p.ID!=JudgmentWordPower.POWER_ID&&p.ID!= Cemetery.POWER_ID&&p.ID!=NaterranTree.POWER_ID)
                        addToTop((AbstractGameAction)new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, p.ID));
                }
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new JudgmentWordPower((AbstractCreature)AbstractDungeon.player)));
                setMove((byte)5, Intent.ATTACK_DEBUFF,((DamageInfo)this.damage.get(2)).base,this.fireAmt,true);
                break;
            case 5:
                addToBot((AbstractGameAction)new SFXAction("Vincent_A3"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[6]));
                for (int i = 0;i<this.fireAmt;i++) {
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new DamageAction((AbstractCreature) AbstractDungeon.player, this.damage
                            .get(2), AbstractGameAction.AttackEffect.FIRE, true));
                    addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new ExplosionSmallEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY), 0.1F));
                }
                setMove((byte)6, Intent.DEFEND_BUFF);
                break;
            case 6:
                addToBot((AbstractGameAction)new SFXAction("Vincent_E3"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[5]));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.blockAmt));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.strAmt), this.strAmt));
                if (!this.hasPower(TimeWarpPower.POWER_ID)){
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new TimeWarpPower((AbstractCreature)this)));
                }else {
                    for (AbstractPower power:this.powers){
                        if (power instanceof TimeWarpPower){
                            power.amount = 11;
                            power.flash();
                            power.updateDescription();
                        }
                    }
                }
                setMove((byte)7, Intent.STRONG_DEBUFF);
                break;
            case 7:
                addToBot((AbstractGameAction)new SFXAction("Vincent_E1"));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, DIALOG[7]));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new FreezePower((AbstractCreature)AbstractDungeon.player)));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new VulnerablePower((AbstractCreature)AbstractDungeon.player,this.debuffAmt,true),this.debuffAmt));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new RapidFirePower((AbstractCreature)AbstractDungeon.player,this.rapidFireAmt,this)));
                setMove((byte)8, Intent.ATTACK_DEBUFF,((DamageInfo)this.damage.get(1)).base);
                break;
            case 8:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)this, (AbstractCreature)this, this.blockAmt));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new StrengthPower((AbstractCreature)this, this.strAmt), this.strAmt));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new RapidFirePower((AbstractCreature)AbstractDungeon.player,this.rapidFireAmt,this)));
                setMove((byte)1, Intent.ATTACK_DEBUFF,((DamageInfo)this.damage.get(1)).base);
                break;
        }
    }

    @Override
    protected void getMove(int i) {
        if (this.firstTurn) {
            this.firstTurn = false;
            setMove((byte)1, Intent.ATTACK_DEBUFF,((DamageInfo)this.damage.get(1)).base);
            return;
        }
    }


    @Override
    public void die() {
        super.die();
        useFastShakeAnimation(5.0F);
        CardCrawlGame.screenShake.rumble(4.0F);
        onBossVictoryLogic();
        onFinalBossVictoryLogic();
    }
}
