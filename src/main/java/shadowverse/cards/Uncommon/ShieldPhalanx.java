package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import shadowverse.action.MinionSummonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.characters.Royal;
import shadowverse.orbs.FrontguardGeneral;
import shadowverse.orbs.Knight;
import shadowverse.orbs.Minion;
import shadowverse.orbs.ShieldGuardian;

public class ShieldPhalanx extends CustomCard {
    public static final String ID = "shadowverse:ShieldPhalanx";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ShieldPhalanx.png";
    public boolean triggered;


    public ShieldPhalanx() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        this.triggered = false;
    }

    @Override
    public void resetAttributes() {
        super.resetAttributes();
        this.triggered = false;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
            this.exhaust = false;
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (!this.triggered && rally() >= 15) {
            this.triggered = true;
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new FrontguardGeneral()));
        } else {
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new ShieldGuardian()));
        }
        AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new Knight()));
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
        if (this.upgraded) {
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + rally() + cardStrings.EXTENDED_DESCRIPTION[1];
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


    @Override
    public AbstractCard makeCopy() {
        return new ShieldPhalanx();
    }
}

