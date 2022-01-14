package shadowverse.cards.Rare;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import shadowverse.action.MinionSummonAction;
import shadowverse.characters.Royal;
import shadowverse.orbs.AmbushMinion;
import shadowverse.orbs.Minion;

public class Tsubaki
        extends CustomCard {
    public static final String ID = "shadowverse:Tsubaki";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Tsubaki");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Tsubaki.png";

    public Tsubaki() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Royal.Enums.COLOR_YELLOW, CardRarity.RARE, CardTarget.NONE);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded){
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION;
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
            if (o instanceof Minion && !(o instanceof AmbushMinion)) {
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
        addToBot((AbstractGameAction)new SFXAction("Tsubaki"));
        if (rally()>=10){
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new AmbushMinion(2,this.magicNumber+1,this.makeStatEquivalentCopy(),false)));
            AbstractCreature m = (AbstractCreature) AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            if (m != null){
                addToBot((AbstractGameAction)new ApplyPowerAction(m,p,(AbstractPower)new VulnerablePower(m,1,false),1));
                addToBot((AbstractGameAction)new ApplyPowerAction(m,p,(AbstractPower)new WeakPower(m,1,false),1));
            }
        }else {
            AbstractDungeon.actionManager.addToBottom(new MinionSummonAction(new AmbushMinion(1,this.magicNumber,this.makeStatEquivalentCopy(),false)));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Tsubaki();
    }

}


