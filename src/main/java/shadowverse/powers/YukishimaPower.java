package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class YukishimaPower extends AbstractPower {
    public static final String POWER_ID = "shadowverse:YukishimaPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:YukishimaPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public YukishimaPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = -1;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/YukishimaPower.png");
    }


    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void onCardDraw(AbstractCard card) {
        addToBot((AbstractGameAction) new DamageRandomEnemyAction(new DamageInfo(this.owner, 1, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractCard c = AbstractDungeon.player.hand.getRandomCard(AbstractCard.CardType.ATTACK,true);
        if (c!=null){
            c.baseDamage+=1;
            c.baseBlock+=1;
            c.applyPowers();
            c.superFlash();
        }
    }

}

