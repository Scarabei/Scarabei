
package com.jfixby.cmns.api.collections;

public interface Heap<T> {

	public void clear ();

	public int size ();

	public void add (final T element);

	public void addAll (final T... elements);

	public T peek ();

	public T remove ();

	public void print (String tag);

}
