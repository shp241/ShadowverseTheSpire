package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Royal;
import shadowverse.orbs.Knight;
import shadowverse.orbs.ShieldGuardian;

public class ShieldPhalanx extends CustomCard {
    public static final String ID = "shadowverse:ShieldPhalanx";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ShieldPhalanx.png";

    public ShieldPhalanx() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ENEMY);
        this.exhaust = true;
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
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new ShieldGuardian()));
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new Knight()));
    }


    @Override
    public AbstractCard makeCopy() {
        return new ShieldPhalanx();
    }
}

