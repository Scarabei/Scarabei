package com.jfixby.red.image;

import com.jfixby.cmns.api.image.GrayMap;
import com.jfixby.cmns.api.image.GrayλImage;
import com.jfixby.cmns.api.math.FloatMath;

public class RedScaledGrayMap implements GrayMap {

    private int W;
    private int H;
    private GrayMap base;
    private GrayλImage lambda = new GrayλImage() {
	@Override
	public float valueAt(final float x, final float y) {
	    return value(x, y);
	}
    };
    private float scaleX;
    private float scaleY;

    public RedScaledGrayMap(GrayMap base, float scaleX, float scaleY) {
	this.base = base;
	this.scaleX = scaleX;
	this.scaleY = scaleY;
	this.W = (int) FloatMath.round(base.getWidth() * scaleX);
	this.H = (int) FloatMath.round(base.getHeight() * scaleY);
    }

    @Override
    public final float valueAt(final float x, final float y) {
	return this.base.valueAt(x / scaleX, y / scaleY);
    }

    final float value(final float x, final float y) {
	return this.valueAt(x, y);
    }

    @Override
    public final int getWidth() {
	return this.W;
    }

    @Override
    public final int getHeight() {
	return this.H;
    }

    @Override
    public GrayλImage getLambdaImage() {
	return lambda;
    }

}
