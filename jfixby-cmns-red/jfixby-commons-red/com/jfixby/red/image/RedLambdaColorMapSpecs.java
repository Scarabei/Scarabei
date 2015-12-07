package com.jfixby.red.image;

import com.jfixby.cmns.api.image.LambdaColorMapSpecs;
import com.jfixby.cmns.api.lambda.λImage;

public class RedLambdaColorMapSpecs implements LambdaColorMapSpecs {

	private int w;
	private int h;
	private λImage base;

	@Override
	public void setColorMapWidth(int w) {
		this.w = w;
	}

	@Override
	public void setColorMapHeight(int h) {
		this.h = h;
	}

	@Override
	public int getColorMapWidth() {
		return w;
	}

	@Override
	public int getColorMapHeight() {
		return h;
	}

	@Override
	public void setLambdaColoredImage(λImage base) {
		this.base = base;
	}

	@Override
	public λImage getLambdaColoredImage() {
		return base;
	}

	@Override
	public void setColorMapDimentions(int w, int h) {
		this.setColorMapWidth(w);
		this.setColorMapHeight(h);
	}

}
