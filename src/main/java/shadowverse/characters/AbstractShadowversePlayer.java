package shadowverse.characters;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.AbstractAnimation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.AttackFromDeckToHandAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;
import shadowverse.Shadowverse;
import shadowverse.action.RemoveMinionAction;
import shadowverse.action.TreAction;
import shadowverse.powers.*;
import shadowverse.stance.Vengeance;

import java.util.Iterator;

public abstract class AbstractShadowversePlayer extends CustomPlayer {

    public static class Enums {
        @SpireEnum
        public static AbstractCard.CardTags MACHINE;
        @SpireEnum
        public static AbstractCard.CardTags LEVIN;
        @SpireEnum
        public static AbstractCard.CardTags ACCELERATE;
        @SpireEnum
        public static AbstractCard.CardTags CRYSTALLIZE;
        @SpireEnum
        public static AbstractCard.CardTags NATURAL;
        @SpireEnum
        public static AbstractCard.CardTags FUSION;
        @SpireEnum
        public static AbstractCard.CardTags ENHANCE;

        @SpireEnum
        public static AbstractCard.CardTags SPELL_BOOST;
        @SpireEnum
        public static AbstractCard.CardTags SPELL_BOOST_ATTACK;
        @SpireEnum
        public static AbstractCard.CardTags EARTH_RITE;
        @SpireEnum
        public static AbstractCard.CardTags MYSTERIA;
        @SpireEnum
        public static AbstractCard.CardTags LASTWORD;
        @SpireEnum
        public static AbstractCard.CardTags ARTIFACT;
        @SpireEnum
        public static AbstractCard.CardTags AMULET_FOR_ONECE;
        @SpireEnum
        public static AbstractCard.CardTags LEGEND;
        @SpireEnum
        public static AbstractCard.CardTags MINION;
        @SpireEnum
        public static AbstractCard.CardTags FES;
        @SpireEnum
        public static AbstractCard.CardTags HERO;
        @SpireEnum
        public static AbstractCard.CardTags LUMINOUS;
    }

    public int earthCount = 0;
    public int naterranCount = 0;
    public int mechaCount = 0;
    public int mysteriaCount = 0;
    public int wrathCount = 0;
    public int drawAmt = 0;
    public int wrathThisTurn = 0;
    public int wrathLastTurn = 0;
    public int resonanceCount = 0;
    public int necromanceCount = 0;
    public int amuletCount = 0;
    public int healCount = 0;
    public int totalDrawAmt = 0;
    public int costUsedAmt = 0;

    public AbstractShadowversePlayer(String name, PlayerClass playerClass, String[] orbTextures, String orbVfxPath, float[] layerSpeeds, AbstractAnimation animation) {
        super(name, playerClass, orbTextures, orbVfxPath, layerSpeeds, animation);
    }

    public AbstractShadowversePlayer(String name, PlayerClass playerClass, CustomEnergyOrb energyOrb, AbstractAnimation animation) {
        super(name, playerClass, energyOrb, animation);
    }

    public void onVictory() {
        super.onVictory();
        this.naterranCount = 0;
        this.earthCount = 0;
        this.mechaCount = 0;
        this.mysteriaCount = 0;
        this.wrathCount = 0;
        this.drawAmt = 0;
        this.wrathThisTurn = 0;
        this.resonanceCount = 0;
        this.necromanceCount = 0;
        this.amuletCount = 0;
        this.healCount = 0;
        this.totalDrawAmt = 0;
        this.costUsedAmt = 0;
        this.wrathLastTurn = 0;
    }

    public void useCard(AbstractCard c, AbstractMonster monster, int energyOnUse) {
        super.useCard(c, monster, energyOnUse);
        costUsedAmt += c.costForTurn;
        if (!this.hasPower(Cemetery.POWER_ID))
            this.powers.add((AbstractPower) new Cemetery(this, 0));
    }

    public void damage(DamageInfo info) {
        super.damage(info);
        int amt = info.output;
        if (amt > 0) {
            wrathLastTurn++;
        }
        if (info.owner == this && amt >= 0) {
            wrathCount++;
            wrathThisTurn++;
            if (wrathCount >= 7 && !this.hasPower(WrathPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new WrathPower(this)));
            }
        }
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && this.currentHealth <= this.maxHealth / 2.0F && !(this instanceof Nemesis)) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ChangeStanceAction((AbstractStance) new Vengeance()));
        }
    }

    @Override
    public void draw() {
        super.draw();
        totalDrawAmt++;
        if (!this.hasPower(AvaricePower.POWER_ID))
            this.drawAmt++;
        if (this.drawAmt > 5) {
            this.drawAmt = 0;
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ApplyPowerAction(this, this, (AbstractPower) new AvaricePower(this)));
        }
    }

    @Override
    public void applyStartOfTurnPreDrawCards() {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new TreAction());
        super.applyStartOfTurnPreDrawCards();
        this.drawAmt = 0;
        this.wrathThisTurn = 0;
    }


    @Override
    public void applyStartOfTurnPostDrawRelics() {
        super.applyStartOfTurnPostDrawRelics();
    }

    public void applyStartOfCombatLogic() {
        super.applyStartOfCombatLogic();
        if ((this.currentHealth <= this.maxHealth / 2.0F || this.maxHealth == 1) && !(this instanceof Nemesis)) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ChangeStanceAction((AbstractStance) new Vengeance()));
        }
    }

    @Override
    public void renderPowerTips(SpriteBatch sb) {
        super.renderPowerTips(sb);
    }

    @Override
    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
        AbstractDungeon.actionManager.addToBottom(new RemoveMinionAction());
        wrathLastTurn = 0;
    }


    @Override
    public void heal(int healAmount) {
        healCount++;
        super.heal(healAmount);
        if (this.currentHealth > this.maxHealth / 2 && !this.hasPower(VengeanceHealthPower.POWER_ID) && !this.hasPower(ExitVengeancePower.POWER_ID))
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction) new ChangeStanceAction((AbstractStance) new NeutralStance()));
    }

    public static shadowverse.animation.AbstractAnimation getBigAnimation() {
        return null;
    }
}
