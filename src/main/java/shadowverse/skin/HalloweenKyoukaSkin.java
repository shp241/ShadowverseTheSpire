package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import shadowverse.characters.Witchcraft;

public class HalloweenKyoukaSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Witchcraft");
    public static final String SCML = "img/character/Witchcraft/Halloween/images/Halloween.scml";
    private static final String SHOULDER = "img/character/Witchcraft/shoulder4.png";
    private static final String SKIN_CORPSE = "img/character/Witchcraft/corpse4.png";
    public static final String SELECTED = "HalloweenXCW_Select";
    public static final String HURT_SOUND1 = "HalloweenXCW_Hurt";
    public static final String HURT_SOUND2 = "HalloweenXCW_Hurt2";
    public static final String HURT_SOUND3 = "HalloweenXCW_Hurt3";
    public static final String HURT_SOUND4 = "XCW_Hurt";

    public HalloweenKyoukaSkin(){
        this.NAME =  charStrings.NAMES[4];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Witchcraft/background4.png");
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
