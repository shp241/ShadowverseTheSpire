package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.EnergyDownPower;
import shadowverse.cards.Temp.GenerateNine;
import shadowverse.cards.Temp.Manmaru1;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;
import shadowverse.powers.AeneaPower;

import java.util.ArrayList;


public class Aenea
        extends CustomCard {
    public static final String ID = "shadowverse:Aenea";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Aenea");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Aenea.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Manmaru1());
        list.add(new GenerateNine());
        return list;
    }

    public Aenea() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.POWER, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(1);
        }
    }

    public void update() {
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = (AbstractCard) returnChoice().get(previewIndex).makeCopy();
                if (this.previewIndex == returnChoice().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractCard card = (AbstractCard) new Manmaru1().makeStatEquivalentCopy();
        card.setCostForTurn(0);
        addToBot((AbstractGameAction) new SFXAction("Aenea"));
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(card));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)abstractPlayer, (AbstractCreature)abstractPlayer, (AbstractPower)new EnergyDownPower((AbstractCreature)abstractPlayer, 1, true), 1));
        addToBot((AbstractGameAction) new ApplyPowerAction((AbstractCreature) abstractPlayer, (AbstractCreature) abstractPlayer, (AbstractPower) new AeneaPower((AbstractCreature) abstractPlayer, this.magicNumber), this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Aenea();
    }
}


