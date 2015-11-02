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

	static public int toInt(double x) {
		return (int) FloatMath.round(x);
	}

	@Override
	public LambdaImage getRedChannel() {
		return (xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY())).red();
	}

	@Override
	public LambdaImage getGreenChannel() {
		return (xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY())).green();
	}

	@Override
	public LambdaImage getBlueChannel() {
		return (xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY())).blue();
	}

	@Override
	public LambdaImage getAlphaChannel() {
		return (xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY())).alpha();
	}

	@Override
	public LambdaImage getGrayscale(float grayscale_alpha, float grayscale_betta, float grayscale_gamma) {
		return (xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY())).getGrayscaleValue(grayscale_alpha, grayscale_betta, grayscale_gamma);
	}

	@Override
	public LambdaImage getGrayscale() {
		return (xy) -> this.getValue(toInt(xy.getY()), toInt(xy.getY())).getGrayscaleValue();
	}

	// 0.21 R + 0.72 G + 0.07 B.
}
