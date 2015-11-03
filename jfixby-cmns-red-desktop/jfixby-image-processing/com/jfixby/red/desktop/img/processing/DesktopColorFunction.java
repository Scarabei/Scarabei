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

	final LambdaImage COLORED = (xy) -> {
		final int i = toInt(xy.getX());
		final int j = toInt(xy.getY());
		return this.getValue(i, j);
	};

	@Override
	public LambdaImage getLambdaColoredImage() {
		return COLORED;
	}

	// 0.21 R + 0.72 G + 0.07 B.
}
