package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.action.MinionSummonAction;
import shadowverse.orbs.Knight;
import shadowverse.orbs.Minion;

public class Conscription extends CustomRelic {
    public static final String ID = "shadowverse:Conscription";
    public static final String IMG = "img/relics/Conscription.png";
    public static final String OUTLINE_IMG = "img/relics/outline/Conscription_Outline.png";

    public Conscription() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.COMMON, LandingSound.CLINK);
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
        return new Conscription();
    }
}

