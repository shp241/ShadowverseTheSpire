package shadowverse.relics;


import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.action.PlaceAmulet;
import shadowverse.patch.CharacterSelectScreenPatches;
import shadowverse.powers.DisableEffectDamagePower;


public class YukariBOSS
        extends CustomRelic {
    public static final String ID = "shadowverse:YukariBOSS";
    public static final String IMG = "img/relics/YukariBOSS.png";
    public static final String OUTLINE_IMG = "img/relics/outline/ArisaBOSS_Outline.png";
    AbstractPlayer p = AbstractDungeon.player;

    public YukariBOSS() {
        super(ID, ImageMaster.loadImage(IMG), RelicTier.BOSS, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public int onPlayerHeal(int healAmount) {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            flash();
            addToBot((AbstractGameAction) new ApplyPowerAction(p, p, new DisableEffectDamagePower(p, 1)));
        }
        return super.onPlayerHeal(healAmount);
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.type == AbstractCard.CardType.CURSE || drawnCard.type == AbstractCard.CardType.STATUS){
            addToTop((AbstractGameAction)new DrawCardAction(1));
        }
    }

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(Offensive7.ID)) {
            instantObtain(AbstractDungeon.player, 0, false);
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(Offensive7.ID)
                && (CharacterSelectScreenPatches.characters[5]).reskinCount == 1;
    }

    public AbstractRelic makeCopy() {
        return (AbstractRelic) new YukariBOSS();
    }
}


