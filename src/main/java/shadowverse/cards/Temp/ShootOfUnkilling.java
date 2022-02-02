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
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.ShootOfUnkillingPower;

public class ShootOfUnkilling extends CustomCard {
    public static final String ID = "shadowverse:ShootOfUnkilling";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ShootOfUnkilling");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ShootOfUnkilling.png";

    public ShootOfUnkilling () {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new ApplyPowerAction(abstractMonster,abstractPlayer,(AbstractPower)new StrengthPower(abstractMonster,-this.magicNumber),-this.magicNumber));
        if (this.costForTurn == 3 && Shadowverse.Enhance(3)) {
            addToBot((AbstractGameAction)new SFXAction("ShootOfUnkilling_EH"));
            addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,new ShootOfUnkillingPower(abstractPlayer,1),1));
        }else {
            addToBot((AbstractGameAction)new SFXAction("ShootOfUnkilling"));
        }
    }

    @Override
    public void update() {
        if (Shadowverse.Enhance(3)){
            setCostForTurn(3);
        }else {
            setCostForTurn(0);
        }
        super.update();
    }
}
