package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
import shadowverse.Shadowverse;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.cards.Temp.ArsMagna;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.EarthEssence;

public class Cagliostro
        extends CustomCard {
    public static final String ID = "shadowverse:Cagliostro";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cagliostro");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Cagliostro.png";

    public Cagliostro() {
        super("shadowverse:Cagliostro", NAME, "img/cards/Cagliostro.png", 1, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.EARTH_RITE);
        this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
        this.cardsToPreview = (AbstractCard) new ArsMagna();
        this.baseBlock = 5;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(2)) {
            setCostForTurn(2);
        } else {
            setCostForTurn(1);
        }
        super.update();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractCard c = this.cardsToPreview.makeStatEquivalentCopy();
        boolean powerExists = false;
        for (AbstractPower pow : abstractPlayer.powers) {
            if (pow.ID.equals("shadowverse:EarthEssence")) {
                powerExists = true;
                break;
            }
        }
        if (powerExists) {
            addToBot((AbstractGameAction) new SFXAction("Cagliostro2"));
            ((AbstractShadowversePlayer) abstractPlayer).earthCount++;
            if (EnergyPanel.getCurrentEnergy() - this.costForTurn >= 2)
                c.setCostForTurn(2);
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(c, 1));
            addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new EarthEssence((AbstractCreature) abstractPlayer, -1), -1));
        } else {
            addToBot((AbstractGameAction) new SFXAction("Cagliostro1"));
        }
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new EarthEssence((AbstractCreature) abstractPlayer, 1), 1));
        if (this.costForTurn == 2 && Shadowverse.Enhance(2)) {
            addToBot((AbstractGameAction) new MakeTempCardInHandAction((AbstractCard) new EvolutionPoint()));
            addToBot((AbstractGameAction) new MakeTempCardInHandAction((AbstractCard) new Miracle()));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Cagliostro();
    }
}
