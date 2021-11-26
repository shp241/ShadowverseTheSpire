package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import shadowverse.Shadowverse;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.cards.Temp.DreadHound;
import shadowverse.characters.Royal;
import shadowverse.orbs.Minion;
import shadowverse.powers.DisableEffectDamagePower;
import shadowverse.relics.KagemitsuSword;

public class Ilmisuna extends CustomCard {
    public static final String ID = "shadowverse:Ilmisuna";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Ilmisuna.png";
    public static final String IMG_PATH_EV = "img/cards/Ilmisuna_Ev.png";

    public Ilmisuna() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 8;
        this.cardsToPreview = new EvolutionPoint();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            this.textureImg = IMG_PATH_EV;
            this.loadCardImage(IMG_PATH_EV);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public int rally() {
        int rally = 0;

        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Minion) {
                rally++;
            }
        }

        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.type == CardType.ATTACK && !(c.hasTag(CardTags.STRIKE))) {
                rally++;
            }
        }
        return rally;
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (!this.upgraded) {
            this.rawDescription = cardStrings.DESCRIPTION;
            this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + rally() + cardStrings.EXTENDED_DESCRIPTION[1];
            this.initializeDescription();
        }
    }

    @Override
    public void onMoveToDiscard() {
        if (!this.upgraded) {
            this.rawDescription = cardStrings.DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void triggerWhenDrawn() {
        if (rally() >= 11) {
            addToBot(new MakeTempCardInHandAction(new EvolutionPoint()));
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
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Ev"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        if (this.upgraded) {
            addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> true, abstractCards -> {
                for (AbstractCard c : abstractCards) {
                    addToBot(new ExhaustSpecificCardAction(c, p.hand));
                    addToBot(new DrawCardAction(1));
                }
            }));
        }
        addToBot(new GainBlockAction(p, p, this.block));
        if (this.upgraded) {
            this.degrade();
            if (p.hasRelic(KagemitsuSword.ID)) {
                this.upgrade();
            }
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Ilmisuna();
    }
}

