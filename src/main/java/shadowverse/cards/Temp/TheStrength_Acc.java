package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import shadowverse.Shadowverse;
import shadowverse.action.PlaceAmulet;
import shadowverse.characters.Bishop;
import shadowverse.powers.TheStrengthPower;

public class TheStrength_Acc
        extends CustomCard {
    public static final String ID = "shadowverse:TheStrength_Acc";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:TheStrength");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/TheStrength.png";

    public TheStrength_Acc() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.SELF);
        this.cardsToPreview = new SomnolentStrength();
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }




    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("TheStrength_Acc"));
        addToBot((AbstractGameAction)new PlaceAmulet(this.cardsToPreview.makeStatEquivalentCopy(),null));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new TheStrength_Acc();
    }
}


