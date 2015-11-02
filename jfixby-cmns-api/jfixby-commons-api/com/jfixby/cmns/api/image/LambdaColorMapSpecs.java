package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.geometry.Rectangle;

public interface LambdaColorMapSpecs {

	void setAlphaChannel(LambdaImage aplha);

	void setRedChannel(LambdaImage red);

	void setGreenChannel(LambdaImage green);

	void setBlueChannel(LambdaImage blue);

	void setLambdaArea(Rectangle rectangle);

	public Rectangle getLambdaArea();

	LambdaImage getAlphaChannel();

	LambdaImage getRedChannel();

	LambdaImage getGreenChannel();

	LambdaImage getBlueChannel();

	void setColorMapWidth(int w);

	void setColorMapHeight(int h);

	public int getColorMapWidth();

	public int getColorMapHeight();

}
