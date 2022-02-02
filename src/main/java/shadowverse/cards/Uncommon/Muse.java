package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import shadowverse.cards.Temp.NaterranGreatTree;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Bishop;
import shadowverse.characters.Necromancer;
import shadowverse.powers.NaterranTree;

public class Muse extends CustomCard {
    public static final String ID = "shadowverse:Muse";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Muse");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Muse.png";

    public Muse() {
        super(ID, NAME, IMG_PATH, 0, DESCRIPTION, CardType.ATTACK, Bishop.Enums.COLOR_WHITE, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 3;
        this.baseBlock = 3;
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        this.tags.add(AbstractShadowversePlayer.Enums.NATURAL);
        this.exhaust = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(2);
        }
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
            if (c instanceof NaterranGreatTree){
                flash();
                this.baseDamage += this.magicNumber;
                this.baseBlock += this.magicNumber;
                this.applyPowers();
            }
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("Muse"));
        addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot((AbstractGameAction)new GainBlockAction(p,this.block));
        this.baseDamage = this.magicNumber;
        this.baseBlock = this.magicNumber;
    }

    public AbstractCard makeCopy() {
        return new Muse();
    }
}
