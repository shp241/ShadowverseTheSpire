package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import shadowverse.characters.Witchcraft;

public class KyoukaSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Witchcraft");
    public static final String SCML = "";

    public KyoukaSkin(){
        this.NAME =  charStrings.NAMES[1];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Witchcraft/background.png");
        this.SHOULDER1 = Witchcraft.WITCHCRAFT_SHOULDER_1;
        this.SHOULDER2 = Witchcraft.WITCHCRAFT_SHOULDER_2;
        this.CORPSE = Witchcraft.WITCHCRAFT_CORPSE;
    }
}
