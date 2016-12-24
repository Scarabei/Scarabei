package com.jfixby.scarabei.adopted.gdx.atlas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GdxSprite extends Sprite {

    private float aplha = 1;

    public float getAplha() {
	return aplha;
    }

    @Override
    public void setAlpha(float a) {
	super.setAlpha(a);
	this.aplha = a;
    }

    public GdxSprite() {
	super();
    }

    public GdxSprite(Sprite sprite) {
	super(sprite);
    }

    public GdxSprite(Texture texture, int srcX, int srcY, int srcWidth, int srcHeight) {
	super(texture, srcX, srcY, srcWidth, srcHeight);
    }

    public GdxSprite(Texture texture, int srcWidth, int srcHeight) {
	super(texture, srcWidth, srcHeight);
    }

    public GdxSprite(Texture texture) {
	super(texture);
    }

    public GdxSprite(TextureRegion region, int srcX, int srcY, int srcWidth, int srcHeight) {
	super(region, srcX, srcY, srcWidth, srcHeight);
    }

    public GdxSprite(TextureRegion region) {
	super(region);
    }

}
