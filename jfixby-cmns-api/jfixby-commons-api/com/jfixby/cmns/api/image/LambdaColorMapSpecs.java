package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.lambda.λFunction;
import com.jfixby.cmns.api.math.FixedInt2;

public interface LambdaColorMapSpecs {

	void setLambdaColoredImage(λFunction<FixedInt2, Color> base);

	void setColorMapWidth(int w);

	void setColorMapHeight(int h);

	void setColorMapDimentions(int w, int h);

	// ------------

	public int getColorMapWidth();

	public int getColorMapHeight();

	λFunction<FixedInt2, Color> getLambdaColoredImage();

}
