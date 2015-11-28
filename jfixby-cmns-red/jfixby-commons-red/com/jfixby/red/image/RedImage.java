package com.jfixby.red.image;

import com.jfixby.cmns.api.color.Color;
import com.jfixby.cmns.api.debug.Debug;
import com.jfixby.cmns.api.image.EditableColorMap;

public abstract class RedImage implements EditableColorMap {
	final private int width;
	final private int height;
	final private Color default_color;
	private final ArraySupply supply;

	public RedImage(int width, int height, Color default_color, ArraySupply supply) {
		this.width = width;
		this.height = height;
		this.default_color = Debug.checkNull("default_color", default_color);
		this.supply = Debug.checkNull("supply", supply);
	}

	@Override
	public String toString() {
		return "EditableColorMap[" + width + "x" + height + "] ";
	}

	@Override
	final public Color getValue(int x, int y) {
		if (this.out_of_the_scope(x, y)) {
			return default_color;
		}
		final Color value = supply.get(x, y);
		if (value == null) {
			return default_color;
		}
		return value;
	}

	@Override
	final public void setValue(int x, int y, Color color_value) {
		if (this.out_of_the_scope(x, y)) {
			return;
		}
		this.supply.set(x, y, color_value);
	}

	final private boolean out_of_the_scope(final int x, final int y) {
		if (x < 0) {
			return true;
		}
		if (y < 0) {
			return true;
		}
		if (x >= this.width) {
			return true;
		}
		if (y >= this.height) {
			return true;
		}
		return false;
	}

	@Override
	public int getWidth() {
		return this.width;
	}

	@Override
	public int getHeight() {
		return this.height;
	}

}
