
package com.jfixby.scarabei.red.image;

import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.image.ColoredλImageCache;
import com.jfixby.scarabei.api.log.L;

public class RedColoredλImageCache implements ColoredλImageCache {

	final private int width;
	final private int height;
	final private Color[][] array;

	public RedColoredλImageCache (int width, int height) {
		super();
		this.width = width;
		this.height = height;
		this.array = new Color[width][height];

	}

	@Override
	public Color get (float x, float y) {
		return this.array[(int)x][(int)y];
	}

	@Override
	public void put (float x, float y, Color value) {
		this.array[(int)x][(int)y] = value;
	}

	@Override
	public void print (String tag) {
		L.d("RedImageCache");
	}

}
