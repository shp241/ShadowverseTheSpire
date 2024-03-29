package shadowverse.cards;

import basemod.abstracts.CustomCard;

public abstract class AbstractEndTurnInvocationCard extends CustomCard {
    public AbstractEndTurnInvocationCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }
    public abstract void atEndOfTurn(boolean isPlayer);
}
