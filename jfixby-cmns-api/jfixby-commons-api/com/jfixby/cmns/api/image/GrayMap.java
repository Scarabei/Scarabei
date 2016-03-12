package com.jfixby.cmns.api.image;

public interface GrayMap {

    int getWidth();

    int getHeight();

    float getValue(int x, int y);

    GrayλImage getLambdaImage();
}
