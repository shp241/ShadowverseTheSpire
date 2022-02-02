package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.Shadowverse;
import shadowverse.characters.Elf;
import shadowverse.characters.Vampire;
import shadowverse.powers.ShootOfUnkillingPower;
import shadowverse.powers.WingsOfDesirePower;

public class WingsOfDesire extends CustomCard {
    public static final String ID = "shadowverse:WingsOfDesire";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WingsOfDesire");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WingsOfDesire.png";

    public WingsOfDesire() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.SELF);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.costForTurn == 1 && Shadowverse.Enhance(1)) {
            addToBot((AbstractGameAction)new SFXAction("WingsOfDesire_EH"));
            addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new WingsOfDesirePower(abstractPlayer,1,this.magicNumber),1));
        }else {
            addToBot((AbstractGameAction)new SFXAction("WingsOfDesire"));
        }
        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new WingsOfDesirePower(abstractPlayer,1,this.magicNumber),1));
    }

    @Override
    public void update() {
        if (Shadowverse.Enhance(1)){
            setCostForTurn(1);
        }else {
            setCostForTurn(0);
        }
        super.update();
    }
}
