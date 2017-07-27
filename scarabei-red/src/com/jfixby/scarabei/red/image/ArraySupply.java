
package com.jfixby.scarabei.red.image;

import com.jfixby.scarabei.api.color.Color;
import com.jfixby.scarabei.api.image.ArrayColorMapSpecs;

public class ArraySupply {

	final private int h;
	final private int w;

	final Color[][] surface;

	public ArraySupply (ArrayColorMapSpecs specs) {
		h = specs.getHeight();
		w = specs.getWidth();
		surface = new Color[w][h];

	}

	final public Color get (int x, int y) {
		return this.surface[x][y];
	}

	final public void set (int x, int y, Color color_value) {
		this.surface[x][y] = color_value;
	}

}
