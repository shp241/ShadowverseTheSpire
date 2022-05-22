package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.characters.Elf;

public class WrathOfNature extends CustomCard {
    public static final String ID = "shadowverse:WrathOfNature";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:WrathOfNature");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/WrathOfNature.png";

    public WrathOfNature() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Elf.Enums.COLOR_GREEN, CardRarity.SPECIAL, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT&&
                Shadowverse.Enhance(2)){
            setCostForTurn(2);
        }else {
            setCostForTurn(0);
        }
        super.update();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new SFXAction("WrathOfNature"));
        addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,this.magicNumber),this.magicNumber));
        if (this.costForTurn==2){
            addToBot((AbstractGameAction)new ApplyPowerAction(p, p, new DoubleDamagePower(p, 1, false), 1));
        }
    }


    public AbstractCard makeCopy() {
        return new WrathOfNature();
    }
}
