package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import com.megacrit.cardcrawl.vfx.combat.HeartMegaDebuffEffect;
import shadowverse.action.SkullFaneAction;
import shadowverse.cards.AbstractAmuletCard;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.AbstractNoCountDownAmulet;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;
import shadowverse.powers.AbdielPower;

public class SkullFane
        extends CustomCard {
    public static final String ID = "shadowverse:SkullFane";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SkullFane");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SkullFane.png";

    public SkullFane() {
        super(ID, NAME, IMG_PATH, 7, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.baseBlock = 4;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c instanceof AbstractAmuletCard || c instanceof AbstractNoCountDownAmulet || (c instanceof AbstractCrystalizeCard && c.type==CardType.POWER)){
            addToBot((AbstractGameAction)new ReduceCostForTurnAction(this,1));
        }
    }

    @Override
    public void triggerWhenDrawn() {
        for (AbstractOrb o : AbstractDungeon.player.orbs){
            if (o instanceof AmuletOrb){
                if (((AmuletOrb) o).amulet.type==CardType.CURSE)
                    addToBot((AbstractGameAction)new ReduceCostForTurnAction(this,1));
            }
        }
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("SkullFane"));
        addToBot((AbstractGameAction)new VFXAction(new HeartMegaDebuffEffect()));
        addToBot((AbstractGameAction)new SkullFaneAction(this.magicNumber,this.block));
        this.cost = 7;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new SkullFane();
    }
}


