package com.jfixby.cmns.api.image;

import java.awt.image.BufferedImage;

import com.jfixby.cmns.api.color.Color;

public interface ColorFunctionSpecs {

	void setJavaImage(BufferedImage img);

	BufferedImage getJavaImage();

	int getHeight();

	int getWidth();

	Color getDefaultColor();

	void setWidth(int color_function_width);

	void setHeight(int color_function_height);

}
