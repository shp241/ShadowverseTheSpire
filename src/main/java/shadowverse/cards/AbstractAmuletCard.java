package shadowverse.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import shadowverse.orbs.AmuletOrb;


public abstract class AbstractAmuletCard extends CustomCard {

    public int countDown;

    public AbstractAmuletCard(String id, String name, String img, int cost, String rawDescription, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, CardType.POWER, color, rarity, target);
    }

    public abstract void onStartOfTurn(AmuletOrb paramOrb);

    public abstract void onEvoke(AmuletOrb paramOrb);

    public abstract void endOfTurn(AmuletOrb paramOrb);

    public abstract int onHeal(int healAmount, AmuletOrb paramOrb);

    public abstract void onOtherCardPlayed(AbstractCard c,AmuletOrb paramOrb);

    public abstract void onGainedBlock(int blockAmt,AmuletOrb paramOrb);

    protected void upgradeBaseCountDown(int newCountDown) {
        this.countDown = newCountDown;
    }

}
