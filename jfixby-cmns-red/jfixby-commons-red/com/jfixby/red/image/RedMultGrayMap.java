package com.jfixby.red.image;

import com.jfixby.cmns.api.image.GrayMap;
import com.jfixby.cmns.api.image.GrayλImage;

public class RedMultGrayMap implements GrayMap, GrayλImage {

    private float mult;

    public RedMultGrayMap(GrayMap image, float mult) {
	this.base = image;
	this.mult = mult;
    }

    private GrayMap base;

    @Override
    public final float valueAt(final float x, final float y) {
	return this.base.valueAt(x, y) * mult;
    }

    final float value(final float x, final float y) {
	return this.valueAt(x, y);
    }

    @Override
    public final int getWidth() {
	return base.getWidth();
    }

    @Override
    public final int getHeight() {
	return base.getHeight();
    }

    @Override
    public GrayλImage getLambdaImage() {
	return this;
    }

}
