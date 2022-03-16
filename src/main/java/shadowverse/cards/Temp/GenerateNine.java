package shadowverse.cards.Temp;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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
import shadowverse.action.GenerateNineAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.IzudiaPower;


public class GenerateNine extends CustomCard {
    public static final String ID = "shadowverse:GenerateNine";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GenerateNine");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GenerateNine.png";

    public GenerateNine() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Necromancer.Enums.COLOR_PURPLE, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
        this.isEthereal = true;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(4)) {
            setCostForTurn(4);
        } else {
            setCostForTurn(0);
        }
        super.update();
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new IzudiaPower((AbstractCreature) abstractPlayer, 1), 1));
        if (this.costForTurn == 4 && Shadowverse.Enhance(4)) {
            addToBot((AbstractGameAction)new GenerateNineAction());
            addToBot((AbstractGameAction) new SFXAction("GenerateNine_EH"));
        }else {
            addToBot((AbstractGameAction) new SFXAction("GenerateNine"));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new GenerateNine();
    }
}

