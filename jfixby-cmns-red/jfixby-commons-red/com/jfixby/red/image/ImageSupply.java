package com.jfixby.red.image;

import com.jfixby.cmns.api.color.Color;

public interface ImageSupply {

	Color get(int x, int y);

	void set(int x, int y, Color color_value);

}
