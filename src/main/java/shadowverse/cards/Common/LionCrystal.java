package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import shadowverse.Shadowverse;
import shadowverse.cards.Temp.*;
import shadowverse.characters.Bishop;

import java.util.ArrayList;

public class LionCrystal extends CustomCard {
    public static final String ID = "shadowverse:LionCrystal";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LionCrystal");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LionCrystal.png";
    private boolean doubleCheck = false;
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new HolyShieldLion());
        list.add(new HolyPlateLion());
        list.add(new HolyKingLion());
        return list;
    }


    public LionCrystal() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.COMMON, CardTarget.NONE);
    }


    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = (AbstractCard)returnChoice().get(previewIndex).makeCopy();
                if (this.previewIndex == returnChoice().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
                if (this.upgraded)
                    this.cardsToPreview.upgrade();
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void triggerWhenDrawn() {
        if (Shadowverse.Enhance(2)) {
            super.triggerWhenDrawn();
            setCostForTurn(2);
            applyPowers();
        }
    }
    @Override
    public void applyPowers(){

        if (Shadowverse.Enhance(2))
            setCostForTurn(2);
        else
        if (this.costForTurn>0){
            resetAttributes();
        }
        super.applyPowers();
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player.hand.group.contains(this)){
            if (Shadowverse.Enhance(2)) {
                super.triggerWhenDrawn();
                setCostForTurn(2);
                applyPowers();
            }
        }
    }

    public void triggerOnGlowCheck() {
        int count = 0;
        for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c instanceof LionCrystal || c instanceof LionCrystalCopy)
                count++;
        }
        if (count > 5) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }else if (count > 2){
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (AbstractDungeon.player.hasPower("Burst")||AbstractDungeon.player.hasPower("Double Tap")||AbstractDungeon.player.hasPower("Amplified")) {
            doubleCheck = true;
            if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                resetAttributes();
                applyPowers();
            }
        }else {
            if (doubleCheck) {
                doubleCheck = false;
            }else {
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < 2) {
                    resetAttributes();
                    applyPowers();
                }
            }
        }
    }

    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (EnergyPanel.getCurrentEnergy() >= 2) {
            setCostForTurn(2);
        }  else {
            resetAttributes();
        }
        applyPowers();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        int count = 0;
        for (AbstractCard c:AbstractDungeon.actionManager.cardsPlayedThisCombat){
            if (c instanceof LionCrystal || c instanceof LionCrystalCopy)
                count++;
        }
        if (count>5){
            AbstractCard k = new HolyKingLion();
            if (this.upgraded)
                k.upgrade();
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(k));
        }else if (count>2){
            AbstractCard pl = new HolyPlateLion();
            if (this.upgraded)
                pl.upgrade();
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(pl));
        }else {
            AbstractCard s = new HolyShieldLion();
            if (this.upgraded)
                s.upgrade();
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(s));
        }
        if (this.costForTurn == 2 && Shadowverse.Enhance(2)) {
            AbstractCard crs = new LionCrystalCopy();
            if (this.upgraded)
                crs.upgrade();
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(crs));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new LionCrystal();
    }
}
