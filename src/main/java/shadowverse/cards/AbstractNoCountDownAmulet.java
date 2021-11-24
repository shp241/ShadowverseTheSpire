package shadowverse.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import shadowverse.orbs.AmuletOrb;

public interface AbstractNoCountDownAmulet {
    void onStartOfTurn(AmuletOrb paramOrb);
    void onEvoke(AmuletOrb paramOrb);
    void endOfTurn(AmuletOrb paramOrb);
    void onGainedBlock(int blockAmt,AmuletOrb paramOrb);
    void onOtherCardPlayed(AbstractCard c,AmuletOrb paramOrb);
    int onHeal(int healAmount,AmuletOrb paramOrb);
}
