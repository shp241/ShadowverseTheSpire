package shadowverse.skin;

import com.megacrit.cardcrawl.core.CardCrawlGame;

public class AbstractElfSkin extends AbstractSkinCharacter{
    public static final String ID = (CardCrawlGame.languagePack.getCharacterString("shadowverse:Elf")).NAMES[0];

    public static final AbstractSkin[] SKINS = new AbstractSkin[] { new ArisaSkin(), new KokkoroSkin() };

    public AbstractElfSkin() {
        super(ID, SKINS);
        this.lockString = (CardCrawlGame.languagePack.getUIString("shadowverse:Skin")).TEXT[0];
    }

}
