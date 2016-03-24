package com.jfixby.cmns.api.color;

public interface GraySet {

    float findClosest(float valueAt);

    GraySet addAll(float... array);

    GraySet add(float value);

    void print(String string);

    void sort();

}
