package shadowverse.skin;

import com.megacrit.cardcrawl.core.CardCrawlGame;

public class AbstractNecroSkin extends AbstractSkinCharacter{
    public static final String ID = (CardCrawlGame.languagePack.getCharacterString("shadowverse:Necromancer")).NAMES[0];

    public static final AbstractSkin[] SKINS = new AbstractSkin[] { new LunaSkin(), new MiyakoSkin(), new ShinobuSkin() };

    public AbstractNecroSkin() {
        super(ID, SKINS);
        this.lockString = (CardCrawlGame.languagePack.getUIString("shadowverse:Skin")).TEXT[0];
    }

}
