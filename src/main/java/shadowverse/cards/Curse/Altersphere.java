package shadowverse.cards.Curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Altersphere extends CustomCard {
    public static final String ID = "shadowverse:Altersphere";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Altersphere");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Altersphere.png";

    public static CardGroup group = null;

    public Altersphere() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        this.isEthereal = true;
        if (group == null) {
            group = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
            for (AbstractCard q : CardLibrary.getAllCards()) {
                if (q.cardID.contains("shadowverse") && q.rarity != CardRarity.SPECIAL) {
                    group.addToBottom(q.makeCopy());
                }
            }
        }
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        int rnd = AbstractDungeon.cardRandomRng.random(AbstractDungeon.player.hand.size() - 1);
        AbstractCard toExhaust = null;
        toExhaust = AbstractDungeon.player.hand.group.get(rnd);
        addToBot((AbstractGameAction) new ExhaustSpecificCardAction(toExhaust, AbstractDungeon.player.hand));
        addToBot((AbstractGameAction) new MakeTempCardInHandAction(group.getRandomCard(true)));
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }

    @Override
    public AbstractCard makeCopy() {
        return new Altersphere();
    }
}
