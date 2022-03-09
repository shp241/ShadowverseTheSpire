package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;

public class LunaSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Necromancer");
    private static final String SHOULDER = "img/character/Necromancer/shoulder.png";
    private static final String SKIN_CORPSE = "img/character/Necromancer/corpse.png";
    private static final String SCML = "img/character/Necromancer/images/necro.scml";
    public static final String SELECTED = "Necro_Selected";
    public static final String HURT_SOUND1 = "Necro_Hurt2";
    public static final String HURT_SOUND2 = "Necro_Hurt3";
    public static final String HURT_SOUND3 = "Necro_Hurt4";
    public static final String HURT_SOUND4 = "Necro_Hurt";

    public LunaSkin(){
        this.NAME =  charStrings.NAMES[1];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Necromancer/background.png");
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
