package com.jfixby.cmns.api.image.argb;

public interface Channel {

    void checkValid(String name);

    void checkValid(int w, int h);

    float getAlphaValue(int x, int y);

}
