package shadowverse.skin;

import com.megacrit.cardcrawl.core.CardCrawlGame;

public class AbstractNemesisSkin extends AbstractSkinCharacter{
    public static final String ID = (CardCrawlGame.languagePack.getCharacterString("shadowverse:Nemesis")).NAMES[0];

    public static final AbstractSkin[] SKINS = new AbstractSkin[] { new OrchidSkin(), new RalmiaSkin(), new AmethSkin(), new KaiserSkin() };

    public AbstractNemesisSkin() {
        super(ID, SKINS);
        this.lockString = (CardCrawlGame.languagePack.getUIString("shadowverse:Skin")).TEXT[0];
    }

}
