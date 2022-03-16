package shadowverse.cards.Common;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.cards.Temp.ProductMachine;
import shadowverse.cards.Temp.WhiteTiger;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;

public class Cybercannoneer extends CustomCard {
    public static final String ID = "shadowverse:Cybercannoneer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Cybercannoneer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Cybercannoneer.png";


    public Cybercannoneer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.COMMON, CardTarget.ENEMY);
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
        this.baseDamage = 9;
        this.baseMagicNumber = this.magicNumber = 2;
        this.cardsToPreview = new ProductMachine();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(3);
        }
    }

    @Override
    public void update() {
        if (AbstractDungeon.currMapNode != null && (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                Shadowverse.Enhance(2)) {
            setCostForTurn(2);
        } else {
            setCostForTurn(1);
        }
        super.update();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (Shadowverse.Enhance(2) && this.costForTurn == 2) {
            this.addToTop(new MakeTempCardInHandAction(new ProductMachine(), this.magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Cybercannoneer();
    }
}

