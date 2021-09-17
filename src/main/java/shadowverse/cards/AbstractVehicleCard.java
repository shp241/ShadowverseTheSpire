package shadowverse.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.function.Predicate;


public abstract class AbstractVehicleCard extends CustomCard {
    public boolean maneuver = false;
    public Predicate<AbstractCard> predicate;

    public AbstractVehicleCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

}
