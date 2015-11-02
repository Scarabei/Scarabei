package com.jfixby.red.desktop.img.processing;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.geometry.Rectangle;
import com.jfixby.cmns.api.image.LambdaColorMap;
import com.jfixby.cmns.api.image.LambdaColorMapSpecs;
import com.jfixby.cmns.api.image.LambdaImage;
import com.jfixby.red.image.RedLambdaColorMap;

public class DesktopLambdaColorMap extends RedLambdaColorMap implements LambdaColorMap {
	private Rectangle lambda_area;
	private Rectangle pixels_area;
	final Float2 tmp = Geometry.newFloat2();

	public DesktopLambdaColorMap(LambdaColorMapSpecs lambda_specs) {
		super(lambda_specs);
		lambda_area = this.getArea();
		pixels_area = Geometry.newRectangle(getWidth(), getHeight());
	}

	@Override
	public Color getValue(int x, int y) {
		this.tmp.setXY(x, y);
		this.pixels_area.toRelative(tmp);
		this.lambda_area.toAbsolute(tmp);

		float lambda_x = (float) tmp.getX();
		float lambda_y = (float) tmp.getY();

		float a = this.getAlphaChannel().value(lambda_x, lambda_y);
		float r = this.getRedChannel().value(lambda_x, lambda_y);
		float g = this.getGreenChannel().value(lambda_x, lambda_y);
		float b = this.getBlueChannel().value(lambda_x, lambda_y);

		Color result = Colors.newColor(a, r, g, b);
		return result;
	}

	@Override
	public LambdaImage getGrayscale(float grayscale_alpha, float grayscale_betta, float grayscale_gamma) {
		return (x, y) -> this.getValue(DesktopColorFunction.toInt(x), DesktopColorFunction.toInt(y)).getGrayscaleValue(grayscale_alpha, grayscale_betta, grayscale_gamma);
	}

	@Override
	public LambdaImage defaultLambda() {
		return defaultLambda;
	}

	static LambdaImage defaultLambda = (x, y) -> 1f;

}
