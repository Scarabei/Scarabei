package com.jfixby.red.image;

import com.jfixby.cmns.api.image.ArrayGrayMap;
import com.jfixby.cmns.api.image.ArrayGrayMapSpecs;
import com.jfixby.cmns.api.image.GrayMap;
import com.jfixby.cmns.api.image.GrayλImage;
import com.jfixby.cmns.api.image.ImageProcessing;

public class RedArrayGrayMap implements ArrayGrayMap, GrayλImage {

    final float[][] data;
    private int W;
    private int H;

    public RedArrayGrayMap(GrayMap image) {
	W = image.getWidth();
	H = image.getHeight();
	data = new float[W + 1][H + 1];
    }

    public RedArrayGrayMap(ArrayGrayMapSpecs specs) {
	W = specs.getWidth();
	H = specs.getHeight();
	data = new float[W + 1][H + 1];
    }

    @Override
    public void setValue(int x, int y, float grayscale_value) {
	data[x][y] = grayscale_value;
    }

    @Override
    public int getWidth() {
	return W;
    }

    @Override
    public int getHeight() {
	return H;
    }

    @Override
    public GrayλImage getLambdaImage() {
	return this;
    }

    @Override
    public float valueAt(float x, float y) {
	final int i = toInt(x);
	final int j = toInt(y);
	return data[i][j];
    }

    static public int toInt(float x) {
	return ImageProcessing.component().roundArgument(x);
    }

}
