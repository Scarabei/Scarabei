
package com.jfixby.scarabei.red.image;

import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.color.Colors;
import com.jfixby.scarabei.api.image.ArrayColorMapSpecs;

public class RedArrayColorMapSpecs implements ArrayColorMapSpecs {

	public int getWidth () {
		return width;
	}

	public void setWidth (int width) {
		this.width = width;
	}

	public int getHeight () {
		return height;
	}

	public void setHeight (int height) {
		this.height = height;
	}

	public Color getDefaultColor () {
		return defaultColor;
	}

	public void setDefaultColor (Color defaultColor) {
		this.defaultColor = defaultColor;
	}

	int width;
	int height;
	Color defaultColor = Colors.BLACK();

}
