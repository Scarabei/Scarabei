package com.jfixby.red.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.image.LambdaColorMapSpecs;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.math.FixedInt2;

public class RedLambdaColorMapSpecs implements LambdaColorMapSpecs {

	private int w;
	private int h;
	private λFunction<FixedInt2, Color> base;

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
	public void setLambdaColoredImage(λFunction<FixedInt2, Color> base) {
		this.base = base;
	}

	@Override
	public λFunction<FixedInt2, Color> getLambdaColoredImage() {
		return base;
	}

	@Override
	public void setColorMapDimentions(int w, int h) {
		this.setColorMapWidth(w);
		this.setColorMapHeight(h);
	}

}
