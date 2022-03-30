package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import shadowverse.Shadowverse;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.cards.Temp.ArsMagna;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.EarthEssence;
import shadowverse.powers.YukishimaPower;

public class Yukishima
        extends CustomCard {
    public static final String ID = "shadowverse:Yukishima";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Yukishima");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Yukishima.png";

    public Yukishima() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(4)) {
            setCostForTurn(4);
        } else {
            if (this.costForTurn != 0) {
                setCostForTurn(2);
            }
        }
        super.update();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new ShockWaveEffect(p.hb.cX,p.hb.cY, Color.WHITE, ShockWaveEffect.ShockWaveType.ADDITIVE)));
        if (this.costForTurn == 4 && Shadowverse.Enhance(4)) {
            addToBot(new SFXAction("Yukishima_EH"));
            if (p instanceof AbstractShadowversePlayer){
                int dmg = ((AbstractShadowversePlayer) AbstractDungeon.player).totalDrawAmt;
                addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, DamageInfo.createDamageMatrix(dmg, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE, true));
            }
        } else {
            addToBot(new SFXAction("Yukishima"));
        }
        addToBot(new ApplyPowerAction(p,p,new YukishimaPower(p)));
        if (this.upgraded){
            addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(),1));
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (this.upgraded) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + ((AbstractShadowversePlayer) AbstractDungeon.player).totalDrawAmt + cardStrings.EXTENDED_DESCRIPTION[1];
        }
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        if (this.upgraded) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new Yukishima();
    }
}
