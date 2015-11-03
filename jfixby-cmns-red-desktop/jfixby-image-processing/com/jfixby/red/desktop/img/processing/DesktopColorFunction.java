package com.jfixby.red.desktop.img.processing;

import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;
import com.jfixby.cmns.api.image.LambdaColoredImage;
import com.jfixby.cmns.api.image.LambdaImage;
import com.jfixby.cmns.api.image.LambdaImageGrayer;
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

	final LambdaImage RED = (xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY())).red();
	final LambdaImage GREEN = (xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY())).green();
	final LambdaImage BLUE = (xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY())).blue();
	final LambdaImage ALPHA = (xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY())).alpha();
	final LambdaImageGrayer GRAYSCALE = (grayscale_alpha, grayscale_betta, grayscale_gamma) -> ((xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY())).getGrayscaleValue(grayscale_alpha, grayscale_betta, grayscale_gamma));
	final LambdaImage GRAY = (xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY())).getGrayscaleValue();
	final LambdaColoredImage COLORED = (xy) -> this.getValue(toInt(xy.getX()), toInt(xy.getY()));

	@Override
	public LambdaImage getRedChannel() {
		return RED;
	}

	@Override
	public LambdaImage getGreenChannel() {
		return GREEN;
	}

	@Override
	public LambdaImage getBlueChannel() {
		return BLUE;
	}

	@Override
	public LambdaImage getAlphaChannel() {
		return ALPHA;
	}

	@Override
	public LambdaImage getGrayscale(float grayscale_alpha, float grayscale_betta, float grayscale_gamma) {
		return GRAYSCALE.gray(grayscale_alpha, grayscale_betta, grayscale_gamma);
	}

	@Override
	public LambdaImage getGrayscale() {
		return GRAY;
	}

	@Override
	public LambdaColoredImage getLambdaColoredImage() {
		return COLORED;
	}

	// 0.21 R + 0.72 G + 0.07 B.
}
