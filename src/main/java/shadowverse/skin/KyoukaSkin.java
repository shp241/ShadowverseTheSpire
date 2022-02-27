package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import shadowverse.characters.Witchcraft;

public class KyoukaSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Witchcraft");
    public static final String SCML = "img/character/Witchcraft/sprite/Witchcraft.scml";
    public static final String SELECTED = "witch_selected";
    public static final String HURT_SOUND1 = "XCW_Hurt";
    public static final String HURT_SOUND2 = "XCW_Hurt2";
    public static final String HURT_SOUND3 = "XCW_Hurt3";
    public static final String HURT_SOUND4 = "XCW_Hurt4";

    public KyoukaSkin(){
        this.NAME =  charStrings.NAMES[1];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Witchcraft/background.png");
        this.SHOULDER1 = Witchcraft.WITCHCRAFT_SHOULDER_1;
        this.SHOULDER2 = Witchcraft.WITCHCRAFT_SHOULDER_2;
        this.CORPSE = Witchcraft.WITCHCRAFT_CORPSE;
        this.scmlURL = SCML;
        this.select = SELECTED;
        this.hurt = HURT_SOUND1;
        this.hurt2 = HURT_SOUND2;
        this.hurt3 = HURT_SOUND3;
        this.hurt4 = HURT_SOUND4;
    }
}
