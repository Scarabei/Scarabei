
package com.jfixby.scarabei.api.image;

public interface GrayLambdaImageCache {
	float get (float x, float y);

	void put (float x, float y, float value);

	void print (String tag);
}
