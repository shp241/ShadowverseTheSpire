package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;


public class SummonDivineTreasure
        extends CustomCard {
    public static final String ID = "shadowverse:SummonDivineTreasure";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:SummonDivineTreasure");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/SummonDivineTreasure.png";
    private float rotationTimer;
    private int previewIndex;

    public SummonDivineTreasure() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.SELF);
        ExhaustiveVariable.setBaseValue((AbstractCard)this, 3);
        this.tags.add(AbstractShadowversePlayer.Enums.LEGEND);
    }

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new JudgmentSpear());
        list.add(new GuiltyShield());
        list.add(new EternalDogma());
        list.add(new DivineTreasureHerald());
        list.add(new DivineTreasureGuardian());
        return list;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            ExhaustiveVariable.upgrade((AbstractCard)this, 2);
        }
    }

    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(3)) {
            setCostForTurn(3);
        }else if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(2)){
            setCostForTurn(2);
        }
        else {
            setCostForTurn(1);
        }
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
                    if (this.upgraded)
                        this.cardsToPreview.upgrade();
                } else {
                    this.rotationTimer -= Gdx.graphics.getDeltaTime();
                }
        }

        public void use (AbstractPlayer abstractPlayer, AbstractMonster abstractMonster){
            ArrayList<AbstractCard> stanceChoices = new ArrayList<>();
            stanceChoices.add(new JudgmentSpear());
            stanceChoices.add(new GuiltyShield());
            stanceChoices.add(new EternalDogma());
            addToBot((AbstractGameAction) new ChooseOneAction(stanceChoices));
            if (this.costForTurn == 3 && Shadowverse.Enhance(3)){
                addToBot(new MakeTempCardInHandAction(new DivineTreasureGuardian()));
                addToBot(new MakeTempCardInHandAction(new DivineTreasureHerald()));
            }
            if (this.costForTurn == 2 && Shadowverse.Enhance(2)){
                addToBot(new MakeTempCardInHandAction(new DivineTreasureHerald()));
            }
        }


        public AbstractCard makeCopy () {
            return (AbstractCard) new SummonDivineTreasure();
        }
    }

