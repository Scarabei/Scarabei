package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.Color;

public interface ColorMap {

	int getWidth();

	int getHeight();

	Color getValue(int x, int y);

	void setValue(int x, int y, Color color_value);

	void setDefaultColor(Color color);

}
