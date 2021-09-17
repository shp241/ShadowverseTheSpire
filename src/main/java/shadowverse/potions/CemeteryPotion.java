package shadowverse.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.powers.Cemetery;

public class CemeteryPotion extends CustomPotion {
    public static final String POTION_ID = "shadowverse:CemeteryPotion";
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("shadowverse:CemeteryPotion");
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;
    public static final PowerStrings powStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:Cemetery");


    public CemeteryPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.SPHERE, PotionColor.FIRE);
        this.labOutlineColor = CardHelper.getColor(71, 26, 106);
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.tips.add(new PowerTip(

                TipHelper.capitalize(powStrings.NAME), powStrings.DESCRIPTIONS[0]));
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT){
            addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,(AbstractPower)new Cemetery(AbstractDungeon.player,this.potency),this.potency));
        }
    }



    @Override
    public int getPotency(int i) {
        return 5;
    }

    @Override
    public AbstractPotion makeCopy() {
        return new CemeteryPotion();
    }
}
