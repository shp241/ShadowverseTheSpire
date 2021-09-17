package shadowverse.monsters;

import basemod.abstracts.CustomMonster;
import basemod.animations.SpriterAnimation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import shadowverse.powers.chushouExplosionPower;

public class chushou1 extends CustomMonster {
    public static final String ID = "shadowverse:chushou1";
    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("shadowverse:chushou1");
    public static final String NAME = monsterStrings.NAME;

    public static final String[] MOVES = monsterStrings.MOVES;

    public static final String[] DIALOG = monsterStrings.DIALOG;

    public static final int HP_MIN = 40;

    public static final int HP_MAX = 42;

    public static final int A_2_HP_MIN = 44;

    public static final int A_2_HP_MAX = 46;

    public static final int ATTACK_DMG = 4;

    public static final int STRIKE_DMG = 20;

    private static final byte TACKLE = 1;

    private static final byte STRIKE = 2;

    private static final byte CONSTRICT = 3;

    private int debuffAmount;

    public chushou1(float x, float y) {
        super(NAME, ID, AbstractDungeon.monsterHpRng.random(40, 42), -5.0F, -20.0F, 145.0F, 280.0F, null, x, y);
        this.animation = new SpriterAnimation("img/monsters/chushou/chushou.scml");
        if (AbstractDungeon.ascensionLevel >= 9) {
            setHp(44, 46);
        } else {
            setHp(40, 42);
        }
        if (AbstractDungeon.ascensionLevel >= 19) {

            this.debuffAmount = 10;
        } else if (AbstractDungeon.ascensionLevel >= 4) {

            this.debuffAmount = 8;
        } else {
            this.debuffAmount = 6;
        }
        this.damage.add(new DamageInfo((AbstractCreature)this, this.ATTACK_DMG));
        this.damage.add(new DamageInfo((AbstractCreature)this, this.STRIKE_DMG));
    }


    @Override
    public void usePreBattleAction() {
        if (AbstractDungeon.ascensionLevel >= 19) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 3)));
        } else {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new ArtifactPower((AbstractCreature)this, 2)));
        }
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)this, (AbstractCreature)this, (AbstractPower)new chushouExplosionPower((AbstractCreature)this, 12)));
    }

    @Override
    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                for (int i = 0;i<3;i++){
                    AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
                            .get(0), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
                }
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new DamageAction((AbstractCreature)AbstractDungeon.player, this.damage
                        .get(1), AbstractGameAction.AttackEffect.SLASH_HEAVY));
                break;
            case 3:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ApplyPowerAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, (AbstractPower)new ConstrictedPower((AbstractCreature)AbstractDungeon.player, (AbstractCreature)this, this.debuffAmount )));
                break;
        }
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new RollMoveAction(this));
    }

    @Override
    protected void getMove(int num) {
        if (AbstractDungeon.ascensionLevel >= 17 &&
                !AbstractDungeon.player.hasPower("Constricted") && !lastMove((byte)3)) {
            setMove((byte)3, AbstractMonster.Intent.STRONG_DEBUFF);
            return;
        }
        if (num < 50 && !lastTwoMoves((byte)1)) {
            setMove((byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
            return;
        }
        if (!AbstractDungeon.player.hasPower("Constricted") && !lastMove((byte)3)) {
            setMove((byte)3, AbstractMonster.Intent.STRONG_DEBUFF);
            return;
        }
        if (!lastTwoMoves((byte)2)) {
            setMove((byte)2, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(1)).base);
            return;
        }
        setMove((byte)1, AbstractMonster.Intent.ATTACK, ((DamageInfo)this.damage.get(0)).base, 3, true);
    }

}
