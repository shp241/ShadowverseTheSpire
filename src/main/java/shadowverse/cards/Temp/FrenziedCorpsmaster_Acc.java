package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.orbs.HeavyKnight;
import shadowverse.orbs.Minion;
import shadowverse.orbs.SteelcladKnight;

public class FrenziedCorpsmaster_Acc  extends CustomCard {
    public static final String ID = "shadowverse:FrenziedCorpsmaster_Acc";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:FrenziedCorpsmaster");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/FrenziedCorpsmaster.png";

    public FrenziedCorpsmaster_Acc() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseBlock = 15;
        this.baseDamage = 0;
        this.isMultiDamage = true;
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(10);
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
        this.baseDamage = rally() * 2;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + this.baseDamage + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new SteelcladKnight()));
        AbstractDungeon.actionManager.addToBottom(new ChannelAction(new HeavyKnight()));
    }


    @Override
    public AbstractCard makeCopy() {
        return new FrenziedCorpsmaster_Acc();
    }
}
