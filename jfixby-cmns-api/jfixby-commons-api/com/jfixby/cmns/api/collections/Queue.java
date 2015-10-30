package com.jfixby.cmns.api.collections;

public interface Queue<T> extends Collection<T> {

	void push(T element);

	void pushAll(Collection<T> elements);

	boolean hasMore();

	T pop();

	void clear();

}
