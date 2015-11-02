package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.Color;

public interface ColorMapSpecs {

	int getHeight();

	int getWidth();

	Color getDefaultColor();

	void setDefaultColor(Color default_color);

	void setWidth(int color_function_width);

	void setHeight(int color_function_height);

}
