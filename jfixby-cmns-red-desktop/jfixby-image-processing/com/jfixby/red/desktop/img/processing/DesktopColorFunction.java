package com.jfixby.red.desktop.img.processing;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.floatn.FixedFloat2;
import com.jfixby.cmns.api.image.ArrayColorMap;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;
import com.jfixby.cmns.api.image.λImage;
import com.jfixby.cmns.api.lambda.Lambda;
import com.jfixby.cmns.api.lambda.λExpression;
import com.jfixby.cmns.api.lambda.λFunction;
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

	final private λFunction<FixedFloat2, Color> func = Lambda.newFunction(xy -> this.getValue(toInt(xy.getX()), toInt(xy.getY())));
	final private λImage COLORED = xy -> func.val(xy);

	@Override
	public λImage getLambdaColoredImage() {
		return COLORED;
	}

	// 0.21 R + 0.72 G + 0.07 B.
}
