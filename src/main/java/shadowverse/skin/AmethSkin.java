package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CharacterStrings;

public class AmethSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Nemesis");
    private static final String SHOULDER = "img/character/Nemesis/shoulder3.png";
    private static final String SKIN_CORPSE = "img/character/Nemesis/corpse3.png";
    private static final String SCML = "img/character/Nemesis/Ameth/Ameth.scml";
    public static final String SELECTED = "Ameth_Selected";

    public AmethSkin(){
        this.NAME =  charStrings.NAMES[3];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Nemesis/background3.png");
        this.SHOULDER1 = SHOULDER;
        this.SHOULDER2 = SHOULDER;
        this.CORPSE = SKIN_CORPSE;
        this.scmlURL = SCML;
        this.select = SELECTED;
    }

    @Override
    public void playHurtSound(int lastDamageTaken) {
    }
}
