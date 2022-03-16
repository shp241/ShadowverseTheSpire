package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import shadowverse.action.MinionSummonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import shadowverse.Shadowverse;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Royal;
import shadowverse.orbs.Knight;
import shadowverse.orbs.ShieldGuardian;

public class EmpressOfSerenity extends CustomCard {
    public static final String ID = "shadowverse:EmpressOfSerenity";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EmpressOfSerenity.png";

    public EmpressOfSerenity() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.ENHANCE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
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
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(abstractPlayer, abstractPlayer, this.block));
        AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new ShieldGuardian()));
        if (Shadowverse.Enhance(2) && this.costForTurn == 2) {
            addToBot(new SFXAction(ID.replace("shadowverse:", "") + "_Eh"));
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new ShieldGuardian()));
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new ShieldGuardian()));
        } else {
            addToBot(new SFXAction(ID.replace("shadowverse:", "")));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new EmpressOfSerenity();
    }
}
