package shadowverse.powers;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import shadowverse.characters.AbstractShadowversePlayer;

import java.util.ArrayList;


public class ModestPower
        extends TwoAmountPower {
    public static final String POWER_ID = "shadowverse:ModestPower";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("shadowverse:ModestPower");
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private ArrayList<String> dup = new ArrayList<>();

    public ModestPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.amount2 = 0;
        this.type = PowerType.BUFF;
        updateDescription();
        this.img = new Texture("img/powers/ModestPower.png");
    }

    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
        if (this.amount >= 999)
            this.amount = 999;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount2 + DESCRIPTIONS[2];
    }

    public void onExhaust(AbstractCard card) {
        if (card.hasTag(AbstractShadowversePlayer.Enums.ARTIFACT) && !dup.contains(card.cardID)) {
            dup.add(card.cardID);
            this.amount2++;
            updateDescription();
            AbstractDungeon.onModifyPower();
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            if (this.amount2 > 0) {
                AbstractCreature m = (AbstractCreature) AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
                if (m != null){
                    addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new LightningEffect(m.drawX, m.drawY), 0.2F));
                    addToBot((AbstractGameAction) new DamageAction(m,new DamageInfo(this.owner, this.amount * this.amount2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
                }
            }
        }
    }

}

