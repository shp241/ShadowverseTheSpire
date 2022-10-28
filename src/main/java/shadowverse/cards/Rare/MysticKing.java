package shadowverse.cards.Rare;


import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import shadowverse.cards.Temp.MagicalPawn;
import shadowverse.characters.Nemesis;
import shadowverse.characters.Witchcraft;
import shadowverse.powers.MysticKingPower;


public class MysticKing extends CustomCard {
    public static final String ID = "shadowverse:MysticKing";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:MysticKing");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/MysticKing.png";

    public MysticKing() {
        super(ID, NAME, IMG_PATH, 7, DESCRIPTION, CardType.ATTACK, Witchcraft.Enums.COLOR_BLUE, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 55;
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
        this.cardsToPreview = new MagicalPawn();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
        }
    }


    @Override
    public void onRetained() {
        int count = 0;
        for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
            if (c instanceof MagicalPawn)
                count++;
        }
        if (count>=8){
            this.superFlash();
            this.modifyCostForCombat(-4);
        }
    }


    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new BorderFlashEffect(Color.BLACK, true)));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new MiracleEffect(Color.LIGHT_GRAY.cpy(),Color.WHITE.cpy(),"HEAL_3")));
        addToBot(new GainBlockAction(abstractPlayer,this.block));
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new MysticKingPower(abstractPlayer,this.magicNumber),this.magicNumber));
        addToBot(new MakeTempCardInHandAction(this.cardsToPreview.makeStatEquivalentCopy(),4));
        this.cost = 8;
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new MysticKing();
    }
}

