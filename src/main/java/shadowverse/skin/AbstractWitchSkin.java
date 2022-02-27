package shadowverse.skin;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import shadowverse.characters.Witchcraft;

public class AbstractWitchSkin extends AbstractSkinCharacter{
    public static final String ID = (CardCrawlGame.languagePack.getCharacterString("shadowverse:Witchcraft")).NAMES[0];

    public static final AbstractSkin[] SKINS = new AbstractSkin[] { new KyoukaSkin(), new KyaruSkin(), new AnneSkin() };

    public AbstractWitchSkin() {
        super(ID, SKINS);
        this.lockString = (CardCrawlGame.languagePack.getUIString("shadowverse:Skin")).TEXT[0];
    }

}
