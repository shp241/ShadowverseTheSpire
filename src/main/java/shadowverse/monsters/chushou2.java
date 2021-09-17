package shadowverse.monsters;

import basemod.abstracts.CustomMonster;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import shadowverse.powers.chushouHealPower;

public class chushou2 extends CustomMonster {
    public static final String ID = "shadowverse:chushou2";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("shadowverse:chushou2");
    public static final String NAME = monsterStrings.NAME;

    public static final String[] MOVES = monsterStrings.MOVES;

    public static final String[] DIALOG = monsterStrings.DIALOG;

    public static final int HP_MIN = 60;

    public static final int HP_MAX = 62;

    public static final int A_2_HP_MIN = 64;

    public static final int A_2_HP_MAX = 66;

    private int blockAmt;

    public static final int STRIKE_DMG = 10;

    private static final byte STRIKE = 1;

    private static final byte BLOCK = 2;


    public chushou2(float x, float y) {
        super(NAME, ID, AbstractDungeon.monsterHpRng.random(60, 62), -5.0F, -20.0F, 145.0F, 280.0F, null, x, y);
        this.animation = new SpriterAnimation("img/monsters/chushou2/chushou2.scml");
        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(60, 62);
        } else {
            setHp(64, 66);
        }
        if (AbstractDungeon.ascensionLevel >= 19) {
            this.blockAmt = 24;
        } else if (AbstractDungeon.ascensionLevel >= 4) {
            this.blockAmt = 22;
        } else {
            this.blockAmt = 20;
        }
        this.damage.add(new DamageInfo((AbstractCreature)this, this.STRIKE_DMG));
    }

    @Override
    public void usePreBattleAction() {
        if (AbstractDungeon.ascensionLevel >= 19) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 3)));
        } else {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 2)));
        }
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new chushouHealPower((AbstractCreature)this, 80)));
    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new BiteEffect(AbstractDungeon.player.hb.cX +

                        MathUtils.random(-50.0F, 50.0F) * Settings.scale, AbstractDungeon.player.hb.cY +
                        MathUtils.random(-50.0F, 50.0F) * Settings.scale, Color.CHARTREUSE
                        .cpy()), 0.2F));
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
                        .get(0), AbstractGameAction.AttackEffect.NONE));
                break;
            case 2:
                for (AbstractMonster m : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    if (!m.isDead && !m.isDying && !m.isEscaping){
                        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)this, this.blockAmt));
                    }
                }
                break;
        }
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) {
        if (num < 50 && !lastTwoMoves((byte)1)) {
            setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
            return;
        }
        if (!lastTwoMoves((byte)2)) {
            setMove((byte)2, Intent.DEFEND);
            return;
        }
        setMove((byte)1, Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base);
    }

}
