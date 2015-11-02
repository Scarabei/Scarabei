package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.Color;

public interface ColorFunctionSpecs {

	int getHeight();

	int getWidth();

	Color getDefaultColor();

	void setWidth(int color_function_width);

	void setHeight(int color_function_height);

}
