package com.jfixby.cmns.api.image;

import java.awt.image.BufferedImage;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.ColorConstant;

public interface ColorFunction {

	int getWidth();

	int getHeight();

	Color getValue(int x, int y);

	void setValue(int x, int y, Color color_value);

	void setDefaultColor(ColorConstant color);

	BufferedImage toJavaImage();

}
