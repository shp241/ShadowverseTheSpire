package shadowverse.action;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;
import shadowverse.orbs.AmuletOrb;
import shadowverse.powers.CarnivalNecromancerPower;
import shadowverse.powers.Cemetery;
import shadowverse.powers.EverdarkStrixPower;

import java.util.ArrayList;
import java.util.HashMap;

public class ReduceCountDownAction extends AbstractGameAction {
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:ReduceCountDown")).TEXT;

    private AbstractPlayer p = AbstractDungeon.player;
    private ArrayList<AbstractOrb> cannotChose = new ArrayList<>();
    private HashMap<AbstractCard,Integer> map = new HashMap<>();
    private boolean retrieveCard = false;

    public ReduceCountDownAction(int amount){
        this.actionType = ActionType.CARD_MANIPULATION;
        this.amount = amount;
        this.duration = 0.25F;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.orbs.size() == 0) {
                this.isDone = true;
                return;
            }
            for (AbstractOrb o : this.p.orbs) {
                if (!(o instanceof AmuletOrb)) {
                    this.cannotChose.add(o);
                }
            }
            if (this.cannotChose.size() == this.p.orbs.size()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.group.size() > this.amount) {
                AbstractDungeon.cardRewardScreen.customCombatOpen(generateCardChoices(), TEXT[0], false);
                tickDuration();
                return;
            }
        }
        if (!this.retrieveCard) {
            if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard;
                AbstractOrb orb = p.orbs.get(map.get(disCard));
                if (orb instanceof AmuletOrb){
                    for (int i=0;i<this.amount;i++){
                        if (orb.passiveAmount > 0) {
                            orb.passiveAmount--;
                            orb.evokeAmount--;
                            orb.updateDescription();
                        }
                        for (int j = 0; j < 10; j++)
                            AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(orb.tX, orb.tY, Color.YELLOW));
                        if (orb.passiveAmount <= 0){
                            AbstractDungeon.actionManager.addToTop((AbstractGameAction) new StasisEvokeIfRoomInHandAction((AmuletOrb) orb));
                            break;
                        }
                    }
                }
                AbstractDungeon.cardRewardScreen.discoveryCard = null;
            }
            this.retrieveCard = true;
        }
        tickDuration();
    }

    private ArrayList<AbstractCard> generateCardChoices()  {
        ArrayList<AbstractCard> derp = new ArrayList<>();
        for (int i = 0;i<p.orbs.size();i++){
            AbstractOrb o = p.orbs.get(i);
            if (o instanceof AmuletOrb){
                AbstractCard c = ((AmuletOrb) o).amulet;
                this.map.put(c,i);
                derp.add(c);
            }
        }
        return derp;
    }
}
