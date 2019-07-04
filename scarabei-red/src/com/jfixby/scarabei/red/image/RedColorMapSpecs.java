
package com.jfixby.scarabei.red.image;

import com.jfixby.scarabei.api.image.ColorMapSpecs;
import com.jfixby.scarabei.api.image.ColoredLambdaImage;
import com.jfixby.scarabei.api.image.GrayLambdaImage;

public class RedColorMapSpecs implements ColorMapSpecs {

	private int w;
	private int h;
	private ColoredLambdaImage base;
	private GrayLambdaImage red;
	private GrayLambdaImage green;
	private GrayLambdaImage blue;
	private GrayLambdaImage alpha;

	@Override
	public void setColorMapWidth (int w) {
		this.w = w;
	}

	@Override
	public void setColorMapHeight (int h) {
		this.h = h;
	}

	@Override
	public int getColorMapWidth () {
		return w;
	}

	@Override
	public int getColorMapHeight () {
		return h;
	}

	@Override
	public void setLambdaColoredImage (ColoredLambdaImage base) {
		this.base = base;
	}

	@Override
	public ColoredLambdaImage getLambdaColoredImage () {
		return base;
	}

	@Override
	public void setColorMapDimentions (int w, int h) {
		this.setColorMapWidth(w);
		this.setColorMapHeight(h);
	}

	@Override
	public void setRed (GrayLambdaImage red) {
		this.red = red;

	}

	@Override
	public void setGreen (GrayLambdaImage green) {
		this.green = green;
	}

	@Override
	public void setBlue (GrayLambdaImage blue) {
		this.blue = blue;
	}

	@Override
	public void setAlpha (GrayLambdaImage alpha) {
		this.alpha = alpha;
	}

	@Override
	public GrayLambdaImage getGreen () {
		return green;
	}

	@Override
	public GrayLambdaImage getRed () {
		return red;
	}

	@Override
	public GrayLambdaImage getBlue () {
		return blue;
	}

	@Override
	public GrayLambdaImage getAlpha () {
		return alpha;
	}

}
