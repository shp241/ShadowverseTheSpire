package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CharacterStrings;

public class ShizuruSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Royal");
    private static final String SHOULDER = "img/character/Royal/shoulder2.png";
    private static final String SKIN_CORPSE = "img/character/Royal/corpse2.png";
    private static final String SCML = "img/character/Royal/Shizuru/images/Shizuru.scml";
    public static final String SELECTED = "Shizuru_Select";
    public static final String HURT_SOUND1 = "Shizuru_Hurt";
    public static final String HURT_SOUND2 = "Shizuru_Hurt2";
    public static final String HURT_SOUND3 = "Shizuru_Hurt3";
    public static final String HURT_SOUND4 = "Shizuru_Hurt4";

    public ShizuruSkin(){
        this.NAME =  charStrings.NAMES[2];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Royal/background2.png");
        this.SHOULDER1 = SHOULDER;
        this.SHOULDER2 = SHOULDER;
        this.CORPSE = SKIN_CORPSE;
        this.scmlURL = SCML;
        this.select = SELECTED;
        this.hurt = HURT_SOUND1;
        this.hurt2 = HURT_SOUND2;
        this.hurt3 = HURT_SOUND3;
        this.hurt4 = HURT_SOUND4;
    }
}
