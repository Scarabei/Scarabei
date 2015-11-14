package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.geometry.Rectangle;

public interface LambdaColorMapSpecs {

	void setLambdaArea(Rectangle rectangle);

	public Rectangle getLambdaArea();

	void setColorMapWidth(int w);

	void setColorMapHeight(int h);

	public int getColorMapWidth();

	public int getColorMapHeight();

	void setLambdaColoredImage(λImage base);

	λImage getLambdaColoredImage();

	void setColorMapDimentions(int w, int h);

}
