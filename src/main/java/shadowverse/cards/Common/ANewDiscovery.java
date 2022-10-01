package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.Shadowverse;
import shadowverse.action.ChoiceAction;
import shadowverse.action.MinionBuffAction;
import shadowverse.cards.Temp.BlitzArtifact;
import shadowverse.cards.Temp.EdgeArtifact;
import shadowverse.cards.Temp.ProtectArtifact;
import shadowverse.characters.Nemesis;
import shadowverse.characters.Royal;
import shadowverse.orbs.Minion;

import java.util.ArrayList;

public class ANewDiscovery
        extends CustomCard {
    public static final String ID = "shadowverse:ANewDiscovery";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ANewDiscovery");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/ANewDiscovery.png";
    private float rotationTimer;
    private int previewIndex;
    public static ArrayList<AbstractCard> returnChoice() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new ProtectArtifact());
        list.add(new BlitzArtifact());
        return list;
    }

    public ANewDiscovery() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.NONE);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(3)) {
            setCostForTurn(3);
        } else {
            setCostForTurn(2);
        }
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
                if (this.upgraded)
                    this.cardsToPreview.upgrade();
            } else {
                this.rotationTimer -= Gdx.graphics.getDeltaTime();
            }
    }

    public void use(AbstractPlayer player, AbstractMonster abstractMonster) {
        AbstractCard p = (AbstractCard)new ProtectArtifact();
        AbstractCard e = (AbstractCard)new BlitzArtifact();
        if (this.upgraded){
            p.upgrade();
            e.upgrade();
        }
        if (this.costForTurn == 3 && Shadowverse.Enhance(3)) {
            p.setCostForTurn(0);
            e.setCostForTurn(0);
            addToBot(new MakeTempCardInHandAction(p));
            addToBot(new MakeTempCardInHandAction(e));
        }else {
            addToBot((AbstractGameAction)new ChoiceAction(new AbstractCard[] { p,e,}));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new ANewDiscovery();
    }
}


