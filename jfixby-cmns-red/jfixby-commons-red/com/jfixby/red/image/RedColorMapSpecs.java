package com.jfixby.red.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;

public class RedColorMapSpecs implements ArrayColorMapSpecs {

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getDefaultColor() {
		return defaultColor;
	}

	public void setDefaultColor(Color defaultColor) {
		this.defaultColor = defaultColor;
	}

	int width;
	int height;
	Color defaultColor = Colors.BLACK();

}
