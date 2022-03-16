package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.cards.Temp.MadnessRevealed;
import shadowverse.cards.Temp.NightFall;
import shadowverse.characters.Vampire;

import java.util.ArrayList;

public class RestlessParish
        extends CustomCard {
    public static final String ID = "shadowverse:RestlessParish";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:RestlessParish");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/RestlessParish.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnShikigami() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new NightFall());
        list.add(new MadnessRevealed());
        return list;
    }

    public RestlessParish() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.POWER, Vampire.Enums.COLOR_SCARLET, CardRarity.COMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(1)) {
            setCostForTurn(1);
        } else {
            setCostForTurn(0);
        }
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = (AbstractCard) returnShikigami().get(previewIndex).makeCopy();
                if (this.previewIndex == returnShikigami().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
                if (this.upgraded)
                    this.cardsToPreview.upgrade();
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        if (this.costForTurn == 1 && Shadowverse.Enhance(1)) {
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            AbstractCard n = (AbstractCard) new NightFall();
            AbstractCard m = (AbstractCard) new MadnessRevealed();
            if (this.upgraded) {
                n.upgrade();
                m.upgrade();
            }
            stanceChoices.add(n);
            stanceChoices.add(m);
            addToBot((AbstractGameAction) new ChooseOneAction(stanceChoices));
        } else {
            addToBot((AbstractGameAction) new LoseHPAction(p, p, 1));
            addToBot((AbstractGameAction) new ApplyPowerAction(p, p, (AbstractPower) new DrawCardNextTurnPower(p, magicNumber), magicNumber));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new RestlessParish();
    }
}


