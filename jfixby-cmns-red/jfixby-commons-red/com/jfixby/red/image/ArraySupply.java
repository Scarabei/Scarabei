package com.jfixby.red.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;

public class ArraySupply implements ImageSupply {

	private int h;
	private int w;

	Color[][] surface;

	public ArraySupply(ArrayColorMapSpecs specs) {
		h = specs.getHeight();
		w = specs.getWidth();
		surface = new Color[w][h];

	}

	@Override
	public Color get(int x, int y) {
		return this.surface[x][y];
	}

	@Override
	public void set(int x, int y, Color color_value) {
		this.surface[x][y] = color_value;
	}

}
