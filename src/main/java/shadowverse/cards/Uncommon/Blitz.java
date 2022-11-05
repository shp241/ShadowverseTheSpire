package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.cards.Common.MagicalKnight;
import shadowverse.cards.Common.MagicalRook;
import shadowverse.cards.Temp.MagicalPawn;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.BlitzPower;
import shadowverse.powers.MysticQueenPower;

import java.util.ArrayList;

public class Blitz
        extends CustomCard {
    public static final String ID = "shadowverse:Blitz";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Blitz");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Blitz.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new MagicalPawn());
        list.add(new MagicalRook());
        list.add(new MagicalKnight());
        return list;
    }

    public Blitz() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Witchcraft.Enums.COLOR_BLUE, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
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
                this.cardsToPreview = (AbstractCard)returnChoice().get(previewIndex).makeCopy();
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
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new BlitzPower(abstractPlayer)));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Blitz();
    }
}

