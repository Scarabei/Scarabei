package com.jfixby.red.desktop.img.processing;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.geometry.Rectangle;
import com.jfixby.cmns.api.image.LambdaColorMap;
import com.jfixby.cmns.api.image.LambdaColorMapSpecs;
import com.jfixby.cmns.api.image.LambdaColoredImage;
import com.jfixby.cmns.api.image.LambdaImage;
import com.jfixby.red.image.RedLambdaColorMap;

public class DesktopLambdaColorMap extends RedLambdaColorMap implements LambdaColorMap {
	private Rectangle lambda_area;
	private Rectangle pixels_area;
	final Float2 tmp = Geometry.newFloat2();

	public DesktopLambdaColorMap(LambdaColorMapSpecs lambda_specs) {
		super(lambda_specs);
		lambda_area = this.getLambdaArea();
		pixels_area = Geometry.newRectangle(getWidth(), getHeight());
	}

	@Override
	public Color getValue(int x, int y) {
		this.tmp.setXY(x, y);
		this.pixels_area.toRelative(tmp);
		this.lambda_area.toAbsolute(tmp);

		final float a = this.getAlphaChannel().value(tmp);
		final float r = this.getRedChannel().value(tmp);
		final float g = this.getGreenChannel().value(tmp);
		final float b = this.getBlueChannel().value(tmp);

		final Color result = Colors.newColor(a, r, g, b);
		return result;
	}

	@Override
	public LambdaImage getGrayscale(float grayscale_alpha, float grayscale_betta, float grayscale_gamma) {
		return (xy) -> this.getValue(DesktopColorFunction.toInt(xy.getX()), DesktopColorFunction.toInt(xy.getY())).getGrayscaleValue(grayscale_alpha, grayscale_betta, grayscale_gamma);
	}

	@Override
	public LambdaImage defaultLambda() {
		return defaultLambda;
	}

	static LambdaImage defaultLambda = xy -> 1f;

	@Override
	public LambdaColoredImage getLambdaColoredImage() {
		return (xy) -> this.getValue(DesktopColorFunction.toInt(xy.getX()), DesktopColorFunction.toInt(xy.getY()));
	}

}
