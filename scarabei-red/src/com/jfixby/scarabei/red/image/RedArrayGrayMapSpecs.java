
package com.jfixby.scarabei.red.image;

import com.jfixby.scarabei.api.image.ArrayGrayMapSpecs;

public class RedArrayGrayMapSpecs implements ArrayGrayMapSpecs {

	private int w;
	private int h;

	@Override
	public void setWidth (int width) {
		this.w = width;
	}

	@Override
	public void setHeight (int height) {
		this.h = height;
	}

	@Override
	public int getWidth () {
		return w;
	}

	@Override
	public int getHeight () {
		return h;
	}

}
