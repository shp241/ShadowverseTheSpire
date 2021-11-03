package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import shadowverse.cards.Temp.GildedBlade;
import shadowverse.cards.Temp.GildedBoots;
import shadowverse.cards.Temp.GildedGoblet;
import shadowverse.cards.Temp.GildedNecklace;
import shadowverse.characters.Royal;

import java.util.ArrayList;

public class Octrice extends CustomCard {
    public static final String ID = "shadowverse:Octrice";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Octrice");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Octrice.png";
    public static final String IMG_PATH_EV = "img/cards/Octrice_Ev.png";
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

    public Octrice() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.ENEMY);
        this.baseMagicNumber = 10;
        this.magicNumber = this.baseMagicNumber;
        this.exhaust = true;
    }

    @Override
    public void update() {
        super.update();
        if (this.hb.hovered && this.upgraded) {
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
            upgradeMagicNumber(5);
            this.textureImg = IMG_PATH_EV;
            this.loadCardImage(IMG_PATH_EV);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Ev"));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
        addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -2), -2));
        AbstractDungeon.effectList.add(new RainingGoldEffect(this.magicNumber));
        AbstractDungeon.player.gainGold(this.magicNumber);
        if (this.upgraded) {
            int r1 = AbstractDungeon.cardRandomRng.random(3);
            int r2 = AbstractDungeon.cardRandomRng.random(2);
            AbstractCard c1 = returnProphecy().get(r1);
            AbstractCard c2 = returnProphecy().get((r1 + r2 + 1) % 4);
            addToBot(new MakeTempCardInHandAction(c1));
            addToBot(new MakeTempCardInHandAction(c2));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new Octrice();
    }
}
