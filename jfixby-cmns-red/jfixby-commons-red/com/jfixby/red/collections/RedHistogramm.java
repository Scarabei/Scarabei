package com.jfixby.red.collections;

import com.jfixby.cmns.api.collections.Collections;
import com.jfixby.cmns.api.collections.Histogramm;
import com.jfixby.cmns.api.collections.Map;
import com.jfixby.cmns.api.java.IntValue;

public class RedHistogramm<T> implements Histogramm<T> {
    final Map<T, IntValue> storage = Collections.newMap();
    long max = 0;

    @Override
    public void add(T value) {
	IntValue num = storage.get(value);
	if (num == null) {
	    num = new IntValue();
	    storage.put(value, num);
	}
	num.value = num.value + 1;
	max = max(max, num.value);
    }

    final static private long max(long a, long b) {
	if (a > b) {
	    return a;
	}
	return b;
    }

    @Override
    public long getNumberOf(T value) {
	IntValue num = storage.get(value);
	if (num == null) {
	    return 0;
	}
	return num.value;
    }

    @Override
    public long getMax() {
	return max;
    }

    @Override
    public void print(String tag) {
	storage.print(tag);
    }

    @Override
    public void sort() {
	storage.sortKeys();
    }

    @Override
    public void addIf(T value, boolean condition) {
	if (condition) {
	    this.add(value);
	}
    }

}
