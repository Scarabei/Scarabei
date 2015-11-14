package com.jfixby.red.desktop.img.processing;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.floatn.Float2;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.geometry.Rectangle;
import com.jfixby.cmns.api.image.LambdaColorMap;
import com.jfixby.cmns.api.image.LambdaColorMapSpecs;
import com.jfixby.cmns.api.image.λImage;

public class DesktopLambdaColorMap implements LambdaColorMap {
	private Rectangle lambda_area;
	private Rectangle pixels_area;
	final Float2 tmp = Geometry.newFloat2();
	private λImage lambda;

	private int width;
	private int height;

	public DesktopLambdaColorMap(LambdaColorMapSpecs lambda_specs) {
		this.width = lambda_specs.getColorMapWidth();
		this.height = lambda_specs.getColorMapHeight();
		lambda_area = Geometry.newRectangle(JUtils.checkNull(lambda_specs.getLambdaArea()));
		pixels_area = Geometry.newRectangle(width, height);
		lambda = lambda_specs.getLambdaColoredImage();
	}

	@Override
	public String toString() {
		return "LambdaColorMap[" + width + "x" + height + "] ";
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

	@Override
	public Rectangle getLambdaArea() {
		return this.lambda_area;
	}

	@Override
	public Color getValue(int x, int y) {
		this.tmp.setXY(x, y);
		this.pixels_area.toRelative(tmp);
		this.lambda_area.toAbsolute(tmp);
		return lambda.val(tmp);
	}

	@Override
	public λImage getLambdaColoredImage() {
		return lambda;
	}

}
