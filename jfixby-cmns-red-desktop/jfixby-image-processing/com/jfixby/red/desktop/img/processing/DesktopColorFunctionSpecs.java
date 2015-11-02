package com.jfixby.red.desktop.img.processing;

import java.awt.image.BufferedImage;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.color.Colors;
import com.jfixby.cmns.api.image.ColorMapSpecs;

public class DesktopColorFunctionSpecs implements ColorMapSpecs {

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

	public BufferedImage getJavaImage() {
		return javaImage;
	}

	public void setJavaImage(BufferedImage javaImage) {
		this.javaImage = javaImage;
	}

	int width;
	int height;
	Color defaultColor = Colors.BLACK();
	BufferedImage javaImage;
}
