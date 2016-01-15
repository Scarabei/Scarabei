package com.jfixby.cmns.api.lambda.img;

import com.jfixby.cmns.api.color.Color;

public interface λImageCache {
	Color get(float x, float y);

	void put(float x, float y, Color value);

	void print(String tag);
}
