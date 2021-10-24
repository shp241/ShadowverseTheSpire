package shadowverse.cards.Temp;


import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import shadowverse.Shadowverse;
import shadowverse.action.ExhaustSpecificGroupAndDrawAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;


public class OmniscientKaiser_Acc extends CustomCard {
    public static final String ID = "shadowverse:OmniscientKaiser_Acc";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmniscientKaiser");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmniscientKaiser.png";
    public static final String[] TEXT = (CardCrawlGame.languagePack.getUIString("shadowverse:applyEffect")).TEXT;

    public OmniscientKaiser_Acc() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ALL);
        this.baseBlock = 21;
        this.baseDamage = 49;
        this.isMultiDamage = true;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(7);
            upgradeDamage(7);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> cardsNotToExhaust = new ArrayList<>();
        addToBot((AbstractGameAction) new SelectCardsInHandAction(1, TEXT[0], false, false, card -> {
            return true;
        }, abstractCards -> {
            for (AbstractCard c:abstractCards){
                cardsNotToExhaust.add(c);
            }
        }));
        addToBot((AbstractGameAction) new SFXAction("OmniscientKaiser_Acc"));
        addToBot((AbstractGameAction)new ExhaustSpecificGroupAndDrawAction(cardsNotToExhaust,p.hand,this.upgraded));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new OmniscientKaiser_Acc();
    }
}
