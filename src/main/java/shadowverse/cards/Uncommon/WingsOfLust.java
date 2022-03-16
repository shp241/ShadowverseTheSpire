package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;
import shadowverse.powers.DrainPower;
import shadowverse.powers.WingsOfLustPower;

public class WingsOfLust
        extends CustomCard {
    public static final String ID = "shadowverse:WingsOfLust";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WingsOfLust");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WingsOfLust.png";

    public WingsOfLust() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.UNCOMMON, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
        this.isEthereal = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
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
        addToBot((AbstractGameAction) new SFXAction("WingsOfLust"));
        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new WingsOfLustPower(abstractPlayer,1),1));
        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new StrengthPower(abstractPlayer,this.magicNumber),this.magicNumber));
        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new DexterityPower(abstractPlayer,this.magicNumber),this.magicNumber));
        if (this.costForTurn == 2 && Shadowverse.Enhance(2)) {
            addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new DrainPower(abstractPlayer)));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new WingsOfLust();
    }
}


