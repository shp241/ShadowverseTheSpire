package shadowverse.cards;

import basemod.abstracts.CustomCard;
import charbosses.actions.RealWaitAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.PlaceAmulet;
import shadowverse.orbs.AmuletOrb;

import java.util.function.Predicate;


public abstract class AbstractAmuletCard extends CustomCard {

    public int countDown;

    public AbstractAmuletCard(String id, String name, String img, int cost, String rawDescription, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, CardType.POWER, color, rarity, target);
    }

    public abstract void onStartOfTurn(AmuletOrb paramOrb);

    public abstract void onEvoke(AmuletOrb paramOrb);

    public abstract void endOfTurn(AmuletOrb paramOrb);


    protected void upgradeBaseCountDown(int newCountDown) {
        this.countDown = newCountDown;
    }

}
