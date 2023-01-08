package shadowverse.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.powers.CutthroatPower;

public abstract class AbstractEnhanceCard extends CustomCard {
    public int enhanceCost;
    public int baseCost;
    public int exEnhanceCost;

    public AbstractEnhanceCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target,
                               int enhanceCost, int exEnhanceCost) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.baseCost = cost;
        this.enhanceCost = enhanceCost;
        this.exEnhanceCost = exEnhanceCost;
    }

    public AbstractEnhanceCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target,
                               int enhanceCost) {
        this(id, name, img, cost, rawDescription, type, color, rarity, target, enhanceCost, enhanceCost);
    }


    @Override
    public void update() {
        super.update();
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            if (Shadowverse.Enhance(exEnhanceCost)) {
                setCostForTurn(exEnhanceCost);
            } else if (Shadowverse.Enhance(enhanceCost)) {
                setCostForTurn(enhanceCost);
            } else {
                setCostForTurn(baseCost);
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (enhanceCost != exEnhanceCost && this.costForTurn == exEnhanceCost && Shadowverse.Enhance(exEnhanceCost)) {
            exEnhanceUse(p,m);
            if (p.hasPower(CutthroatPower.POWER_ID)){
                addToBot(new GainEnergyAction(exEnhanceCost-1));
            }
        } else if (this.costForTurn == enhanceCost && Shadowverse.Enhance(enhanceCost)) {
            enhanceUse(p,m);
            if (p.hasPower(CutthroatPower.POWER_ID)){
                addToBot(new GainEnergyAction(enhanceCost-1));
            }
        } else {
            baseUse(p,m);
        }
    }

    public abstract void exEnhanceUse(AbstractPlayer p, AbstractMonster m);

    public abstract void enhanceUse(AbstractPlayer p, AbstractMonster m);

    public abstract void baseUse(AbstractPlayer p, AbstractMonster m);


}
