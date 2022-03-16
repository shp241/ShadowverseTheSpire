package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
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
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;

public class Aerin
        extends CustomCard {
    public static final String ID = "shadowverse:Aerin";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Aerin");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Aerin.png";

    public Aerin() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.UNCOMMON, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
        this.baseBlock = 12;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(4);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(3)) {
            setCostForTurn(3);
        } else {
            setCostForTurn(2);
        }
        super.update();
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Aerin"));
        addToBot((AbstractGameAction) new HealAction(abstractPlayer, abstractPlayer, 3));
        addToBot((AbstractGameAction) new GainBlockAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, this.block));
        if (this.costForTurn == 3 && Shadowverse.Enhance(3)) {
            addToBot((AbstractGameAction) new MakeTempCardInHandAction((AbstractCard) new EvolutionPoint()));
            addToBot((AbstractGameAction) new MakeTempCardInHandAction((AbstractCard) new Miracle()));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Aerin();
    }
}


