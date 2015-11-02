package com.jfixby.red.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.geometry.Rectangle;
import com.jfixby.cmns.api.image.LambdaColorMap;
import com.jfixby.cmns.api.image.LambdaColorMapSpecs;
import com.jfixby.cmns.api.image.LambdaImage;
import com.jfixby.cmns.api.math.FloatMath;

public abstract class RedLambdaColorMap implements LambdaColorMap {

	private Rectangle area;
	private LambdaImage red;
	private LambdaImage green;
	private LambdaImage blue;
	private LambdaImage alpha;

	public RedLambdaColorMap(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public RedLambdaColorMap(LambdaColorMapSpecs lambda_specs) {
		this(getWidth(lambda_specs), getHeight(lambda_specs));
		area = Geometry.newRectangle(lambda_specs.getArea());
		red = lambda_specs.getRedChannel();
		green = lambda_specs.getGreenChannel();
		blue = lambda_specs.getBlueChannel();
		alpha = lambda_specs.getAlphaChannel();
		if (alpha == null) {
			alpha = defaultLambda();
		}
	}

	public abstract LambdaImage defaultLambda();

	private static int getHeight(LambdaColorMapSpecs lambda_specs) {
		return (int) FloatMath.round(lambda_specs.getArea().getHeight());
	}

	private static int getWidth(LambdaColorMapSpecs lambda_specs) {
		return (int) FloatMath.round(lambda_specs.getArea().getWidth());
	}

	private int width;
	private int height;

	@Override
	public String toString() {
		return "LambdaColorMap[" + width + "x" + height + "] ";
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public LambdaImage getRedChannel() {
		return red;
	}

	@Override
	public LambdaImage getGreenChannel() {
		return green;
	}

	@Override
	public LambdaImage getBlueChannel() {
		return blue;
	}

	@Override
	public LambdaImage getAlphaChannel() {
		return alpha;
	}

	@Override
	public Rectangle getArea() {
		return area;
	}

	@Override
	public LambdaImage getGrayscale() {
		return this.getGrayscale(Color.grayscale_alpha, Color.grayscale_betta, Color.grayscale_gamma);
	}

}
