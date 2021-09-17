package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.InflameEffect;
import shadowverse.cards.Temp.Koko;
import shadowverse.cards.Temp.Mimi;
import shadowverse.characters.Necromancer;

import java.util.ArrayList;


public class Cerberus
        extends CustomCard {
    public static final String ID = "shadowverse:Cerberus";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cerberus");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Cerberus.png";
    private float rotationTimer;
    private int previewIndex;

    public static ArrayList<AbstractCard> returnShikigami() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new Mimi());
        list.add(new Koko());
        return list;
    }

    public Cerberus() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 9;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void update() {
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

    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction) new SFXAction("Cerberus"));
        addToBot((AbstractGameAction) new GainBlockAction(abstractPlayer, this.block));
        addToBot((AbstractGameAction) new VFXAction((AbstractCreature) abstractPlayer, (AbstractGameEffect) new InflameEffect((AbstractCreature) abstractPlayer), 1.0F));
        ArrayList<AbstractCard> shikigamiHand = returnShikigami();
        for (AbstractCard c : shikigamiHand) {
            if (this.upgraded) {
                c.upgrade();
            }
            addToBot((AbstractGameAction) new MakeTempCardInHandAction(c, 1));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Cerberus();
    }
}

