
package com.jfixby.scarabei.api.collections;

public interface Queue<T> extends Collection<T> {

	void enqueue (T element);

	void enqueueAll (Collection<T> elements);

	boolean hasMore ();

	T dequeue ();

	void clear ();

}
