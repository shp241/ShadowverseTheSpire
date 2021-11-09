package shadowverse.cards.Status;

import basemod.abstracts.CustomCard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.action.AnimationAction;
import shadowverse.characters.*;

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
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.selfRetain = true;
            this.isEthereal = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SelectCardsInHandAction(1, TEXT[0], false, false, card -> card.type != CardType.SKILL && card.canUpgrade(), abstractCards -> {
            for (AbstractCard c : abstractCards) {
                int roll = AbstractDungeon.cardRandomRng.random(3);
                if (abstractPlayer.chosenClass == Witchcraft.Enums.WITCHCRAFT) {
                    addToBot(new SFXAction("Witchcraft_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Witchcraft.getBigAnimation(), "extra", 3.0F));
                } else if (abstractPlayer.chosenClass == Elf.Enums.Elf) {
                    addToBot(new SFXAction("Elf_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Elf.getBigAnimation(), "extra", 3.0F));
                } else if (abstractPlayer.chosenClass == Necromancer.Enums.Necromancer) {
                    addToBot(new SFXAction("Necromancer_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Necromancer.getBigAnimation(), "extra", 3.0F));
                } else if (abstractPlayer.chosenClass == Vampire.Enums.Vampire) {
                    addToBot(new SFXAction("Vampire_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Vampire.getBigAnimation(), "extra", 3.0F));
                } else if (abstractPlayer.chosenClass == Nemesis.Enums.Nemesis) {
                    addToBot(new SFXAction("Nemesis_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Nemesis.getBigAnimation(), "extra", 3.0F));
                } else if (abstractPlayer.chosenClass == Royal.Enums.Royal) {
                    addToBot(new SFXAction("Royal_Evolve" + roll % 3));
                    addToBot(new AnimationAction(Royal.getBigAnimation(), "extra", 3.0F));
                }

                addToBot(new UpgradeSpecificCardAction(c));
            }
        }));
    }

    @Override
    public AbstractCard makeCopy() {
        return new EvolutionPoint();
    }
}
