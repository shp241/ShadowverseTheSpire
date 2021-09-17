package shadowverse.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.action.ReanimateAction;

public class ReanimatePotion extends CustomPotion {
    public static final String POTION_ID = "shadowverse:ReanimatePotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("shadowverse:ReanimatePotion");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;


    public ReanimatePotion() {
        super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.BOLT, PotionColor.WEAK);
        this.labOutlineColor = CardHelper.getColor(71, 26, 106);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = getPotency();
        if (AbstractDungeon.player == null || !AbstractDungeon.player.hasRelic("SacredBark")) {
            this.description = potionStrings.DESCRIPTIONS[0];
        } else {
            this.description = potionStrings.DESCRIPTIONS[1];
        }
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT){
            addToBot((AbstractGameAction)new ReanimateAction(3));
            if (this.potency>1)
                addToBot((AbstractGameAction)new ReanimateAction(3));
        }
    }



    @Override
    public int getPotency(int i) {
        return 1;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new ReanimatePotion();
    }
}
