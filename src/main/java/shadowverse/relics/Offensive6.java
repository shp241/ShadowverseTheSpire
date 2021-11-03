package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import shadowverse.action.MinionBuffAction;
import shadowverse.action.MinionSummonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.cards.Temp.Horse;
import shadowverse.cards.Temp.Jeep;
import shadowverse.cards.Temp.Motorbike;
import shadowverse.orbs.Knight;
import shadowverse.orbs.Minion;

import java.util.ArrayList;

public class Offensive6 extends CustomRelic {
    public static final String ID = "shadowverse:Offensive6";
    public static final String IMG = "img/relics/Offensive.png";
    public static final String OUTLINE_IMG = "img/relics/outline/Offensive_Outline.png";

    public Offensive6() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.SPECIAL, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {
        flash();
        AbstractPlayer p = AbstractDungeon.player;
        if (p.orbs.get(0) instanceof EmptyOrbSlot) {
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new Knight()));
        }
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        this.counter = rally();
    }

    @Override
    public void onPlayerEndTurn() {
        if (rally() != this.counter) {
            this.counter = rally();
        }
    }

    public int rally() {
        int rally = 0;

        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Minion) {
                rally++;
            }
        }

        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.type == AbstractCard.CardType.ATTACK && !(c.hasTag(AbstractCard.CardTags.STRIKE))) {
                rally++;
            }
        }
        return rally;
    }

    @Override
    public void onVictory() {
        this.counter = -1;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new Offensive6();
    }
}