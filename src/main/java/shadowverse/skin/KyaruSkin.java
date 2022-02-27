package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import shadowverse.characters.Witchcraft;

public class KyaruSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Witchcraft");
    private static final String SHOULDER = "img/character/Witchcraft/shoulder2.png";
    private static final String SKIN_CORPSE = "img/character/Witchcraft/corpse2.png";

    public KyaruSkin(){
        this.NAME =  charStrings.NAMES[2];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Witchcraft/background2.png");
        this.SHOULDER1 = SHOULDER;
        this.SHOULDER2 = SHOULDER;
        this.CORPSE = SKIN_CORPSE;
    }
}
