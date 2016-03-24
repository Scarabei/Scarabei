package com.jfixby.cmns.api.collections;

public interface Histogramm<T> {

    void add(T value);

    long getNumberOf(T value);

    long getMax();

    void print(String tag);

    void sort();

    void addIf(T value, boolean condition);

}
