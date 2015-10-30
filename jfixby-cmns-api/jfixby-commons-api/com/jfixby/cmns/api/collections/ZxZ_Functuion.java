package com.jfixby.cmns.api.collections;

public interface ZxZ_Functuion<T> {

	T getValueAt(long x, long y);

	void setValueAt(long x, long y, T value);

	T removeElementAt(long x, long y);

	Collection<T> getAllValues();


	void print(String tag);

}
