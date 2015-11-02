package com.jfixby.red.desktop.img.processing;

import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;
import com.jfixby.cmns.api.image.LambdaImage;
import com.jfixby.cmns.api.math.FloatMath;
import com.jfixby.red.image.ArraySupply;
import com.jfixby.red.image.RedImage;

public class DesktopColorFunction extends RedImage implements ArrayColorMap {

	public DesktopColorFunction(ArrayColorMapSpecs specs) {
		super(specs.getWidth(), specs.getHeight(), specs.getDefaultColor(), new ArraySupply(specs));
	}

	static public int toInt(float x) {
		return (int) FloatMath.round(x);
	}

	@Override
	public LambdaImage getRedChannel() {
		return (x, y) -> this.getValue(toInt(x), toInt(y)).red();
	}

	@Override
	public LambdaImage getGreenChannel() {
		return (x, y) -> this.getValue(toInt(x), toInt(y)).green();
	}

	@Override
	public LambdaImage getBlueChannel() {
		return (x, y) -> this.getValue(toInt(x), toInt(y)).blue();
	}

	@Override
	public LambdaImage getAlphaChannel() {
		return (x, y) -> this.getValue(toInt(x), toInt(y)).alpha();
	}

	@Override
	public LambdaImage getGrayscale(float grayscale_alpha, float grayscale_betta, float grayscale_gamma) {
		return (x, y) -> this.getValue(toInt(x), toInt(y)).getGrayscaleValue(grayscale_alpha, grayscale_betta, grayscale_gamma);
	}

	@Override
	public LambdaImage getGrayscale() {
		return (x, y) -> this.getValue(toInt(x), toInt(y)).getGrayscaleValue();
	}

	// 0.21 R + 0.72 G + 0.07 B.
}
