package shadowverse.cards.Neutral;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import shadowverse.characters.AbstractShadowversePlayer;


public class Alice
        extends AbstractNeutralCard {
    public static final String ID = "shadowverse:Alice";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Alice");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Alice.png";

    public Alice() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, CardColor.COLORLESS, CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 20;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            count = ((AbstractShadowversePlayer) AbstractDungeon.player).upgradedThisCombat;
        }
        this.rawDescription = cardStrings.DESCRIPTION;
        if (this.upgraded)
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("Alice"));
        int dmg = this.damage;
        int count = 0;
        if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
            count = ((AbstractShadowversePlayer) AbstractDungeon.player).upgradedThisCombat;
        }
        if (count > 2){
            this.upgrade();
            addToBot(new DiscoveryAction());
            addToBot(new DiscoveryAction());
        }
        if (count > 6){
            dmg *= 2;
        }
        addToBot(new DamageAction(m,new DamageInfo(p, dmg, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (this.upgraded){
            addToBot(new GainEnergyAction(1));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Alice();
    }
}

