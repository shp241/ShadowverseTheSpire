package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.action.MusketeersAction;
import shadowverse.cards.Temp.Aramis;
import shadowverse.cards.Temp.Athos;
import shadowverse.cards.Temp.DArtagnan;
import shadowverse.cards.Temp.Porthos;
import shadowverse.characters.Royal;

import java.util.ArrayList;


public class MusketeersVow extends CustomCard {
    public static final String ID = "shadowverse:MusketeersVow";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MusketeersVow.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoose() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Aramis());
        list.add(new Porthos());
        list.add(new Athos());
        list.add(new DArtagnan());
        return list;
    }

    public MusketeersVow() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.NONE);
        ExhaustiveVariable.setBaseValue((AbstractCard)this, 1);
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            ExhaustiveVariable.upgrade((AbstractCard)this, 1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(4)) {
            setCostForTurn(4);
        } else {
            setCostForTurn(2);
        }
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = (AbstractCard) returnChoose().get(previewIndex).makeCopy();
                if (this.previewIndex == returnChoose().size() - 1) {
                    this.previewIndex = 0;
                } else {
                    this.previewIndex++;
                }
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (Shadowverse.Enhance(4) && this.costForTurn == 4) {
            addToBot((AbstractGameAction)new SFXAction("MusketeersVow_EH"));
            addToBot((AbstractGameAction)new MusketeersAction(true,true));
        }else {
            addToBot((AbstractGameAction)new SFXAction("MusketeersVow"));
            addToBot((AbstractGameAction)new MusketeersAction(true,false));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new MusketeersVow();
    }
}

