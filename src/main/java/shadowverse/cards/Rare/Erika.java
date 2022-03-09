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
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import shadowverse.action.MinionSummonAction;
import shadowverse.cards.Temp.AnnesSorcery;
import shadowverse.cards.Temp.AnnesSummoning;
import shadowverse.cards.Temp.ErikasSleight;
import shadowverse.cards.Temp.SecretSkill;
import shadowverse.characters.Royal;
import shadowverse.orbs.AmbushMinion;
import shadowverse.orbs.ErikaOrb;
import shadowverse.orbs.Minion;

import java.util.ArrayList;

public class Erika
        extends CustomCard {
    public static final String ID = "shadowverse:Erika";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Erika");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Erika.png";
    private float rotationTimer;
    private int previewIndex;
    public boolean triggered;

    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new SecretSkill());
        list.add(new ErikasSleight());
        return list;
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

    public Erika() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded){
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (this.upgraded){
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        }else {
            this.rawDescription = cardStrings.DESCRIPTION;
        }
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + rally() + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    @Override
    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public int rally() {
        int rally = 0;

        for (AbstractOrb o : AbstractDungeon.actionManager.orbsChanneledThisCombat) {
            if (o instanceof Minion && !(o instanceof AmbushMinion) && !(o instanceof ErikaOrb)) {
                rally++;
            }
        }

        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisCombat) {
            if (c.type == CardType.ATTACK && !(c.hasTag(CardTags.STRIKE))) {
                rally++;
            }
        }
        return rally;
    }

    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("Erika"));
        addToBot(new MinionSummonAction(new ErikaOrb(this.magicNumber,1,this.makeStatEquivalentCopy())));
        if (rally()>=15 && this.upgraded){
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(new ErikasSleight()));
        }
        if (rally()>=7 && !triggered){
            triggered = true;
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(new SecretSkill()));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Erika();
    }

}


