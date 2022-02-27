package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;

public class AnneSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Witchcraft");
    private static final String SHOULDER = "img/character/Witchcraft/shoulder3.png";
    private static final String SKIN_CORPSE = "img/character/Witchcraft/corpse3.png";
    private static final String SCML = "img/character/Witchcraft/Anne/images/Anne.scml";
    public static final String SELECTED = "Anne_Select";
    public static final String HURT_SOUND1 = "Anne_Hurt";
    public static final String HURT_SOUND2 = "Anne_Hurt2";
    public static final String HURT_SOUND3 = "Anne_Hurt3";
    public static final String HURT_SOUND4 = "Anne_Hurt4";

    public AnneSkin(){
        this.NAME =  charStrings.NAMES[3];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Witchcraft/background3.png");
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
