package shadowverse.skin;

import com.megacrit.cardcrawl.core.CardCrawlGame;

public class AbstractVampireSkin extends AbstractSkinCharacter{
    public static final String ID = (CardCrawlGame.languagePack.getCharacterString("shadowverse:Vampire")).NAMES[0];

    public static final AbstractSkin[] SKINS = new AbstractSkin[] { new IryaSkin(), new IoSkin(), new VanpiSkin() };

    public AbstractVampireSkin() {
        super(ID, SKINS);
        this.lockString = (CardCrawlGame.languagePack.getUIString("shadowverse:Skin")).TEXT[0];
    }

}
