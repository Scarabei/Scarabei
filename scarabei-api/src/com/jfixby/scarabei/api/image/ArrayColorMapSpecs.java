
package com.jfixby.scarabei.api.image;

import com.jfixby.scarabei.api.color.Color;

public interface ArrayColorMapSpecs {

	int getHeight ();

	int getWidth ();

	Color getDefaultColor ();

	void setDefaultColor (Color default_color);

	void setWidth (int color_function_width);

	void setHeight (int color_function_height);

}
