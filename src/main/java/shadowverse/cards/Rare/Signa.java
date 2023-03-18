package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Vampire;


public class Signa
        extends CustomCard {
    public static final String ID = "shadowverse:Signa";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Signa");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Signa.png";

    public Signa() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Vampire.Enums.COLOR_SCARLET, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 3;
        this.baseMagicNumber = 9;
        this.magicNumber = this.baseMagicNumber;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
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
        addToBot(new SFXAction("Signa"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.damage, true), this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE, true));
        addToBot(new ApplyPowerAction(p,p,new BlurPower(p,1),1));
        if (this.upgraded){
            int count = 0;
            if (AbstractDungeon.player instanceof AbstractShadowversePlayer) {
                count = ((AbstractShadowversePlayer) AbstractDungeon.player).upgradedThisCombat;
            }
            if (count > 2){
                addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
                addToBot(new DamageAllEnemiesAction(p, DamageInfo.createDamageMatrix(this.magicNumber, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE, true));
            }
            if (count > 6){
                addToBot(new ApplyPowerAction(p,p,new StrengthPower(p,1),1));
                addToBot(new ApplyPowerAction(p,p,new DexterityPower(p,1),1));
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new Signa();
    }
}

