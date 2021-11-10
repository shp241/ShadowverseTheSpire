package shadowverse.cards.Curse;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.defect.ShuffleAllAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import shadowverse.powers.WingsOfLustPower;

import java.util.ArrayList;

public class OmenOfTen extends CustomCard {
    public static final String ID = "shadowverse:OmenOfTen";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:OmenOfTen");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/OmenOfTen.png";

    public OmenOfTen() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (!c.exhaust){
            addToBot((AbstractGameAction)new ShuffleAllAction());
            addToBot((AbstractGameAction)new ShuffleAction(AbstractDungeon.player.drawPile, false));
        }
        addToBot((AbstractGameAction)new SFXAction("VO_CULTIST_1A"));
        addToBot((AbstractGameAction)new TalkAction(true, "咔咔！", 1.0F, 2.0F));
    }

    @Override
    public void triggerWhenDrawn() {
        AbstractDungeon.player.decreaseMaxHealth(6);
        addToBot((AbstractGameAction)new LoseHPAction(AbstractDungeon.player,AbstractDungeon.player,1));
        addToBot((AbstractGameAction)new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        addToBot((AbstractGameAction)new GainGoldAction(8));
    }

    @Override
    public boolean canPlay(AbstractCard card) {
        boolean deckCheck = true;
        ArrayList<String> tmp = new ArrayList<>();
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (tmp.contains(c.cardID)) {
                deckCheck = false;
                break;
            }
            tmp.add(c.cardID);
        }
        if (!deckCheck)
            return false;
        return true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        int theSize = AbstractDungeon.player.hand.size();
        addToBot((AbstractGameAction)new LoseHPAction(abstractPlayer,abstractPlayer,4));
        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new WingsOfLustPower(abstractPlayer,1),1));
        addToBot((AbstractGameAction)new ApplyPowerAction(abstractPlayer,abstractPlayer,(AbstractPower)new StrengthPower(abstractPlayer,4),4));
        addToBot((AbstractGameAction)new DiscardAction(abstractPlayer, abstractPlayer, theSize, false));
        AbstractCard s = (new Shiv()).makeCopy();
        addToBot((AbstractGameAction)new MakeTempCardInHandAction(s, 7));
        addToBot((AbstractGameAction)new SkipEnemiesTurnAction());
        addToBot((AbstractGameAction)new PressEndTurnButtonAction());
    }

    @Override
    public AbstractCard makeCopy(){
        return new OmenOfTen();
    }
}
