
package com.jfixby.cmns.api.collections;

public interface Heap<T> {

	void clear ();

	int size ();

	void add (final T element);

	void addAll (final T... elements);

	void remove (final T element);

	public T top ();

	T removeTop ();

	public void print (String tag);

}
