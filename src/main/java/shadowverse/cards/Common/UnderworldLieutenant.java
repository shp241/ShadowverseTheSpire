package shadowverse.cards.Common;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import shadowverse.action.NecromanceAction;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Necromancer;


public class UnderworldLieutenant
        extends CustomCard {
    public static final String ID = "shadowverse:UnderworldLieutenant";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:UnderworldLieutenant");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/UnderworldLieutenant.png";

    public UnderworldLieutenant() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Necromancer.Enums.COLOR_PURPLE, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 3;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.LASTWORD);
        this.tags.add(AbstractShadowversePlayer.Enums.CONDEMNED);
        this.cardsToPreview = new SpartoiSoldier();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public void triggerOnExhaust() {
        addToBot(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(6, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, this.block));
        AbstractCard tmp = this.cardsToPreview.makeStatEquivalentCopy();
        tmp.setCostForTurn(0);
        tmp.costForTurn = 0;
        tmp.isCostModified = true;
        tmp.exhaustOnUseOnce = true;
        tmp.exhaust = true;
        tmp.rawDescription += " NL " + TEXT + " 。";
        tmp.initializeDescription();
        tmp.applyPowers();
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                p.hand.addToTop(tmp);
                isDone = true;
            }
        });
        if (upgraded) {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    p.hand.addToTop(tmp);
                    isDone = true;
                }
            });
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new UnderworldLieutenant();
    }
}

