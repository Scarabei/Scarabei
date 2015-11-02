package com.jfixby.red.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.image.LambdaColorMapSpecs;

public class LambdaSupply implements ImageSupply {

	public LambdaSupply(LambdaColorMapSpecs lambda_specs) {
	}

	@Override
	public Color get(int x, int y) {
		return null;
	}

	@Override
	public void set(int x, int y, Color color_value) {
	}

}
