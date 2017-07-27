
package com.jfixby.scarabei.red.image;

import com.jfixby.scarabei.api.image.ColorMapSpecs;
import com.jfixby.scarabei.api.image.ColoredλImage;
import com.jfixby.scarabei.api.image.GrayλImage;

public class RedColorMapSpecs implements ColorMapSpecs {

	private int w;
	private int h;
	private ColoredλImage base;
	private GrayλImage red;
	private GrayλImage green;
	private GrayλImage blue;
	private GrayλImage alpha;

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
	public void setLambdaColoredImage (ColoredλImage base) {
		this.base = base;
	}

	@Override
	public ColoredλImage getLambdaColoredImage () {
		return base;
	}

	@Override
	public void setColorMapDimentions (int w, int h) {
		this.setColorMapWidth(w);
		this.setColorMapHeight(h);
	}

	@Override
	public void setRed (GrayλImage red) {
		this.red = red;

	}

	@Override
	public void setGreen (GrayλImage green) {
		this.green = green;
	}

	@Override
	public void setBlue (GrayλImage blue) {
		this.blue = blue;
	}

	@Override
	public void setAlpha (GrayλImage alpha) {
		this.alpha = alpha;
	}

	@Override
	public GrayλImage getGreen () {
		return green;
	}

	@Override
	public GrayλImage getRed () {
		return red;
	}

	@Override
	public GrayλImage getBlue () {
		return blue;
	}

	@Override
	public GrayλImage getAlpha () {
		return alpha;
	}

}
