package com.jfixby.cmns.api.collections;

public interface Histogramm<T> extends Collection<T> {

    void add(T value);

    long getNumberOf(T value);

    long getMax();

    void print(String tag);

    void sortValues();

    void addIf(T value, boolean condition);

    long getNumberAt(long index);

    int size();

    T getValueAt(long index);

    void sortNumbers();

    void cutToSize(int index_max_size);

}
