package shadowverse.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class KagemitsuSword extends CustomRelic {
    public static final String ID = "shadowverse:KagemitsuSword";
    public static final String IMG = "img/relics/KagemitsuSword.png";
    public static final String OUTLINE_IMG = "img/relics/outline/KagemitsuSword_Outline.png";

    public KagemitsuSword() {
        super(ID, new Texture(Gdx.files.internal(IMG)), RelicTier.UNCOMMON, LandingSound.SOLID);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public AbstractRelic makeCopy() {
        return new KagemitsuSword();
    }
}
