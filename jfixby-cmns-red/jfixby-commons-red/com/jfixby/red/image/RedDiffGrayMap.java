package com.jfixby.red.image;

import com.jfixby.cmns.api.image.GrayMap;
import com.jfixby.cmns.api.image.GrayλImage;

public class RedDiffGrayMap implements GrayMap {

    private GrayMap base;
    private GrayλImage lambda = new GrayλImage() {
	@Override
	public float valueAt(final float x, final float y) {
	    return value(x, y);
	}
    };
    private GrayMap reduce;

    public RedDiffGrayMap(GrayMap base, GrayMap reduce) {
	this.base = base;
	this.reduce = reduce;

    }

    @Override
    public final float valueAt(final float x, final float y) {
	return this.base.valueAt(x, y) - this.reduce.valueAt(x, y);
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
	return lambda;
    }

}
