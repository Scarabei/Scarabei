package com.jfixby.cmns.api.image;

import com.jfixby.cmns.api.color.Color;

public interface ColoredλImageCache {
    Color get(float x, float y);

    void put(float x, float y, Color value);

    void print(String tag);
}
