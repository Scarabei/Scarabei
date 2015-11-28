package com.jfixby.red.desktop.img.processing;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.image.LambdaColorMap;
import com.jfixby.cmns.api.image.LambdaColorMapSpecs;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.math.FixedInt2;
import com.jfixby.cmns.api.math.IntegerMath;

public class DesktopLambdaColorMap implements LambdaColorMap {

	private λFunction<FixedInt2, Color> lambda;

	private int width;
	private int height;

	public DesktopLambdaColorMap(LambdaColorMapSpecs lambda_specs) {
		this.width = lambda_specs.getColorMapWidth();
		this.height = lambda_specs.getColorMapHeight();
		lambda = Debug.checkNull("lambda", lambda_specs.getLambdaColoredImage());
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
	public Color getValue(int x, int y) {
		return lambda.val(IntegerMath.newInt2(x, y));
	}

	@Override
	public λFunction<FixedInt2, Color> getLambdaImage() {
		return lambda;
	}

}
