package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.BurialAction;
import shadowverse.action.ReanimateAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


public class Cernunnos extends CustomCard {
    public static final String ID = "shadowverse:Cernunnos";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cernunnos");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Cernunnos.png";

    public Cernunnos() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new BurialAction(1, (AbstractGameAction) new DrawCardAction(this.magicNumber)));
        addToBot((AbstractGameAction) new SFXAction("Cernunnos"));
        addToBot((AbstractGameAction) new GainEnergyAction(2));
        addToBot((AbstractGameAction) new ReanimateAction(this.magicNumber));
        if (abstractPlayer instanceof AbstractShadowversePlayer){
           if (((AbstractShadowversePlayer)abstractPlayer).necromanceCount>=20){
               addToBot((AbstractGameAction) new ReanimateAction(5));
           }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Cernunnos();
    }
}
