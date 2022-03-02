package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;

public class YukariSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Bishop");
    private static final String SHOULDER = "img/character/Bishop/shoulder2.png";
    private static final String SKIN_CORPSE = "img/character/Bishop/corpse2.png";
    private static final String SCML = "img/character/Bishop/Yukari/images/Yukari.scml";
    public static final String SELECTED = "Yukari_Select";
    public static final String HURT_SOUND1 = "Yukari_Hurt";
    public static final String HURT_SOUND2 = "Yukari_Hurt2";
    public static final String HURT_SOUND3 = "Yukari_Hurt3";
    public static final String HURT_SOUND4 = "Yukari_Hurt4";

    public YukariSkin(){
        this.NAME =  charStrings.NAMES[2];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Bishop/background2.png");
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
