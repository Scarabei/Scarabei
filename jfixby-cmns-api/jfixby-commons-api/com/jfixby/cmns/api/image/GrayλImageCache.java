package com.jfixby.cmns.api.image;

public interface GrayλImageCache {
    float get(float x, float y);

    void put(float x, float y, float value);

    void print(String tag);
}
