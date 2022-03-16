package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.MinionSummonAction;
import shadowverse.cards.Rare.Albert;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.orbs.ShieldGuardian;

public class Lounes extends CustomCard {
    public static final String ID = "shadowverse:Lounes";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Lounes.png";

    public Lounes() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 3;
        this.baseBlock = 2;
        this.tags.add(AbstractShadowversePlayer.Enums.LEVIN);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(1);
            upgradeDamage(2);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(1)) {
            setCostForTurn(1);
        } else {
            setCostForTurn(0);
        }
        super.update();
    }

    public int levins() {
        AbstractPlayer abstractPlayer = AbstractDungeon.player;
        int l = 0;
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.LEVIN)) {
                l++;
            }
        }
        return l;
    }

    @Override
    public void applyPowers() {
        if (Shadowverse.Enhance(1)) {
            setCostForTurn(1);
        } else {
            resetAttributes();
        }
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + levins() + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + levins() + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        boolean co = false;
        for (AbstractCard c : abstractPlayer.hand.group) {
            if (c instanceof Albert) {
                co = true;
                break;
            }
        }
        if (co) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Co"));
        } else if (Shadowverse.Enhance(1) && this.costForTurn == 1) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Eh"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        if (Shadowverse.Enhance(1) && this.costForTurn == 1) {
            addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
        addToBot(new DamageAction(abstractMonster, new DamageInfo(abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        for (int i = 0; i < levins(); i++) {
            addToBot(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Lounes();
    }
}




