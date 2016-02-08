package com.jfixby.cmns.api.collections;

public interface CollectionFilter<T> {

	public boolean fits(T element);
}
