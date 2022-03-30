package shadowverse.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import shadowverse.orbs.AmuletOrb;

public class FeelingAction extends AbstractGameAction {

    private AbstractPlayer p = AbstractDungeon.player;
    private int damage;

    public FeelingAction(int amount){
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_MED;
        this.amount = amount;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED) {
            damageAllEnemyByGroup(p.hand);
            damageAllEnemyByGroup(p.drawPile);
            damageAllEnemyByGroup(p.discardPile);
            damageAllEnemyByGroup(p.exhaustPile);
            damageAllEnemyByAmuletZone();
            addToBot((AbstractGameAction)new DamageAllEnemiesAction(p, this.damage, DamageInfo.DamageType.THORNS, AttackEffect.FIRE));
            this.isDone = true;
        }
    }

    private void damageAllEnemyByGroup(CardGroup cardGroup) {
        for (AbstractCard c : cardGroup.group) {
            if (c.type== AbstractCard.CardType.CURSE) {
                this.damage += this.amount;
            }
        }
    }

    private void damageAllEnemyByAmuletZone(){
        for (AbstractOrb o : p.orbs){
            if (o instanceof AmuletOrb){
                if (((AmuletOrb) o).amulet.type== AbstractCard.CardType.CURSE){
                    this.damage+=amount;
                }
            }
        }
    }
}
