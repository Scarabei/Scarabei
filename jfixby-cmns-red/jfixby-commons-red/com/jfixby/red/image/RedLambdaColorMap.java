package com.jfixby.red.image;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.geometry.Geometry;
import com.jfixby.cmns.api.geometry.Rectangle;
import com.jfixby.cmns.api.image.LambdaColorMap;
import com.jfixby.cmns.api.image.LambdaColorMapSpecs;
import com.jfixby.cmns.api.image.LambdaColoredImage;
import com.jfixby.cmns.api.image.LambdaImage;

public abstract class RedLambdaColorMap implements LambdaColorMap {

	final private Rectangle area;
	final private LambdaImage red;
	final private LambdaImage green;
	final private LambdaImage blue;
	final private LambdaImage alpha;
	final private LambdaColoredImage colored;

	static boolean ARGB = true;
	static boolean COLORED = !ARGB;
	boolean mode = ARGB;

	public RedLambdaColorMap(LambdaColorMapSpecs lambda_specs) {
		this.width = lambda_specs.getColorMapWidth();
		this.height = lambda_specs.getColorMapHeight();
		this.area = JUtils.checkNull(Geometry.newRectangle(lambda_specs.getLambdaArea()));
		this.colored = lambda_specs.getLambdaColoredImage();
		if (this.colored != null) {
			this.mode = COLORED;
			this.red = xy -> this.colored.value(xy).red();
			this.green = xy -> this.colored.value(xy).green();
			this.blue = xy -> this.colored.value(xy).blue();
			this.alpha = xy -> this.colored.value(xy).alpha();
		} else {
			this.red = JUtils.checkNull("getRedChannel", lambda_specs.getRedChannel());
			this.green = JUtils.checkNull("getGreenChannel", lambda_specs.getGreenChannel());
			this.blue = JUtils.checkNull("getBlueChannel", lambda_specs.getBlueChannel());
			LambdaImage alpha = lambda_specs.getAlphaChannel();
			if (alpha == null) {
				this.alpha = defaultLambda();
			} else {
				this.alpha = alpha;
			}
			this.mode = ARGB;
		}

	}

	public abstract LambdaImage defaultLambda();

	private int width;
	private int height;

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
	public LambdaImage getRedChannel() {
		return this.red;
	}

	@Override
	public LambdaImage getGreenChannel() {
		return this.green;
	}

	@Override
	public LambdaImage getBlueChannel() {
		return this.blue;
	}

	@Override
	public LambdaImage getAlphaChannel() {
		return this.alpha;
	}

	@Override
	public Rectangle getLambdaArea() {
		return this.area;
	}

	@Override
	public LambdaImage getGrayscale() {
		return this.getGrayscale(Color.grayscale_alpha, Color.grayscale_betta, Color.grayscale_gamma);
	}

}
