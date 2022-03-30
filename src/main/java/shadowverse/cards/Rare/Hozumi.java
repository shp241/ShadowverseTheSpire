package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Elf;

public class Hozumi extends CustomCard {
    public static final String ID = "shadowverse:Hozumi";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Hozumi");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Hozumi.png";

    public Hozumi() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Elf.Enums.COLOR_GREEN, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 9;
        this.tags.add(AbstractShadowversePlayer.Enums.FES);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
        }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Hozumi"));
        addToBot(new GainBlockAction(p,this.block));
        int count = 0;
        for (AbstractCard c : AbstractDungeon.actionManager.cardsPlayedThisTurn){
            count++;
        }
        if (count > 4){
            for (AbstractCard card : p.hand.group){
                if (card!=this){
                    addToBot(new ExhaustSpecificCardAction(card,p.hand));
                    if (card.type==CardType.ATTACK){
                        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(AbstractCard.CardType.ATTACK).makeCopy();
                        c.setCostForTurn(0);
                        addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, true));
                    }
                }
            }
        }
    }


    public AbstractCard makeCopy() {
        return new Hozumi();
    }
}
