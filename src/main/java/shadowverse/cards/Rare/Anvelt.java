package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import charbosses.actions.RealWaitAction;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import com.megacrit.cardcrawl.vfx.combat.SweepingBeamEffect;
import shadowverse.Shadowverse;
import shadowverse.action.PlaceAmulet;
import shadowverse.cards.AbstractCrystalizeCard;
import shadowverse.cards.Temp.ElanaPrayer;
import shadowverse.characters.Bishop;
import shadowverse.orbs.AmuletOrb;

public class Anvelt
        extends CustomCard implements AbstractCrystalizeCard {
    public static final String ID = "shadowverse:Anvelt";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Anvelt");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Anvelt.png";

    public Anvelt() {
        super(ID, NAME, IMG_PATH, 4, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.RARE, CardTarget.ALL);
        this.baseBlock = 30;
        this.baseDamage = 12;
        this.isMultiDamage = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(6);
            upgradeDamage(3);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Accelerate(this)){
            setCostForTurn(0);
            this.type = CardType.SKILL;
        }else {
            if (this.type==CardType.SKILL){
                setCostForTurn(4);
                this.type = CardType.ATTACK;
            }
        }
        super.update();
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (this.type==CardType.POWER && this.costForTurn == 0){
            addToBot((AbstractGameAction)new SFXAction("Anvelt_Acc"));
        }else {
            addToBot((AbstractGameAction)new SFXAction("Anvelt"));
            addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
            addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new SweepingBeamEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.flipHorizontal), 0.4F));
            addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Anvelt();
    }

    @Override
    public void onStartOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onEvoke(AmuletOrb paramOrb) {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot((AbstractGameAction)new SFXAction("Anvelt"));
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)p, (AbstractGameEffect)new SweepingBeamEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractDungeon.player.flipHorizontal), 0.4F));
        addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    }

    @Override
    public void endOfTurn(AmuletOrb paramOrb) {

    }

    @Override
    public void onGainedBlock(int blockAmt, AmuletOrb paramOrb) {
        if (paramOrb.passiveAmount>0){
            paramOrb.onStartOfTurn();
        }
    }

    @Override
    public void onOtherCardPlayed(AbstractCard c, AmuletOrb paramOrb) {

    }

    @Override
    public int onHeal(int healAmount, AmuletOrb paramOrb) {
        return healAmount;
    }

    @Override
    public int returnCountDown() {
        return 7;
    }
}


