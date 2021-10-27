package shadowverse.cards.Status;

import basemod.abstracts.AbstractCardModifier;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.ArmamentsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cardmods.ImmoralDesireMod2;
import shadowverse.characters.AbstractShadowversePlayer;

public class EvolutionPoint extends CustomCard {
    public static final String ID = "shadowverse:EvolutionPoint";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EvolutionPoint");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EvolutionPoint.png";
    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString("ArmamentsAction").TEXT;

    public EvolutionPoint() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.STATUS, CardColor.COLORLESS, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SelectCardsInHandAction(1,TEXT[0],false,false,card -> {
            return card.type!=CardType.ATTACK;
        }, abstractCards ->{
            for (AbstractCard c:abstractCards){
                c.upgrade();
                c.superFlash();
                c.applyPowers();
            }
        } ));
    }

    @Override
    public AbstractCard makeCopy() {
        return new EvolutionPoint();
    }
}
