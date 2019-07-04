
package com.jfixby.scarabei.api.image;

import com.jfixby.scarabei.api.color.Color;

public interface ColoredLambdaImageCache {
	Color get (float x, float y);

	void put (float x, float y, Color value);

	void print (String tag);
}
