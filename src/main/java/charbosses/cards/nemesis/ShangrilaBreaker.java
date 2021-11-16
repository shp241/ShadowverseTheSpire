package charbosses.cards.nemesis;

import charbosses.cards.AbstractBossCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BloodShotEffect;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

public class ShangrilaBreaker extends AbstractBossCard {
    public static final String ID = "shadowverse:ShangrilaBreaker";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:ShangrilaBreaker");

    public static final String IMG_PATH = "img/cards/ShangrilaBreaker.png";

    public ShangrilaBreaker() {
        super(ID, cardStrings.NAME, IMG_PATH, 3, cardStrings.DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.SPECIAL, CardTarget.ENEMY, AbstractMonster.Intent.ATTACK);
        this.baseDamage = 1;
        this.baseBlock = 25;
        this.isMultiDamage = true;
        this.intentMultiAmt = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new GainBlockAction(m,this.block));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BloodShotEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY, 10), 0.25F));
        for (int i = 0; i < 10; i++)
            addToBot((AbstractGameAction) new DamageAction((AbstractCreature) p, new DamageInfo((AbstractCreature) m, this.damage), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(1);
            upgradeBlock(5);
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard) new ShangrilaBreaker();
    }
}
