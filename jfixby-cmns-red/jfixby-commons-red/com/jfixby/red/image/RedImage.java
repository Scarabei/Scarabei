package com.jfixby.red.image;

import com.jfixby.cmns.api.collections.JUtils;
import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.image.EditableColorMap;
import com.jfixby.cmns.api.image.ArrayColorMapSpecs;

public abstract class RedImage implements EditableColorMap {
	private int width;
	private int height;
	private Color default_color;
	private ImageSupply supply;

	public RedImage(int width, int height, Color default_color, ImageSupply supply) {
		this.width = width;
		this.height = height;
		this.default_color = JUtils.checkNull("default_color", default_color);
		this.supply = JUtils.checkNull("supply", supply);
	}

	@Override
	public String toString() {
		return "EditableColorMap[" + width + "x" + height + "] ";
	}

	@Override
	public Color getValue(int x, int y) {
		if (out_of_the_scope(x, y)) {
			return default_color;
		}
		final Color value = supply.get(x, y);
		if (value == null) {
			return default_color;
		}
		return value;
	}

	@Override
	public void setValue(int x, int y, Color color_value) {
		if (out_of_the_scope(x, y)) {
			return;
		}
		supply.set(x, y, color_value);
	}

	private boolean out_of_the_scope(int x, int y) {
		if (x < 0) {
			return true;
		}
		if (y < 0) {
			return true;
		}
		if (x >= width) {
			return true;
		}
		if (y >= height) {
			return true;
		}
		return false;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
