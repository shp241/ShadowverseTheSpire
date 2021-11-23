package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import shadowverse.action.ReduceAllCountDownAction;
import shadowverse.characters.Bishop;
import shadowverse.powers.RaPower;
import shadowverse.powers.RealmOfReposePower;

public class OmenOfRepose
        extends CustomCard {
    public static final String ID = "shadowverse:OmenOfRepose";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfRepose");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmenOfRepose.png";

    public OmenOfRepose() {
        super(ID, NAME, IMG_PATH, 3, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("OmenOfRepose"));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new MiracleEffect(Color.GOLD.cpy(),Color.WHITE.cpy(),"HEAL_3")));
        addToBot((AbstractGameAction)new HealAction(p,p,1));
        addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new DrawCardNextTurnPower(p,1),1));
        addToBot((AbstractGameAction)new ReduceAllCountDownAction(1));
        addToBot((AbstractGameAction)new ApplyPowerAction(p,p,(AbstractPower)new RealmOfReposePower(p,2),2));
        addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
        addToBot((AbstractGameAction)new PressEndTurnButtonAction());
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OmenOfRepose();
    }
}


