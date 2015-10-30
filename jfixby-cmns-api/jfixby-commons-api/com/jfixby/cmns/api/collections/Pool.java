package com.jfixby.cmns.api.collections;

public interface Pool<T> {

	T obtain();

	void free(T instance);

	void freeAll(Collection<T> collection);

}
