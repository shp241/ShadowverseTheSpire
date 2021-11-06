package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.cards.Temp.WhiteTiger;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class LevinBeastmaster extends CustomCard {
    public static final String ID = "shadowverse:LevinBeastmaster";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LevinBeastmaster");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LevinBeastmaster.png";
    public static final String IMG_PATH_EV = "img/cards/LevinBeastmaster_Ev.png";

    public LevinBeastmaster() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.LEVIN);
        this.cardsToPreview = new WhiteTiger();
        this.baseBlock = 3;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.textureImg = IMG_PATH_EV;
            this.loadCardImage(IMG_PATH_EV);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void degrade() {
        if (this.upgraded) {
            degradeName();
            this.textureImg = IMG_PATH;
            this.loadCardImage(IMG_PATH);
            this.rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
            this.superFlash();
            this.applyPowers();
        }
    }

    public void degradeName() {
        --this.timesUpgraded;
        this.upgraded = false;
        this.name = NAME;
        this.initializeTitle();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.upgraded) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Ev"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        this.addToTop(new MakeTempCardInHandAction(new WhiteTiger(), 1));
        if (this.upgraded) {
            this.addToTop(new MakeTempCardInHandAction(new WhiteTiger(), 2));
            this.degrade();
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new LevinBeastmaster();
    }
}

