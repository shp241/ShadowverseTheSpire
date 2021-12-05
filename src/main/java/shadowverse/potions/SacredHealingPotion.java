package shadowverse.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.action.ReduceAllCountDownAction;

public class SacredHealingPotion extends CustomPotion {
    public static final String POTION_ID = "shadowverse:SacredHealingPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("shadowverse:SacredHealingPotion");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;


    public SacredHealingPotion() {
        super(NAME, POTION_ID, PotionRarity.RARE,  AbstractPotion.PotionSize.H, AbstractPotion.PotionColor.WHITE);
        this.labOutlineColor = CardHelper.getColor(239, 236, 186);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
            addToBot((AbstractGameAction)new HealAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, (int)(AbstractDungeon.player.maxHealth * this.potency / 100.0F)));
        } else {
            AbstractDungeon.player.heal((int)(AbstractDungeon.player.maxHealth * this.potency / 100.0F));
        }
    }

    public boolean canUse() {
        if (AbstractDungeon.actionManager.turnHasEnded &&
                (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT)
            return false;
        if ((AbstractDungeon.getCurrRoom()).event != null &&
                (AbstractDungeon.getCurrRoom()).event instanceof com.megacrit.cardcrawl.events.shrines.WeMeetAgain)
            return false;
        return true;
    }

    @Override
    public int getPotency(int i) {
        return 20;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new SacredHealingPotion();
    }
}
