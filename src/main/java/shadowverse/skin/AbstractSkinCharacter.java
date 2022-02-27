package shadowverse.skin;

public class AbstractSkinCharacter {
    public String id;

    public int reskinCount = 0;

    public String lockString;

    public AbstractSkin[] skins;

    public AbstractSkinCharacter(String id, AbstractSkin[] skins) {
        this.id = id;
        this.skins = skins;
    }

    public void InitializeReskinCount() {
        if (this.reskinCount < 0)
            this.reskinCount = 0;
        if (this.reskinCount > this.skins.length)
            this.reskinCount = 0;
    }

    public Boolean isOriginal() {
        return Boolean.valueOf((this.reskinCount <= 0));
    }

}
