package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import shadowverse.cards.Status.EvolutionPoint;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;

public class MegaEnforcer
        extends CustomCard {
    public static final String ID = "shadowverse:MegaEnforcer";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MegaEnforcer");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MegaEnforcer.png";

    public MegaEnforcer() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.baseBlock = 6;
        this.tags.add(AbstractShadowversePlayer.Enums.MACHINE);
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeBlock(3);
        }
    }




    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("MegaEnforcer"));
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
        int mCount = 0;
        for (AbstractCard c : p.hand.group) {
            if (c.hasTag(AbstractShadowversePlayer.Enums.MACHINE)&&c!=this){
                mCount++;
            }
        }
        if (mCount>0){
            addToBot((AbstractGameAction)new DrawCardAction(1));
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new SmallLaserEffect(abstractMonster.hb.cX, abstractMonster.hb.cY, p.hb.cX, p.hb.cY), 0.1F));
            addToBot((AbstractGameAction) new DamageAction((AbstractCreature) abstractMonster, new DamageInfo((AbstractCreature) p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        }
        if (mCount>1){
            addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new EvolutionPoint()));
            addToBot((AbstractGameAction)new MakeTempCardInHandAction((AbstractCard)new Miracle()));
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MegaEnforcer();
    }
}


