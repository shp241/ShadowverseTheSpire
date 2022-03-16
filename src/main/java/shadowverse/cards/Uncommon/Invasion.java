package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.action.DrawPileToHandAction_Tag;
import shadowverse.cards.Temp.ArmoredTentacle;
import shadowverse.cards.Temp.AssaultTentacle;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

import java.util.ArrayList;

public class Invasion extends CustomCard {
    public static final String ID = "shadowverse:Invasion";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Invasion");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Invasion.png";

    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnTentacle(){
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ArmoredTentacle());
        list.add(new AssaultTentacle());
        return list;
    }

    public Invasion() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 9;
        this.exhaust = true;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
    }

    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(3)) {
            setCostForTurn(3);
        } else {
            setCostForTurn(1);
        }
        super.update();
        if (this.hb.hovered)
            if (this.rotationTimer <= 0.0F) {
                this.rotationTimer = 2.0F;
                this.cardsToPreview = (AbstractCard)returnTentacle().get(previewIndex).makeCopy();
                if (this.previewIndex == returnTentacle().size() - 1) {
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
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster monster) {
        addToBot((AbstractGameAction)new SFXAction("Belphomet2"));
        addToBot((AbstractGameAction)new DamageAllEnemiesAction((AbstractCreature)p, DamageInfo.createDamageMatrix(this.damage, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE, true));
        if (this.costForTurn == 3 && Shadowverse.Enhance(3)) {
            AbstractCard assault = (AbstractCard)new AssaultTentacle();
            AbstractCard armored = (AbstractCard)new ArmoredTentacle();
            if (this.upgraded){
                armored.upgrade();
                assault.upgrade();
            }
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(assault.makeStatEquivalentCopy()));
            addToBot((AbstractGameAction)new MakeTempCardInHandAction(armored.makeStatEquivalentCopy()));
            addToBot((AbstractGameAction)new DrawPileToHandAction_Tag(1,AbstractShadowversePlayer.Enums.MACHINE,null));
        }
    }

    public AbstractCard makeCopy() {
        return new Invasion();
    }
}
