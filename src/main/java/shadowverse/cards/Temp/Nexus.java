package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import shadowverse.characters.Nemesis;
import shadowverse.powers.NexusPower;
import shadowverse.relics.Offensive5;

public class Nexus extends CustomCard {
    public static final String ID = "shadowverse:Nexus";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Nexus");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Nerva.png";

    public Nexus() {
        super(ID, NAME, IMG_PATH, -2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
    }


    public void upgrade() {
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        onChoseThisOption();
    }

    public void onChoseThisOption() {
        AbstractRelic relic= null;
        if (AbstractDungeon.player.hasRelic(Offensive5.ID)){
            for (AbstractRelic r:AbstractDungeon.player.relics){
                if (r instanceof Offensive5)
                    relic = (Offensive5)r;
            }
        }
        addToBot((AbstractGameAction)new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,(AbstractPower)new NexusPower(AbstractDungeon.player,((Offensive5)relic).nexusGroup)));
    }

    public AbstractCard makeCopy() {
        return new Nexus();
    }
}
