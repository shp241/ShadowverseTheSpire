package shadowverse.cards.Uncommon;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
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
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import rs.lazymankits.interfaces.cards.BranchableUpgradeCard;
import rs.lazymankits.interfaces.cards.UpgradeBranch;
import shadowverse.Shadowverse;
import shadowverse.action.Choose2DifferentAction;
import shadowverse.action.Garuda2Action;
import shadowverse.action.Garuda3Action;
import shadowverse.action.ReduceCountDownAction;
import shadowverse.cards.Rare.BelphometCard;
import shadowverse.cards.Temp.*;
import shadowverse.characters.AbstractShadowversePlayer;
import shadowverse.characters.Nemesis;
import shadowverse.powers.SpinePower;

import java.util.ArrayList;
import java.util.List;

public class Garuda extends CustomCard implements BranchableUpgradeCard{
    public static final String ID = "shadowverse:Garuda";
    public static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:Garuda");
    public static CardStrings cardStrings2 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Garuda2");
    public static CardStrings cardStrings3 = CardCrawlGame.languagePack.getCardStrings("shadowverse:Garuda3");
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String IMG_PATH = "img/cards/Garuda.png";
    public static final String IMG_PATH2 = "img/cards/Garuda2.png";
    public static final String IMG_PATH3 = "img/cards/Garuda3.png";

    private int previewBranch;
    public boolean doubleCheck = false;



    public Garuda() {
        super(ID, NAME, IMG_PATH, 2, DESCRIPTION, CardType.ATTACK, Nemesis.Enums.COLOR_SKY, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 15;
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (this.chosenBranch()==1){
            if (AbstractDungeon.player.hasPower("Burst") || AbstractDungeon.player.hasPower("Double Tap") || AbstractDungeon.player.hasPower("Amplified")) {
                doubleCheck = true;
                if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                    setCostForTurn(0);
                    this.type = CardType.SKILL;
                    applyPowers();
                }
            } else {
                if (doubleCheck) {
                    doubleCheck = false;
                } else {
                    if (EnergyPanel.getCurrentEnergy() - c.costForTurn < this.cost) {
                        setCostForTurn(0);
                        this.type = CardType.SKILL;
                        applyPowers();
                    }
                }
            }
        }
    }

    public void triggerOnGainEnergy(int e, boolean dueToCard) {
        if (this.chosenBranch()==1){
            if (EnergyPanel.getCurrentEnergy() >= 4 && this.type != CardType.ATTACK) {
                resetAttributes();
                this.type = CardType.ATTACK;
                applyPowers();
            }
        }
    }

    public void triggerWhenDrawn() {
        if (this.chosenBranch()==1){
            if (Shadowverse.Accelerate((AbstractCard) this)) {
                super.triggerWhenDrawn();
                setCostForTurn(0);
                this.type = CardType.SKILL;
            } else {
                this.type = CardType.ATTACK;
            }
            applyPowers();
        }
    }

    @Override
    public void atTurnStart() {
        if (this.chosenBranch()==1){
            if (AbstractDungeon.player.hand.group.contains(this)) {
                if (EnergyPanel.getCurrentEnergy() < 4) {
                    setCostForTurn(0);
                    this.type = CardType.SKILL;
                } else {
                    resetAttributes();
                    this.type = CardType.ATTACK;
                }
                applyPowers();
            }
        }
    }

    public void onMoveToDiscard() {
        if (this.chosenBranch()==1){
            resetAttributes();
            this.type = CardType.ATTACK;
            applyPowers();
        }
    }


    @Override
    public List<UpgradeBranch> possibleBranches() {
        ArrayList<UpgradeBranch> list = new ArrayList<UpgradeBranch>();
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Garuda.this.timesUpgraded;
                Garuda.this.upgraded = true;
                Garuda.this.name = NAME + "+";
                Garuda.this.initializeTitle();
                Garuda.this.baseDamage = 20;
                Garuda.this.upgradedDamage = true;
                Garuda.this.previewBranch = 0;
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Garuda.this.timesUpgraded;
                Garuda.this.upgraded = true;
                Garuda.this.textureImg = IMG_PATH2;
                Garuda.this.loadCardImage(IMG_PATH2);
                Garuda.this.name = cardStrings2.NAME;
                Garuda.this.initializeTitle();
                Garuda.this.upgradeBaseCost(4);
                Garuda.this.tags.add(AbstractShadowversePlayer.Enums.ACCELERATE);
                Garuda.this.rawDescription = cardStrings2.DESCRIPTION;
                Garuda.this.initializeDescription();
                Garuda.this.previewBranch = 1;
                Garuda.this.rarity = CardRarity.RARE;
                Garuda.this.setDisplayRarity(Garuda.this.rarity);
            }
        });
        list.add(new UpgradeBranch() {
            @Override
            public void upgrade() {
                ++Garuda.this.timesUpgraded;
                Garuda.this.upgraded = true;
                Garuda.this.textureImg = IMG_PATH3;
                Garuda.this.loadCardImage(IMG_PATH3);
                Garuda.this.name = cardStrings3.NAME;
                Garuda.this.initializeTitle();
                Garuda.this.rawDescription = cardStrings3.DESCRIPTION;
                Garuda.this.initializeDescription();
                Garuda.this.previewBranch = 2;
                Garuda.this.rarity = CardRarity.RARE;
                Garuda.this.setDisplayRarity(Garuda.this.rarity);
            }
        });
        return list;
    }

    public void applyPowers() {
        super.applyPowers();
        if (previewBranch==2){
            int count = 0;
            if (AbstractDungeon.player instanceof AbstractShadowversePlayer){
                count = ((AbstractShadowversePlayer) AbstractDungeon.player).amuletCount;
            }
            this.rawDescription = cardStrings3.DESCRIPTION;
            this.rawDescription += cardStrings3.EXTENDED_DESCRIPTION[0] + count;
            this.rawDescription += cardStrings3.EXTENDED_DESCRIPTION[1];
            initializeDescription();
        }
    }

    @Override
    public void upgrade() {
        ((UpgradeBranch)((BranchableUpgradeCard)this).possibleBranches().get(chosenBranch())).upgrade();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot((AbstractGameAction)new VFXAction((AbstractCreature)abstractPlayer, (AbstractGameEffect)new ShockWaveEffect(abstractPlayer.hb.cX, abstractPlayer.hb.cY, Settings.BLUE_TEXT_COLOR, ShockWaveEffect.ShockWaveType.ADDITIVE), 0.2F));
        if (!this.upgraded){
            addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            addToBot((AbstractGameAction)new SFXAction("Garuda"));
            addToBot((AbstractGameAction)new ReduceCountDownAction(3));
        }else {
            switch (chosenBranch()){
                case 0:
                    addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                    addToBot((AbstractGameAction)new SFXAction("Garuda"));
                    addToBot((AbstractGameAction)new ReduceCountDownAction(3));
                    break;
                case 1:
                    if (Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
                        addToBot((AbstractGameAction)new SFXAction("Garuda2_Acc"));
                        addToBot((AbstractGameAction)new ReduceCountDownAction(1));
                    }else {
                        addToBot((AbstractGameAction)new SFXAction("Garuda2"));
                        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect) new WhirlwindEffect(new Color(1.0F, 0.9F, 0.4F, 1.0F), true)));
                        addToBot((AbstractGameAction)new Garuda2Action(3,this.damage));
                    }
                    break;
                case 3:
                    addToBot((AbstractGameAction)new SFXAction("Garuda3"));
                    addToBot((AbstractGameAction)new DamageAction((AbstractCreature)abstractMonster, new DamageInfo((AbstractCreature)abstractPlayer, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                    addToBot((AbstractGameAction)new Garuda3Action());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public AbstractCard makeSameInstanceOf() {
        AbstractCard card = null;
        if (this.chosenBranch()==1&&Shadowverse.Accelerate((AbstractCard) this) && this.type == CardType.SKILL) {
            card = (new Garuda2_Acc()).makeStatEquivalentCopy();
            card.uuid = (new Garuda2_Acc()).uuid;
        } else {
            card = makeStatEquivalentCopy();
            card.uuid = this.uuid;
        }
        return card;
    }


    @Override
    public AbstractCard makeCopy() {
        return (AbstractCard)new Garuda();
    }
}
