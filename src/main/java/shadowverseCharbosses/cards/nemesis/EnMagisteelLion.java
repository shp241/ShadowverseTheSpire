package shadowverseCharbosses.cards.nemesis;

import shadowverseCharbosses.actions.common.EnemyMakeTempCardInDrawPileAction;
import shadowverseCharbosses.cards.AbstractBossCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.Nemesis;

public class EnMagisteelLion extends AbstractBossCard {
    public static final String ID = "shadowverse:EnMagisteelLion";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EnMagisteelLion");

    public static final String IMG_PATH = "img/cards/MagisteelLion.png";

    public EnMagisteelLion() {
        super(ID, cardStrings.NAME, IMG_PATH, 1, cardStrings.DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.COMMON, CardTarget.SELF, AbstractMonster.Intent.DEFEND_BUFF);
        this.baseBlock = 8;
        this.cardsToPreview = new EnAnalyzeArtifact();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction((AbstractCreature)m, (AbstractCreature)m, this.block));
        addToBot((AbstractGameAction)new EnemyMakeTempCardInDrawPileAction(this.cardsToPreview.makeStatEquivalentCopy(),2,true,true,false));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new EnMagisteelLion();
    }
}
