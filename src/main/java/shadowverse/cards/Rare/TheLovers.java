package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.BurialAction;
import shadowverse.action.ReanimateAction;
import shadowverse.characters.Necromancer;
import shadowverse.powers.TheLoversPower;


public class TheLovers extends CustomCard {
    public static final String ID = "shadowverse:TheLovers";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheLovers");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TheLovers.png";

    public TheLovers() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 6;
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(6);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(6)) {
            setCostForTurn(6);
        } else {
            if (this.costForTurn > 1) {
                setCostForTurn(2);
            }
        }
        super.update();
    }

    @Override
    public void triggerOnExhaust() {
        addToBot((AbstractGameAction) new DrawCardAction(2));
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
        if (this.costForTurn == 6 && Shadowverse.Enhance(6)) {
            addToBot((AbstractGameAction) new SFXAction("TheLoversEH"));
            addToBot((AbstractGameAction) new ApplyPowerAction(abstractPlayer, abstractPlayer, (AbstractPower) new TheLoversPower(abstractPlayer)));
        } else {
            addToBot((AbstractGameAction) new SFXAction("TheLovers"));
            int attackAmt = 0;
            for (AbstractCard c : abstractPlayer.hand.group) {
                if (c != this && c.type == CardType.ATTACK)
                    attackAmt++;
            }
            if (attackAmt >= 2 && this.costForTurn > 0) {
                addToBot((AbstractGameAction) new BurialAction(2, null));
                int rand = AbstractDungeon.cardRandomRng.random(this.magicNumber);
                int rand2 = this.magicNumber - rand;
                addToBot((AbstractGameAction) new ReanimateAction(rand));
                addToBot((AbstractGameAction) new ReanimateAction(rand2));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new TheLovers();
    }
}

