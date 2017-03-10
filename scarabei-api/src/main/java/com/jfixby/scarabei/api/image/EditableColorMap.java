
package com.jfixby.scarabei.api.image;

import com.jfixby.scarabei.api.color.Color;

public interface EditableColorMap extends ColorMap {

	void setValue (int x, int y, Color color_value);

	EditableGrayMap getAlpha ();

}
