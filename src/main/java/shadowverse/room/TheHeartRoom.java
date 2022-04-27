package shadowverse.room;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BloodVial;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import shadowverse.relics.BanShadowverse;

public class TheHeartRoom extends MonsterRoomBoss {
    @Override
    public void onPlayerEntry() {
        AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, (AbstractRelic)new BanShadowverse());
        this.monsters = new MonsterGroup(new AbstractMonster[]{new CorruptHeart()});;
        CardCrawlGame.metricData.path_taken.add("BOSS");
        CardCrawlGame.music.silenceBGM();
        this.monsters.init();
        waitTimer = 0.1F;
    }
}
