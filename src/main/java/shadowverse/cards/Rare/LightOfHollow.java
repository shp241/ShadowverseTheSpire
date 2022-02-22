package shadowverse.cards.Rare;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import com.megacrit.cardcrawl.vfx.combat.VerticalImpactEffect;
import shadowverse.cards.AbstractRightClickCard2;
import shadowverse.cards.Temp.GildedBlade;
import shadowverse.cards.Temp.GildedBoots;
import shadowverse.cards.Temp.GildedGoblet;
import shadowverse.cards.Temp.GildedNecklace;
import shadowverse.cards.Uncommon.UltimateHollow;
import shadowverse.characters.Elf;
import shadowverse.characters.Royal;

public class LightOfHollow
        extends AbstractRightClickCard2 {
    public static final String ID = "shadowverse:LightOfHollow";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:LightOfHollow");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/LightOfHollow.png";
    private boolean hasFusion = false;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("ExhaustAction");
    public static final String[] TEXT = uiStrings.TEXT;



    public LightOfHollow() {
        super(ID, NAME, IMG_PATH, 1, DESCRIPTION, CardType.SKILL, Royal.Enums.COLOR_YELLOW, CardRarity.SPECIAL, CardTarget.ALL_ENEMY);
        this.baseDamage = 28;
        this.baseMagicNumber = 0;
        this.magicNumber = this.baseMagicNumber;
        this.selfRetain = true;
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
        }
    }

    @Override
    protected void onRightClick() {
        if (!this.hasFusion && AbstractDungeon.player!=null){
            addToBot((AbstractGameAction)new SelectCardsInHandAction(9,TEXT[0],true,true,card -> {
                return card instanceof GildedBlade || card instanceof GildedBoots || card instanceof GildedGoblet || card instanceof GildedNecklace || card instanceof UltimateHollow;
            }, abstractCards -> {
                if (abstractCards.size()>0){
                    addToBot((AbstractGameAction)new DrawCardAction(1));
                }
                for (AbstractCard c:abstractCards){
                    addToBot((AbstractGameAction) new ExhaustSpecificCardAction(c,AbstractDungeon.player.hand));
                    this.baseMagicNumber++;
                    this.magicNumber = this.baseMagicNumber;
                    applyPowers();
                }
            }));
            this.hasFusion = true;
        }
    }

    public void applyPowers() {
        super.applyPowers();
        int count = this.magicNumber;
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] + count;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }


    @Override
    public void atTurnStart(){
        hasFusion = false;
    }



    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new SFXAction("LightOfHollow"));
        int dmg = this.damage;
        if (this.magicNumber>=4){
            dmg = dmg*2;
            this.baseMagicNumber = 0;
            this.magicNumber = this.baseMagicNumber;
        }
        int leftDamage = dmg;
        AbstractMonster weakestMonster = null;
        int amountOfweakestMonster = 0;
        while (leftDamage > 0) {
            for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
                if (!m.isDead || !m.isDeadOrEscaped()) {
                    if (weakestMonster == null) {
                        weakestMonster = m;
                        continue;
                    }
                    if (m.currentHealth < weakestMonster.currentHealth) {
                        weakestMonster = m;
                    }
                }
            }
            amountOfweakestMonster = weakestMonster.currentHealth + weakestMonster.currentBlock;
            if (leftDamage < amountOfweakestMonster) {
                addToBot((AbstractGameAction)new DamageAction((AbstractCreature)weakestMonster, new DamageInfo((AbstractCreature)abstractPlayer, leftDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
                leftDamage = 0;
                continue;
            } else if (leftDamage >= amountOfweakestMonster) {
                int count = 0;
                for (AbstractMonster m2 : (AbstractDungeon.getMonsters()).monsters) {
                    if (!m2.isDeadOrEscaped()) {
                        count++;
                    }
                }
                if (count <= 1) {
                    addToBot((AbstractGameAction)new DamageAction((AbstractCreature)weakestMonster, new DamageInfo((AbstractCreature)abstractPlayer, leftDamage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
                    leftDamage = 0;
                    continue;
                }
                if (!weakestMonster.isDead || !weakestMonster.isDeadOrEscaped()) {
                    addToBot((AbstractGameAction)new DamageAction((AbstractCreature)weakestMonster, new DamageInfo((AbstractCreature)abstractPlayer, amountOfweakestMonster, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
                    leftDamage -= amountOfweakestMonster;
                    (AbstractDungeon.getMonsters()).monsters.remove(weakestMonster);
                    weakestMonster = null;
                }
            }
        }
    }


    public AbstractCard makeCopy() {
        return (AbstractCard) new LightOfHollow();
    }
}

