package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.ChoiceAction2;
import shadowverse.cards.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

import java.util.ArrayList;

public class GrandAcquisition extends CustomCard {
    public static final String ID = "shadowverse:GrandAcquisition";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:GrandAcquisition");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/GrandAcquisition.png";

    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnProphecy() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new GildedBlade());
        list.add(new GildedNecklace());
        list.add(new GildedGoblet());
        list.add(new GildedBoots());
        return list;
    }

    public GrandAcquisition() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust = true;
        this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(3)) {
            setCostForTurn(3);
        } else {
            setCostForTurn(0);
        }
        super.update();
        if (this.hb.hovered) {
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = returnProphecy().get(previewIndex).makeCopy();
                if (this.previewIndex == returnProphecy().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Enhance(3) && this.costForTurn == 3) {
            addToBot(new MakeTempCardInHandAction(new GildedBlade()));
            addToBot(new MakeTempCardInHandAction(new GildedGoblet()));
            addToBot(new MakeTempCardInHandAction(new GildedBoots()));
            addToBot(new MakeTempCardInHandAction(new GildedNecklace()));
            addToBot(new GainEnergyAction(2));
        } else {
            addToBot(new ChoiceAction2(new GildedBlade(), new GildedGoblet(), new GildedBoots(), new GildedNecklace()));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new GrandAcquisition();
    }
}
