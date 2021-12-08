package charbosses.cards.nemesis;

import charbosses.actions.RealWaitAction;
import charbosses.cards.AbstractBossCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import com.megacrit.cardcrawl.vfx.combat.HeartBuffEffect;
import shadowverse.characters.Nemesis;

public class CalamityEnd extends AbstractBossCard {
    public static final String ID = "shadowverse:CalamityMode";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings("shadowverse:CalamityEnd");

    public static final String IMG_PATH = "img/cards/CalamityEnd.png";

    public CalamityEnd() {
        super(ID, cardStrings.NAME, IMG_PATH, 0, cardStrings.DESCRIPTION, CardType.SKILL, Nemesis.Enums.COLOR_SKY, CardRarity.RARE, CardTarget.SELF, AbstractMonster.Intent.MAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("CalamityEnd"));
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new VFXAction((AbstractGameEffect)new HeartBuffEffect(m.hb.cX, m.hb.cY)));
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new GrandFinalEffect()));
        addToBot((AbstractGameAction)new RealWaitAction(0.3F));
        p.currentHealth-=p.maxHealth;
        addToBot((AbstractGameAction) new LoseHPAction(p,m,99999));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
        }
    }

    public AbstractCard makeCopy() {
        return (AbstractCard)new CalamityEnd();
    }
}
