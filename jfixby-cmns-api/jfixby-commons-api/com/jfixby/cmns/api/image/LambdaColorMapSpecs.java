package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.geometry.Rectangle;

public interface LambdaColorMapSpecs {

	void setAlphaChannel(LambdaImage aplha);

	void setRedChannel(LambdaImage red);

	void setGreenChannel(LambdaImage green);

	void setBlueChannel(LambdaImage blue);

	void setArea(Rectangle rectangle);

	public Rectangle getArea();

	LambdaImage getAlphaChannel();

	LambdaImage getRedChannel();

	LambdaImage getGreenChannel();

	LambdaImage getBlueChannel();

}
