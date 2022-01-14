package shadowverse.cards.Temp;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
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
import shadowverse.cards.Common.DemonicProcession;
import shadowverse.cards.Common.HungrySlash;
import shadowverse.cards.Common.SpiritCurator;
import shadowverse.cards.Rare.Ferry;
import shadowverse.cards.Rare.TheLovers;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;


public class EvilEyeDemon_Acc
        extends CustomCard {
    public static final String ID = "shadowverse:EvilEyeDemon_Acc";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:EvilEyeDemon");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/EvilEyeDemon.png";

    public EvilEyeDemon_Acc() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Vampire.Enums.COLOR_SCARLET, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 9;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
        }
    }

    public void applyPowers() {
        AbstractShadowversePlayer w = (AbstractShadowversePlayer) AbstractDungeon.player;
        if (w.wrathCount > 0) {
            this.magicNumber = w.wrathCount * this.baseMagicNumber;
            super.applyPowers();
            this.isMagicNumberModified = (this.magicNumber != this.baseMagicNumber);
        }else {
            super.applyPowers();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction) new SFXAction("EvilEyeDemon_Acc"));
        addToBot((AbstractGameAction)new LoseHPAction((AbstractCreature)p, (AbstractCreature)p, 1));
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new EvilEyeDemon_Acc();
    }
}


