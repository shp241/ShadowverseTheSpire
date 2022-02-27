package shadowverse.skin;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CharacterStrings;

public class PekoSkin extends AbstractSkin{
    public static final CharacterStrings charStrings = CardCrawlGame.languagePack.getCharacterString("shadowverse:Royal");
    private static final String SHOULDER = "img/character/Royal/shoulder.png";
    private static final String SKIN_CORPSE = "img/character/Royal/corpse.png";
    private static final String SCML = "img/character/Royal/images/Royal.scml";
    public static final String SELECTED = "Royal_Selected";

    public PekoSkin(){
        this.NAME =  charStrings.NAMES[1];
        this.DESCRIPTION = charStrings.TEXT[0];
        this.portraitStatic_IMG = new Texture("img/character/Royal/background.png");
        this.SHOULDER1 = SHOULDER;
        this.SHOULDER2 = SHOULDER;
        this.CORPSE = SKIN_CORPSE;
        this.scmlURL = SCML;
        this.select = SELECTED;
    }

    @Override
    public void playHurtSound(int lastDamageTaken) {
        if (lastDamageTaken > 0) {
            String sound = null;
            int roll = AbstractDungeon.cardRandomRng.random(99);
            if (lastDamageTaken >= 20) {
                if (roll < 50) {
                    sound = "Royal_Hurt3";
                } else {
                    sound = "Royal_Hurt4";
                }
            } else {
                if (roll < 33) {
                    sound = "Royal_Hurt";
                } else if (roll < 66) {
                    sound = "Royal_Hurt1";
                } else {
                    sound = "Royal_Hurt2";
                }
            }
            CardCrawlGame.sound.playA(sound, 0.0F);
        }
    }
}
