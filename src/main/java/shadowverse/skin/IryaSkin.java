package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;

public class IryaSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Vampire");
    private static final String SHOULDER = "img/character/Vampire/shoulder.png";
    private static final String SKIN_CORPSE = "img/character/Vampire/corpse.png";
    private static final String SCML = "img/character/Vampire/images/Vampire.scml";
    public static final String SELECTED = "Vampire_Selected";
    public static final String HURT_SOUND1 = "Vampire_Hurt";
    public static final String HURT_SOUND2 = "Vampire_Hurt2";
    public static final String HURT_SOUND3 = "Vampire_Hurt3";
    public static final String HURT_SOUND4 = "Vampire_Hurt4";

    public IryaSkin(){
        this.NAME =  charStrings.NAMES[1];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Vampire/background.png");
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
