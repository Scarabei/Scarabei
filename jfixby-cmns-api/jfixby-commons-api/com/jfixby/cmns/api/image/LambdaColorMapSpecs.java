package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.lambda.img.λImage;

public interface LambdaColorMapSpecs {

	void setLambdaColoredImage(λImage base);

	void setColorMapWidth(int w);

	void setColorMapHeight(int h);

	void setColorMapDimentions(int w, int h);

	// ------------

	public int getColorMapWidth();

	public int getColorMapHeight();

	λImage getLambdaColoredImage();

}
