package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.Color;

public interface EditableColorMap extends ColorMap {

	void setValue(int x, int y, Color color_value);

}
