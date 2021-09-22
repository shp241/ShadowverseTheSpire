package shadowverse.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import shadowverse.action.RemoveMinionAction;

import java.util.Collections;

public abstract class Minion extends AbstractOrb {
    // Animation Rendering Numbers - You can leave these at default, or play around with them and see what they change.
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;

    public int attack;
    public int baseAttack;
    public int defense;
    public int baseDefense;

    @Override
    public void applyFocus() {
        passiveAmount = basePassiveAmount;
        evokeAmount = baseEvokeAmount;
    }

    public abstract void effect();

    public void order() {
        effect();
    }

    public void buff(int a, int d) {
        this.attack += a;
        this.defense += d;
        AbstractDungeon.actionManager.addToBottom(new RemoveMinionAction());
    }

    @Override
    public void onEvoke() {
        for (int i = 0; i < defense; i++) {
            effect();
        }
        this.defense = 0;
        AbstractDungeon.actionManager.addToBottom(new RemoveMinionAction());
    }

    @Override
    public void onEndOfTurn() {
        effect();
        this.defense -= 1;
        AbstractDungeon.actionManager.addToBottom(new RemoveMinionAction());
    }

    // Render the orb.
    @Override
    public void render(SpriteBatch sb) {
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, scale, angle, 0, 0, 96, 96, false, false);
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, -angle, 0, 0, 96, 96, false, false);
        if (this.defense > this.baseDefense) {
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.defense), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(1.0F, 1.0F, 0.2F, this.c.a), this.fontScale);
        } else if (this.defense < this.baseDefense) {
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.defense), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(1.0F, 0.2F, 0.2F, this.c.a), this.fontScale);
        } else {
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.defense), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(1.0F, 0.2F, 0.2F, this.c.a), this.fontScale);
        }
        if (this.attack > this.baseAttack) {
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.attack), this.cX - NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 0.2F, 1.0F, this.c.a), this.fontScale);
        } else {
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.attack), this.cX - NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, new Color(0.2F, 1.0F, 0.2F, this.c.a), this.fontScale);
        }
        hb.render(sb);
    }
}
