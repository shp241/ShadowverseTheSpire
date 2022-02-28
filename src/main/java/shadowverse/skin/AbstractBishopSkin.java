package shadowverse.skin;

import com.megacrit.cardcrawl.core.CardCrawlGame;

public class AbstractBishopSkin extends AbstractSkinCharacter{
    public static final String ID = (CardCrawlGame.languagePack.getCharacterString("shadowverse:Bishop")).NAMES[0];

    public static final AbstractSkin[] SKINS = new AbstractSkin[] { new SarenSkin(), new YukariSkin() };

    public AbstractBishopSkin() {
        super(ID, SKINS);
        this.lockString = (CardCrawlGame.languagePack.getUIString("shadowverse:Skin")).TEXT[0];
    }

}
